/*
 * Copyright (c) 2025 Michael Hopfner
 * Questions? Let me know!
 * michael.hopfner@icloud.com
 */

package util;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

public class TestUtil {

    public static char[][] read2DChars(String filename) {
        try {
            return Files.readAllLines(Path.of("src/test/resources", filename))
                    .stream()
                    .map(String::toCharArray)
                    .toArray(char[][]::new);
        } catch (Exception e) {
            throw new RuntimeException("failed to read resource file '" + filename + "'");
        }
    }

    public static int[][] read2DHexInts(String filename) {
        try {
            return Files.readAllLines(Path.of("src/test/resources", filename))
                    .stream()
                    .map(str->
                        Arrays.stream(str.split(" "))
                                .mapToInt(s -> Integer.parseInt(s, 16))
                                .toArray()
                    )
                    .toArray(int[][]::new);
        } catch (Exception e) {
            throw new RuntimeException("failed to read resource file '" + filename + "'");
        }
    }

    public static char[][] clone(char[][] chars){
        return Arrays.stream(chars)
                .map(char[]::clone)
                .toArray(char[][]::new);
    }

    public static int[][] clone(int[][] ints){
        return Arrays.stream(ints)
                .map(int[]::clone)
                .toArray(int[][]::new);
    }
}
