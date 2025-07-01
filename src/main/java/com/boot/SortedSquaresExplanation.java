package com.boot;

/**
 * Explanation of the SortedSquares algorithm
 * 
 * The SortedSquares class implements an algorithm to square each element in an array
 * and return the result in sorted order. The algorithm works in O(N) time complexity
 * when the input array is already sorted.
 * 
 * Key concepts:
 * 
 * 1. Why we move from the end of the array to the start (in reverse):
 *    - When we square numbers, the largest values will be at the extremes of the array
 *      (either very negative or very positive numbers)
 *    - By starting from the end of the result array, we can place the largest squared
 *      values first and work our way down to the smallest
 *    - This approach allows us to build the result array in a single pass
 * 
 * 2. Why we compare absolute values:
 *    - The square of a number is always positive
 *    - The square of a number depends only on its absolute value
 *      (e.g., (-5)² = 25 and 5² = 25)
 *    - By comparing absolute values, we can determine which number will have
 *      the larger square
 * 
 * 3. Why the input array must be sorted:
 *    - The algorithm assumes that the smallest values (by absolute value) are in the
 *      middle of the array, and the largest values are at the extremes
 *    - This is only true if the array is sorted in ascending order
 *    - If the array is not sorted, we need to sort it first (which adds O(N log N) complexity)
 *      or use a different approach
 * 
 * Time Complexity:
 * - If the input array is already sorted: O(N)
 * - If the input array is not sorted and we sort it first: O(N log N)
 * 
 * Space Complexity:
 * - O(N) for the result array
 * 
 * Example:
 * Input (sorted): [-5, -2, -1, 3, 4, 6]
 * 
 * Step 1: Initialize pointers
 *   smallerIdx = 0 (pointing to -5)
 *   largerIdx = 5 (pointing to 6)
 * 
 * Step 2: Compare absolute values
 *   |nums[smallerIdx]| = |-5| = 5
 *   |nums[largerIdx]| = |6| = 6
 *   Since 6 > 5, we put 6² = 36 at the end of the result array
 *   and decrement largerIdx
 * 
 * Step 3: Continue the process
 *   smallerIdx = 0, largerIdx = 4
 *   |nums[smallerIdx]| = |-5| = 5
 *   |nums[largerIdx]| = |4| = 4
 *   Since 5 > 4, we put 5² = 25 at the next position in the result array
 *   and increment smallerIdx
 * 
 * ... and so on until we fill the entire result array
 * 
 * Final result: [1, 4, 9, 16, 25, 36]
 */
public class SortedSquaresExplanation {
    // This class is for explanation purposes only
}