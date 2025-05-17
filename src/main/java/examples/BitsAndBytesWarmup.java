/*
 * Copyright (c) 2025 Michael Hopfner
 * Questions? Let me know!
 * michael.hopfner@icloud.com
 */

package examples;

public class BitsAndBytesWarmup {

    /**
     * flips each bit of a number, i.e. 1 becomes 0 and 0 becomes 1
     */
    public static int flipBits(int number) {
        throw new UnsupportedOperationException();
    }

    /**
     * filters a number by only leaving bits on 1 if the bit at the same position in the filter is 1.
     * The opposite of sieve (see below)
     *
     * @param number the number to filter
     * @param filter the filter to apply
     * @return the filtered number
     */
    public static int filter(int number, int filter) {
        throw new UnsupportedOperationException();
    }

    /**
     * sieves a number by only leaving bits on 1 if the bit at the same position in the sieve is 0.
     * The opposite of filter (see above).
     *
     * @param number the number to sieve
     * @param sieve  the sieve to sieve the number through
     * @return the sieved number
     */
    public static int sieve(int number, int sieve) {
        throw new UnsupportedOperationException();
    }

    /**
     * masks a number by only setting bits to 1 if the bit of the number is not the same as the bit in the mask at the same position.
     *
     * @param number the number to mask
     * @param mask the mask
     * @return the masked number
     */
    public static int mask(int number, int mask) {
        throw new UnsupportedOperationException();
    }

    /**
     * packs 4 bytes left to right into an int
     * The opposite of unpack (see below)
     *
     * @param bytes the bytes to pack
     * @return an int containing the 4 bytes
     */
    public static int pack(byte[] bytes) {
        throw new UnsupportedOperationException();
    }

    /**
     * unpacks an int into an array of 4 bytes.
     * The opposite of pack (see above).
     *
     * @param number the int to unpack.
     * @return the bytes extracted from the int
     */
    public static byte[] unpack(int number) {
        throw new UnsupportedOperationException();
    }

    /**
     * rotates a number by shifting it to the right
     * and adding the bits that have been shifted out at the beginning,
     * e.g.
     * 0b00000000111111110000000011111111 rotated by an amount of 5 becomes
     * 0b11111000000001111111100000000111
     *
     * @param number the number to rotate
     * @param amount the amount of places to shift
     * @return the rotated number
     */
    public static int rotate(int number, int amount) {
        throw new UnsupportedOperationException();
    }

    /**
     * reverses or mirrors the bits of a number
     * i.e. the leftmost bit becomes the rightmost and so on
     * e.g.
     * 0b00000000111111110000000011111111 becomes
     * 0b11111111000000001111111100000000
     */
    public static int reverseBits(int number) {
        throw new UnsupportedOperationException();
    }
}
