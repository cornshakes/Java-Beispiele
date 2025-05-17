/*
 * Copyright (c) 2025 Michael Hopfner
 * Questions? Let me know!
 * michael.hopfner@icloud.com
 */

package examples;

public class Array2DExamples {

    /**
     * rotates a 2-dimensional rectangular character grid by 90° clockwise.
     *
     * @param chars the original grid (will not be modified).
     * @return the resulting new grid.
     */
    public static char[][] rotate90Degrees(char[][] chars) {
        throw new UnsupportedOperationException();
    }

    /**
     * mirrors a 2-dimensional rectangular character grid left to right.
     *
     * @param chars the original grid (will not be modified).
     * @return the resulting new grid.
     */
    public static char[][] mirrorLeftRight(char[][] chars) {
        throw new UnsupportedOperationException();
    }

    /**
     * mirrors a 2-dimensional rectangular character grid top to bottom.
     *
     * @param chars the original grid (will not be modified).
     * @return the resulting new grid.
     */
    public static char[][] mirrorTopBottom(char[][] chars) {
        throw new UnsupportedOperationException();
    }

    /**
     * frames a 2-dimensional rectangular character grid with the character
     * provided.
     *
     * @param chars     the original grid (will not be modified).
     * @param frameChar the character to use in the frame
     * @return the resulting new grid.
     */
    public static char[][] frame(char[][] chars, char frameChar) {
        throw new UnsupportedOperationException();
    }

    /**
     * Blurs a picture represented as a rectangular array of integers.
     * The picture is divided into square chunks starting at the top left.
     * All the pixels in a chunk are set to the same color.
     * The color for a chunk is the average color of all the pixels in it.
     * If a chunk is cut off (smaller), the average of the remaining pixels in it is used.
     * <p>
     * <p>
     * colors are represented as RGB bytes packed into an int like this:
     * 0x00RRGGBB. To take the average of 2 colors, the R, G, and B bytes must be
     * averaged individually, rounded down to whole numbers, then packed back into an int.
     *
     * @param picture the picture to blur
     * @return the blurred picture
     */
    public static int[][] blur(int[][] picture, int chunkSize) {


        throw new UnsupportedOperationException();
    }
}
