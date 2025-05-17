package solutions;

import java.util.Random;

/**
 * This class uses the Imaginary Network Protocol INP. Each INP packet comes as
 * a byte[].
 * The first 4 bytes contain the packet header,
 * following bytes can contain a masked message containing ascii characters.
 * If there is no message, the mask interval (see below) must be set to 0,
 * otherwise it is randomly chosen.
 * The maximum packet size is 0xFFFE.
 * <p>
 * <p>
 * The packet header looks like this:
 * bits 1-16: always magic number 8647 to identify our protocol
 * bits 17-28: the packet number (min 1), little endian (most significant bit on
 * the left side)
 * bits 29-32: the mask interval, big endian (most significant bit on the right
 * side)
 * <p>
 * <p>
 * All message bytes are xor'd with a mask.
 * The mask always masks 4 consecutive bytes like this
 * message byte 1 ^ mask byte 1
 * message byte 2 ^ mask byte 2
 * message byte 3 ^ mask byte 3
 * message byte 4 ^ mask byte 4
 * message byte 5 ^ mask byte 1
 * message byte 6 ^ mask byte 2
 * message byte 7 ^ mask byte 3
 * message byte 8 ^ mask byte 4
 * message byte 9 ^ mask byte 1
 * ...
 * <p>
 * <p>
 * The mask is a 32-bit number that has every nth bit set to 1 according to the
 * mask interval, starting with a 1 on the left side.
 * For example, if the mask interval is 3, then every third bit of the mask has
 * to be set to 1 etc.
 * <p>
 * <p>
 * mask interval = 1 => mask = 0b11111111111111111111111111111111
 * mask interval = 2 => mask = 0b10101010101010101010101010101010
 * mask interval = 3 => mask = 0b10010010010010010010010010010010
 * ...
 */
public class ImaginaryNetworkProtocol {

    private final static Random random = new Random();

    /**
     * checks if a packet is valid according to the protocol
     *
     * @param packet the packet to check
     * @return true if the packet is valid
     */
    @SuppressWarnings("RedundantIfStatement")
    public static boolean isPacketValid(byte[] packet) {
        if (packet.length < 4 || packet.length > 0xfffe) {
            return false;
        }
        if (!hasCorrectMagicNumber(packet)) {
            return false;
        }
        if (getMessageNumber(packet) == 0) {
            return false;
        }
        var maskInterval = getMaskInterval(packet);
        if (maskInterval == 0 && packet.length != 4) {
            return false;
        }
        if (maskInterval > 0 && packet.length == 4) {
            return false;
        }
        return true;
    }

    /**
     * checks if a packet has the correct magic number set in the first 2 bytes
     *
     * @param packet the packet to check
     * @return true if the packet has the correct magic number
     */
    public static boolean hasCorrectMagicNumber(byte[] packet) {
        return packet[0] == 86 && packet[1] == 47;
    }

    /**
     * reads the message number from a packet
     *
     * @param packet a packet
     * @return the message number
     */
    public static int getMessageNumber(byte[] packet) {
        return ((packet[2] & 0xff) << 4) | (packet[3] & 0xf0) >>> 4;
    }

    /**
     * sets the message number of a packet
     *
     * @param packet a packet
     * @param number the message number to set
     * @throws IllegalArgumentException if the number is smaller than 1 or too large
     *                                  to fit into 12 bits
     */
    public static void setMessageNumber(byte[] packet, int number) {
        if (number < 1 || number > 0xfff) {
            throw new IllegalArgumentException("Message number must be a positive 12-bit number");
        }
        packet[2] = (byte) (number >>> 4);
        packet[3] &= 0x0F;
        packet[3] |= (byte) ((number & 0xF) << 4);
    }

    /**
     * reads the mask interval from a packet
     *
     * @param packet a packet
     * @return the mask interval
     */
    public static int getMaskInterval(byte[] packet) {
        var interval = 0;
        for (int i = 0; i < 4; i++) {
            if (((packet[3] >>> (3 - i)) & 1) == 1) {
                interval |= 1 << i;
            }
        }
        return interval;
    }

    /**
     * sets the mask interval in a packet
     *
     * @param packet   a packet
     * @param interval the interval to set
     */
    public static void setMaskInterval(byte[] packet, int interval) {
        packet[3] &= (byte) 0b11110000;
        for (int i = 0; i < 4; i++) {
            if (((interval >>> (3 - i)) & 1) == 1) {
                packet[3] |= (byte) (1 << i);
            }
        }
    }

    /**
     * creates a mask using the specified interval (spacing of 1s)
     *
     * @param interval the interval to use (must be between 1 and 15)
     * @return the mask
     * @throws IllegalArgumentException if interval is not between 1 and 15
     */
    public static int createMask(int interval) {
        if (interval < 1 || interval > 15) {
            throw new IllegalArgumentException("Interval must be between 1 and 15");
        }
        var mask = 0;
        for (int i = 0; i < 32; i += interval) {
            mask |= 1 << (31 - i);
        }
        return mask;
    }

    /**
     * Creates a packet, masking the message with a random mask interval
     *
     * @param messageNumber the message number of the packet
     * @param message       the message in the packet
     * @return a valid packet containing the masked message
     * @throws IllegalArgumentException if the messageNumber is invalid
     */
    public static byte[] createPacket(int messageNumber, String message) {
        var chars = message == null ? new char[0] : message.toCharArray();
        if (chars.length > 0xFFFA) {
            throw new IllegalArgumentException("Message too long, no more than 65530 characters");
        }
        var packet = new byte[4 + chars.length];
        packet[0] = 86;
        packet[1] = 47;
        setMessageNumber(packet, messageNumber);
        if (chars.length > 0) {
            var maskInterval = random.nextInt(1, 16);
            setMaskInterval(packet, maskInterval);
            var mask = createMask(maskInterval);
            for (int i = 0; i < chars.length; i++) {
                var mask_byte = mask >>> i % 4 * 8;
                var masked = chars[i] & 0xFF ^ mask_byte;
                packet[i + 4] = (byte) masked;
            }
        }
        return packet;
    }

    /**
     * Reads the message from a packet
     *
     * @param packet a valid packet
     * @return the message
     */
    public static String readMessage(byte[] packet) {
        if (packet.length == 4) {
            return "";
        }
        var mask = createMask(getMaskInterval(packet));
        var chars = new char[packet.length - 4];
        for (int i = 4; i < packet.length; i++) {
            var mask_byte = mask >> i % 4 * 8;
            var unmasked = (packet[i] ^ mask_byte) & 0xFF;
            chars[i - 4] = (char) unmasked;
        }
        return new String(chars);
    }
}
