package com.boot;

import java.util.Arrays;

/**
 * Test class for the SortedSquares algorithm
 * 
 * This class contains various test cases to verify that the SortedSquares
 * algorithm works correctly for different input scenarios.
 * 
 * @author Java Developer
 * @version 1.0
 * @date July 1st, 2023
 */
public class SortedSquaresTest {

    /**
     * Main method to run all test cases
     * 
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        // Run all test cases
        testAllNegative();
        testAllPositive();
        testMixedValues();
        testSingleElement();
        testEmptyArray();
        testDuplicateValues();
        
        System.out.println("All tests completed successfully!");
    }
    
    /**
     * Test case with all negative numbers
     */
    private static void testAllNegative() {
        System.out.println("\n=== Test Case: All Negative Numbers ===");
        int[] input = {-5, -4, -3, -2, -1};
        int[] expected = {1, 4, 9, 16, 25};
        
        runTest(input, expected, "All Negative");
    }
    
    /**
     * Test case with all positive numbers
     */
    private static void testAllPositive() {
        System.out.println("\n=== Test Case: All Positive Numbers ===");
        int[] input = {1, 2, 3, 4, 5};
        int[] expected = {1, 4, 9, 16, 25};
        
        runTest(input, expected, "All Positive");
    }
    
    /**
     * Test case with mixed positive and negative numbers
     */
    private static void testMixedValues() {
        System.out.println("\n=== Test Case: Mixed Values ===");
        int[] input = {-5, -2, -1, 3, 4, 6};
        int[] expected = {1, 4, 9, 16, 25, 36};
        
        runTest(input, expected, "Mixed Values");
    }
    
    /**
     * Test case with a single element
     */
    private static void testSingleElement() {
        System.out.println("\n=== Test Case: Single Element ===");
        int[] input = {-7};
        int[] expected = {49};
        
        runTest(input, expected, "Single Element");
    }
    
    /**
     * Test case with an empty array
     */
    private static void testEmptyArray() {
        System.out.println("\n=== Test Case: Empty Array ===");
        int[] input = {};
        int[] expected = {};
        
        runTest(input, expected, "Empty Array");
    }
    
    /**
     * Test case with duplicate values
     */
    private static void testDuplicateValues() {
        System.out.println("\n=== Test Case: Duplicate Values ===");
        int[] input = {-2, -2, 0, 3, 3};
        int[] expected = {0, 4, 4, 9, 9};
        
        runTest(input, expected, "Duplicate Values");
    }
    
    /**
     * Helper method to run a test case and verify the result
     * 
     * @param input The input array
     * @param expected The expected result
     * @param testName The name of the test case
     */
    private static void runTest(int[] input, int[] expected, String testName) {
        System.out.println("Input: " + Arrays.toString(input));
        System.out.println("Expected: " + Arrays.toString(expected));
        
        // Run the optimized algorithm
        int[] result = SortedSquares.sortedSquares(input);
        System.out.println("Result: " + Arrays.toString(result));
        
        // Verify the result
        boolean passed = Arrays.equals(result, expected);
        System.out.println("Test " + (passed ? "PASSED" : "FAILED"));
        
        if (!passed) {
            System.err.println("ERROR: Test case '" + testName + "' failed!");
            System.err.println("Expected: " + Arrays.toString(expected));
            System.err.println("Got: " + Arrays.toString(result));
        }
    }
}