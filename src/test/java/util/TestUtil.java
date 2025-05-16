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

    public static char[][] clone(char[][] chars){
        return Arrays.stream(chars)
                .map(char[]::clone)
                .toArray(char[][]::new);
    }
}
