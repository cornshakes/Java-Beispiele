/*
 * Copyright (c) 2025 Michael Hopfner
 * Questions? Let me know!
 * michael.hopfner@icloud.com
 */

package examples;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ImaginaryNetworkProtocolTest {

    byte[] packet_1;

    @BeforeEach
    public void setup() {
        packet_1 = new byte[] {
                // magic number / protocol identifier
                86, 47,
                // first 8 bits of 12-bit message number
                0b00000000,
                // last 4 bits of 12-bit message number, then
                // 4-bit big-endian mask interval
                0b00011000,
                // some message bytes
                0x01, 0x02
        };
    }

    @Test
    void isPacketValid() {
        assertTrue(ImaginaryNetworkProtocol.isPacketValid(new byte[] { 86, 47, 0, 0b00011000, 1, 2 }));
        // too short, too long
        assertFalse(ImaginaryNetworkProtocol.isPacketValid(new byte[0]));
        assertFalse(ImaginaryNetworkProtocol.isPacketValid(new byte[0xffff]));
        // wrong magic number
        assertFalse(ImaginaryNetworkProtocol.isPacketValid(new byte[] { 12, 47, 0, 0b00011000, 1, 2 }));
        // packet number is 0
        assertFalse(ImaginaryNetworkProtocol.isPacketValid(new byte[] { 86, 47, 0, 0b00001000, 1, 2 }));
        // mask interval is 0 even though there are message bytes
        assertFalse(ImaginaryNetworkProtocol.isPacketValid(new byte[] { 86, 47, 0, 0b00010000, 1, 2 }));
        // mask interval is non-zero even though there are no message bytes
        assertFalse(ImaginaryNetworkProtocol.isPacketValid(new byte[] { 86, 47, 0, 0b00011000 }));
    }

    @Test
    void hasCorrectMagicNumber() {
        assertTrue(ImaginaryNetworkProtocol.hasCorrectMagicNumber(packet_1));
        packet_1[1] = 46;
        assertFalse(ImaginaryNetworkProtocol.hasCorrectMagicNumber(packet_1));
    }

    @Test
    void getMessageNumber() {
        assertEquals(1, ImaginaryNetworkProtocol.getMessageNumber(packet_1));
    }

    @Test
    void setMessageNumber() {
        assertThrows(IllegalArgumentException.class, () -> ImaginaryNetworkProtocol.setMessageNumber(packet_1, -23));
        assertThrows(IllegalArgumentException.class, () -> ImaginaryNetworkProtocol.setMessageNumber(packet_1, 0));
        assertThrows(IllegalArgumentException.class, () -> ImaginaryNetworkProtocol.setMessageNumber(packet_1, 4096));

        ImaginaryNetworkProtocol.setMessageNumber(packet_1, 509);
        assertEquals(509, ImaginaryNetworkProtocol.getMessageNumber(packet_1));
        ImaginaryNetworkProtocol.setMessageNumber(packet_1, 2300);
        assertEquals(2300, ImaginaryNetworkProtocol.getMessageNumber(packet_1));
        ImaginaryNetworkProtocol.setMessageNumber(packet_1, 3999);
        assertEquals(3999, ImaginaryNetworkProtocol.getMessageNumber(packet_1));
    }

    @Test
    public void getMaskInterval() {
        assertEquals(1, ImaginaryNetworkProtocol.getMaskInterval(packet_1));
        packet_1[3] = 0b00001001;
        assertEquals(9, ImaginaryNetworkProtocol.getMaskInterval(packet_1));
        packet_1[3] = 0b00001111;
        assertEquals(15, ImaginaryNetworkProtocol.getMaskInterval(packet_1));
    }

    @Test
    public void setMaskInterval() {
        for (int i = 0; i < 16; i++) {
            ImaginaryNetworkProtocol.setMaskInterval(packet_1, i);
            assertEquals(i, ImaginaryNetworkProtocol.getMaskInterval(packet_1));
        }
    }

    @Test
    void createMask() {
        assertThrows(IllegalArgumentException.class, () -> ImaginaryNetworkProtocol.createMask(-3));
        assertThrows(IllegalArgumentException.class, () -> ImaginaryNetworkProtocol.createMask(0));
        assertThrows(IllegalArgumentException.class, () -> ImaginaryNetworkProtocol.createMask(16));
        assertThrows(IllegalArgumentException.class, () -> ImaginaryNetworkProtocol.createMask(20));

        var mask_1 = 0b11111111111111111111111111111111;
        var mask_2 = 0b10101010101010101010101010101010;
        var mask_5 = 0b10000100001000010000100001000010;
        var mask_7 = 0b10000001000000100000010000001000;
        var mask_15 = 0b10000000000000010000000000000010;

        assertEquals(mask_1, ImaginaryNetworkProtocol.createMask(1));
        assertEquals(mask_2, ImaginaryNetworkProtocol.createMask(2));
        assertEquals(mask_5, ImaginaryNetworkProtocol.createMask(5));
        assertEquals(mask_7, ImaginaryNetworkProtocol.createMask(7));
        assertEquals(mask_15, ImaginaryNetworkProtocol.createMask(15));
    }

    @Test
    void createPacket() {
        assertThrows(IllegalArgumentException.class, () -> ImaginaryNetworkProtocol.createPacket(0, "hello world!"));
        assertThrows(IllegalArgumentException.class,
                () -> ImaginaryNetworkProtocol.createPacket(0x1000, "hello world!"));

        var packet = ImaginaryNetworkProtocol.createPacket(777, "hello world!");
        assertTrue(ImaginaryNetworkProtocol.isPacketValid(packet));
        packet = ImaginaryNetworkProtocol.createPacket(788, null);
        assertTrue(ImaginaryNetworkProtocol.isPacketValid(packet));
        packet = ImaginaryNetworkProtocol.createPacket(1, "");
        assertTrue(ImaginaryNetworkProtocol.isPacketValid(packet));
    }

    @Test
    void readMessage() {
        var packet = ImaginaryNetworkProtocol.createPacket(777, "hello world!");
        var message = ImaginaryNetworkProtocol.readMessage(packet);
        assertEquals("hello world!", message);
    }

    @Test
    void packetsTooLarge() {
        var still_ok = new char[0xFFFA];
        Arrays.fill(still_ok, 'L');
        assertTrue(ImaginaryNetworkProtocol.isPacketValid(
                ImaginaryNetworkProtocol.createPacket(1, new String(still_ok))));

        var too_large = new char[0xFFFB];
        Arrays.fill(too_large, 'X');
        assertThrows(IllegalArgumentException.class,
                () -> ImaginaryNetworkProtocol.createPacket(1, new String(too_large)));
    }
}
