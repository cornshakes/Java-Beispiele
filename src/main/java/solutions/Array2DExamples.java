package solutions;

public class Array2DExamples {

    /**
     * rotates a 2-dimensional square character grid by 90° clockwise.
     * @param chars the original grid (will not be modified).
     * @return the resulting new grid.
     */
    public static char[][] rotate90Degrees(char[][] chars) {
        var result = new char[chars.length][];
        for (int r = 0; r < chars.length; r++) {
            result[r] = new char[chars[r].length];
            for (int c = 0; c < chars[r].length; c++) {
                result[r][c] = chars[chars.length - 1 - c][r];
            }
        }
        return result;
    }

    /**
     * mirrors a 2-dimensional square character grid on a vertical axis through the middle
     * i.e. left to right.
     * @param chars the original grid (will not be modified).
     * @return the resulting new grid.
     */
    public static char[][] mirrorLeftRight(char[][] chars) {
        var result = new char[chars.length][];
        for (int r = 0; r < chars.length; r++) {
            result[r] = new char[chars[r].length];
            for (int c = 0; c < chars[r].length; c++) {
                result[r][c] = chars[r][chars.length-1-c];
            }
        }
        return result;
    }

    /**
     * mirrors a 2-dimensional square character grid on a horizontal axis through the middle
     * i.e. top to bottom.
     * @param chars the original grid (will not be modified).
     * @return the resulting new grid.
     */
    public static char[][] mirrorTopBottom(char[][] chars) {
        var result = new char[chars.length][];
        for (int r = 0; r < chars.length; r++) {
            result[r] = new char[chars[r].length];
            for (int c = 0; c < chars[r].length; c++) {
                result[r][c] = chars[chars.length - 1 - r][c];
            }
        }
        return result;
    }
}
