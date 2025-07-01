package com.boot;

import java.util.Arrays;

/**
 * SortedSquares Algorithm Implementation
 * 
 * This class implements an algorithm to square each element in a sorted array
 * and return the result in sorted order. The algorithm works in O(N) time complexity
 * when the input array is already sorted.
 * 
 * @author Java Developer
 * @version 1.0
 * @date July 1st, 2023
 */
public class SortedSquares {

    /**
     * Main method to demonstrate the SortedSquares algorithm
     * 
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        // Example sorted array with both negative and positive numbers
        int arr[] = {-5, -2, -1, 3, 4, 6};
        
        // Apply the sortedSquares algorithm
        int[] result = sortedSquares(arr);

        // Print the original array and the result
        System.out.println("Original array: " + Arrays.toString(arr));
        System.out.println("Sorted squares: " + Arrays.toString(result));
    }

    /**
     * Squares each element in a sorted array and returns the result in sorted order.
     * Uses a two-pointer technique to achieve O(N) time complexity.
     * 
     * @param nums The input array, assumed to be sorted in ascending order
     * @return A new array containing the squares of the input elements, sorted in ascending order
     */
    public static int[] sortedSquares(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];

        // Initialize two pointers
        int smallerIdx = 0;                // Points to the start of the array
        int largerIdx = nums.length - 1;   // Points to the end of the array
        
        // Fill the result array from the end to the beginning
        for (int i = nums.length - 1; i >= 0; i--) {
            // Get the values at both pointers
            int smallerValue = nums[smallerIdx];
            int largerValue = nums[largerIdx];
            
            // Compare absolute values to determine which one has the larger square
            if (Math.abs(smallerValue) > Math.abs(largerValue)) {
                // If the absolute value at the smaller index is larger,
                // put its square at the current position in the result array
                result[i] = smallerValue * smallerValue;
                // Move the smaller index pointer to the right
                smallerIdx++;
            } else {
                // Otherwise, put the square of the larger value
                result[i] = largerValue * largerValue;
                // Move the larger index pointer to the left
                largerIdx--;
            }
        }

        return result;
    }
    
    /**
     * Alternative implementation using the naive approach (for comparison).
     * This method has O(N log N) time complexity due to the sorting operation.
     * 
     * @param nums The input array
     * @return A new array containing the squares of the input elements, sorted in ascending order
     */
    public static int[] sortedSquaresNaive(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        
        // Square each element
        for (int i = 0; i < n; i++) {
            result[i] = nums[i] * nums[i];
        }
        
        // Sort the squared values
        Arrays.sort(result);
        
        return result;
    }
}