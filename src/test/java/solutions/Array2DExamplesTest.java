package solutions;

import util.TestUtil;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class Array2DExamplesTest {

    @ParameterizedTest
    @CsvSource({
            "grid-001.txt, grid-001-rotate90Degrees.txt",
            "grid-002.txt, grid-002-rotate90Degrees.txt"
    })
    void rotate90Degrees(String inputFile, String expectedFile) {
        var input = TestUtil.read2DChars(inputFile);
        var original = TestUtil.clone(input);
        var expected = TestUtil.read2DChars(expectedFile);
        var actual = Array2DExamples.rotate90Degrees(input);
        assertArrayEquals(input, original, "the input can not be modified");
        assertArrayEquals(expected, actual, "the output was not as expected");
    }

    @ParameterizedTest
    @CsvSource({
            "grid-001.txt, grid-001-mirrorLeftRight.txt",
            "grid-002.txt, grid-002-mirrorLeftRight.txt"
    })
    void mirrorLeftRight(String inputFile, String expectedFile) {
        var input = TestUtil.read2DChars(inputFile);
        var original = TestUtil.clone(input);
        var expected = TestUtil.read2DChars(expectedFile);
        var actual = Array2DExamples.mirrorLeftRight(input);
        assertArrayEquals(input, original, "the input can not be modified");
        assertArrayEquals(expected, actual, "the output was not as expected");
    }

    @ParameterizedTest
    @CsvSource({
            "grid-001.txt, grid-001-mirrorTopBottom.txt",
            "grid-002.txt, grid-002-mirrorTopBottom.txt"
    })
    void mirrorTopBottom(String inputFile, String expectedFile) {
        var input = TestUtil.read2DChars(inputFile);
        var original = TestUtil.clone(input);
        var expected = TestUtil.read2DChars(expectedFile);
        var actual = Array2DExamples.mirrorTopBottom(input);
        assertArrayEquals(input, original, "the input can not be modified");
        assertArrayEquals(expected, actual, "the output was not as expected");
    }

    @ParameterizedTest
    @CsvSource({
            "grid-001.txt, grid-001-frame.txt",
            "grid-002.txt, grid-002-frame.txt"
    })
    void frame(String inputFile, String expectedFile) {
        var input = TestUtil.read2DChars(inputFile);
        var original = TestUtil.clone(input);
        var expected = TestUtil.read2DChars(expectedFile);
        var actual = Array2DExamples.frame(input, '*');
        assertArrayEquals(input, original, "the input can not be modified");
        assertArrayEquals(expected, actual, "the output was not as expected");
    }
}