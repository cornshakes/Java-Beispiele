package examples;

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

    /**
     * checks if a packet is valid according to the protocol
     *
     * @param packet the packet to check
     * @return true if the packet is valid
     */
    @SuppressWarnings("RedundantIfStatement")
    public static boolean isPacketValid(byte[] packet) {
        throw new UnsupportedOperationException();
    }

    /**
     * checks if a packet has the correct magic number set in the first 2 bytes
     *
     * @param packet the packet to check
     * @return true if the packet has the correct magic number
     */
    public static boolean hasCorrectMagicNumber(byte[] packet) {
        throw new UnsupportedOperationException();
    }

    /**
     * reads the message number from a packet
     *
     * @param packet a packet
     * @return the message number
     */
    public static int getMessageNumber(byte[] packet) {
        throw new UnsupportedOperationException();
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
        throw new UnsupportedOperationException();
    }

    /**
     * reads the mask interval from a packet
     *
     * @param packet a packet
     * @return the mask interval
     */
    public static int getMaskInterval(byte[] packet) {
        throw new UnsupportedOperationException();
    }

    /**
     * sets the mask interval in a packet
     *
     * @param packet   a packet
     * @param interval the interval to set
     */
    public static void setMaskInterval(byte[] packet, int interval) {
        throw new UnsupportedOperationException();
    }

    /**
     * creates a mask using the specified interval (spacing of 1s)
     *
     * @param interval the interval to use (must be between 1 and 15)
     * @return the mask
     * @throws IllegalArgumentException if interval is not between 1 and 15
     */
    public static int createMask(int interval) {
        throw new UnsupportedOperationException();
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
        throw new UnsupportedOperationException();
    }

    /**
     * Reads the message from a packet
     *
     * @param packet a valid packet
     * @return the message
     */
    public static String readMessage(byte[] packet) {
        throw new UnsupportedOperationException();
    }
}
