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
}
