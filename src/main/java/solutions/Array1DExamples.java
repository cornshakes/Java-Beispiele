package solutions;

public class Array1DExamples {

    /**
     * Sums the numbers in the array
     * 
     * @param numbers the array of numbers to sum
     * @return the sum
     */
    public static int sum(int[] numbers) {
        int sum = 0;
        for (var n : numbers) {
            sum += n;
        }
        return sum;
    }

    /**
     * Returns true if the array contains the number.
     * 
     * @param numbers the array to search
     * @param n       the number to search for
     * @return true if the array contains the number, false otherwise.
     */
    public static boolean contains(int[] numbers, int n) {
        for (var number : numbers) {
            if (number == n) {
                return true;
            }
        }
        return false;
    }

    /**
     * Replaces each occurence of n in the array with the replacement and returns
     * the number of replaced occurences.
     * 
     * @param numbers     the array to search / replace numbers in
     * @param n           the number to replace
     * @param replacement the number to take the place
     * @return the number of replaced occurences
     */
    public static int replace(int[] numbers, int n, int replacement) {
        int count = 0;
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] == n) {
                numbers[i] = replacement;
                count += 1;
            }
        }
        return count;
    }

}
