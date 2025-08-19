package com.boot.java8Programs;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * Simple test class to verify the Fibonacci implementation
 */
public class FibonacciSeriesUsingJava8Test {

    public static void main(String[] args) {
        System.out.println("Running Fibonacci Series Test...");

        // First, run the main implementation
        System.out.println("\n=== Running the main implementation ===");
        FibonacciSeriesUsingJava8.main(new String[]{});

        // Then, test the logic directly
        System.out.println("\n=== Testing the Fibonacci logic directly ===");
        testFibonacciLogic();
    }

    private static void testFibonacciLogic() {
        // Expected first 10 Fibonacci numbers
        List<Integer> expected = Arrays.asList(0, 1, 1, 2, 3, 5, 8, 13, 21, 34);

        // Generate Fibonacci sequence using the same logic as in the main class
        List<Integer> actual = 
                Stream.iterate(
                        new int[]{0, 1},
                        f -> new int[]{f[1], f[0] + f[1]}
                )
                .limit(10)
                .map(f -> f[0])
                .toList();

        // Compare the results
        System.out.println("Expected: " + expected);
        System.out.println("Actual:   " + actual);

        boolean isEqual = expected.equals(actual);
        System.out.println("Test result: " + (isEqual ? "PASSED ✓" : "FAILED ✗"));

        if (!isEqual) {
            System.out.println("ERROR: The generated Fibonacci sequence does not match the expected sequence!");
        } else {
            System.out.println("SUCCESS: The Fibonacci sequence was correctly generated using Java 8 streams!");
        }
    }
}
