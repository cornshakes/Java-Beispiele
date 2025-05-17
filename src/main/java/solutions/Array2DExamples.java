package solutions;

public class Array2DExamples {

    /**
     * rotates a 2-dimensional rectangular character grid by 90° clockwise.
     *
     * @param chars the original grid (will not be modified).
     * @return the resulting new grid.
     */
    public static char[][] rotate90Degrees(char[][] chars) {
        var result = new char[chars[0].length][];
        for (int r = 0; r < result.length; r++) {
            result[r] = new char[chars.length];
            for (int c = 0; c < chars.length; c++) {
                result[r][c] = chars[chars.length - 1 - c][r];
            }
        }
        return result;
    }

    /**
     * mirrors a 2-dimensional rectangular character grid left to right.
     *
     * @param chars the original grid (will not be modified).
     * @return the resulting new grid.
     */
    public static char[][] mirrorLeftRight(char[][] chars) {
        var result = new char[chars.length][];
        for (int r = 0; r < chars.length; r++) {
            result[r] = new char[chars[r].length];
            for (int c = 0; c < chars[r].length; c++) {
                result[r][c] = chars[r][chars[r].length - 1 - c];
            }
        }
        return result;
    }

    /**
     * mirrors a 2-dimensional rectangular character grid top to bottom.
     *
     * @param chars the original grid (will not be modified).
     * @return the resulting new grid.
     */
    public static char[][] mirrorTopBottom(char[][] chars) {
        var result = new char[chars.length][];
        for (int r = 0; r < chars.length; r++) {
            result[r] = chars[chars.length - 1 - r].clone();
        }
        return result;
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
        var width = chars[0].length + 2;
        var height = chars.length + 2;
        var result = new char[height][];
        result[0] = new char[width];
        result[height - 1] = new char[width];
        for (int i = 0; i < width; i++) {
            result[0][i] = frameChar;
            result[height - 1][i] = frameChar;
        }
        for (int i = 0; i < chars.length; i++) {
            result[i + 1] = new char[chars[i].length + 2];
            result[i + 1][0] = frameChar;
            System.arraycopy(chars[i], 0, result[i + 1], 1, chars[i].length);
            result[i + 1][result[i + 1].length - 1] = frameChar;
        }
        return result;
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
        var result = new int[picture.length][];
        for (int i = 0; i < picture.length; i++) {
            result[i] = new int[picture[0].length];
        }
        for (int chunk_row = 0; chunk_row < picture.length; chunk_row += chunkSize) {
            for (int chunk_col = 0; chunk_col < picture[0].length; chunk_col += chunkSize) {
                var avg_r = 0;
                var avg_g = 0;
                var avg_b = 0;
                var count = 0;
                for (int row = 0; row < chunkSize; row++) {
                    if (chunk_row + row < picture.length) {
                        for (int col = 0; col < chunkSize; col++) {
                            if (chunk_col + col < picture[0].length) {
                                var color = picture[chunk_row + row][chunk_col + col];
                                avg_r += color >>> 16 & 0xFF;
                                avg_g += color >>> 8 & 0xFF;
                                avg_b += color & 0xFF;
                                count += 1;
                            }
                        }
                    }
                }

                var r = avg_r / count;
                var g = avg_g / count;
                var b = avg_b / count;
                var chunk_color = (r << 16) | (g << 8) | b;

                for (int row = 0; row < chunkSize; row++) {
                    if (chunk_row + row < picture.length) {
                        for (int col = 0; col < chunkSize; col++) {
                            if (chunk_col + col < picture[0].length) {
                                result[chunk_row + row][chunk_col + col] = chunk_color;
                            }
                        }
                    }
                }
            }
        }
        return result;
    }
}
