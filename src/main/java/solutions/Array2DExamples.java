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
     * @param chars the original grid (will not be modified).
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
}
