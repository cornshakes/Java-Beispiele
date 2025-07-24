package solutions;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Array1DExamplesTest {

    @Test
    public void sum() {
        assertEquals(4, Array1DExamples.sum(new int[] { 2, 4, -5, 3 }));
        assertEquals(15, Array1DExamples.sum(new int[] { 1, 2, 3, 4, 5 }));
    }

    @Test
    public void contains() {
        int[] numbers = { 1, 2, 3, 4, 5 };
        assertTrue(Array1DExamples.contains(numbers, 3));
        assertFalse(Array1DExamples.contains(numbers, 7));
    }

    @Test
    public void replace() {
        int[] numbers = { 1, 1, 1, 2, 3, 4, 5, 5, 5, 1, 5, 1, 6 };
        {
            int replacedCount = Array1DExamples.replace(numbers, 1, 9);
            int[] result = { 9, 9, 9, 2, 3, 4, 5, 5, 5, 9, 5, 9, 6 };
            assertEquals(5, replacedCount);
            assertArrayEquals(numbers, result);
        }
        {
            int replacedCount = Array1DExamples.replace(numbers, 5, 8);
            int[] result = { 9, 9, 9, 2, 3, 4, 8, 8, 8, 9, 8, 9, 6 };
            assertEquals(4, replacedCount);
            assertArrayEquals(numbers, result);
        }
    }

}
