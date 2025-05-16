package solutions;

import util.TestUtil;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class Array2DExamplesTest {

    @org.junit.jupiter.api.Test
    void rotate90Degrees() {
        var input = TestUtil.read2DChars("grid-001.txt");
        var original = TestUtil.clone(input);
        var expected = TestUtil.read2DChars("grid-001-rotate90Degrees.txt");
        var actual = Array2DExamples.rotate90Degrees(input);
        assertArrayEquals(input, original, "the input can not be modified");
        assertArrayEquals(expected, actual, "the output was not as expected");
    }

    @org.junit.jupiter.api.Test
    void mirrorLeftRight() {
        var input = TestUtil.read2DChars("grid-001.txt");
        var original = TestUtil.clone(input);
        var expected = TestUtil.read2DChars("grid-001-mirrorLeftRight.txt");
        var actual = Array2DExamples.mirrorLeftRight(input);
        assertArrayEquals(input, original, "the input can not be modified");
        assertArrayEquals(expected, actual, "the output was not as expected");
    }

    @org.junit.jupiter.api.Test
    void mirrorTopBottom() {
        var input = TestUtil.read2DChars("grid-001.txt");
        var original = TestUtil.clone(input);
        var expected = TestUtil.read2DChars("grid-001-mirrorTopBottom.txt");
        var actual = Array2DExamples.mirrorTopBottom(input);
        assertArrayEquals(input, original, "the input can not be modified");
        assertArrayEquals(expected, actual, "the output was not as expected");
    }

    @org.junit.jupiter.api.Test
    void frame_grid001() {
        var input = TestUtil.read2DChars("grid-001.txt");
        var original = TestUtil.clone(input);
        var expected = TestUtil.read2DChars("grid-001-frame.txt");
        var actual = Array2DExamples.frame(input, '*');
        assertArrayEquals(input, original, "the input can not be modified");
        assertArrayEquals(expected, actual, "the output was not as expected");
    }

    @org.junit.jupiter.api.Test
    void frame_grid002() {
        var input = TestUtil.read2DChars("grid-002.txt");
        var original = TestUtil.clone(input);
        var expected = TestUtil.read2DChars("grid-002-frame.txt");
        var actual = Array2DExamples.frame(input, '#');
        assertArrayEquals(input, original, "the input can not be modified");
        assertArrayEquals(expected, actual, "the output was not as expected");
    }
}