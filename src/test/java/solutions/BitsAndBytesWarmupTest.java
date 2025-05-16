package solutions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BitsAndBytesWarmupTest {

    @Test
    void flipBits() {
        var number = 0b01010101010101010101010101010101;
        var result = 0b10101010101010101010101010101010;
        assertEquals(result, BitsAndBytesWarmup.flipBits(number));
    }

    @Test
    void filter() {
        var number = 0b01010101010101010101010101010101;
        var filter = 0b11111111111111110000000000000000;
        var result = 0b01010101010101010000000000000000;
        assertEquals(result, BitsAndBytesWarmup.filter(number, filter));
    }

    @Test
    void sieve() {
        var number = 0b10101010101010101010101010101010;
        var sieve  = 0b11111111111111110000000000000000;
        var result = 0b00000000000000001010101010101010;
        assertEquals(result, BitsAndBytesWarmup.sieve(number, sieve));
    }

    @Test
    void mask() {
        var number = 0b10101010101010101010101010101010;
        var mask   = 0b11111111111111110000000000000000;
        var result = 0b01010101010101011010101010101010;
        assertEquals(result, BitsAndBytesWarmup.mask(number, mask));
        assertEquals(number, BitsAndBytesWarmup.mask(result, mask));
        assertEquals(mask, BitsAndBytesWarmup.mask(number, result));
    }

    @Test
    void pack() {
        var number = 0b00000001000000100000001100000100;
        var bytes = new byte[] {1,2,3,4};
        assertEquals(number, BitsAndBytesWarmup.pack(bytes));
    }

    @Test
    void unpack() {
        var number = 0b00000001000000100000001100000100;
        var bytes = new byte[] {1,2,3,4};
        assertArrayEquals(bytes, BitsAndBytesWarmup.unpack(number));
    }

    @Test
    void rotate() {
        var number = 0b00000000111111110000000011111111;
        var amount = 7;
        var result = 0b11111110000000011111111000000001;
        assertEquals(result, BitsAndBytesWarmup.rotate(number, amount));
    }

    @Test
    void reverseBits() {
        var number = 0b11110000111100001010101010101010;
        var result = 0b01010101010101010000111100001111;
        assertEquals(result, BitsAndBytesWarmup.reverseBits(number));
    }
}