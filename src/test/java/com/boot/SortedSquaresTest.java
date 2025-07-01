package com.boot;

import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.Arrays;

public class SortedSquaresTest {
    
    @Test
    public void testSortedSquares() {
        int[] input = {-1, -2, -4, 6};
        int[] expected = {1, 4, 16, 36};
        
        int[] result = SortedSquares.sortedSquares(input);
        
        System.out.println("[DEBUG_LOG] Input: " + Arrays.toString(input));
        System.out.println("[DEBUG_LOG] Expected: " + Arrays.toString(expected));
        System.out.println("[DEBUG_LOG] Result: " + Arrays.toString(result));
        
        Assert.assertEquals(result, expected);
    }
    
    @Test
    public void testSortedSquaresWithExample() {
        int[] input = {-5, -1, -2, -4, 3};
        int[] expected = {1, 4, 9, 16, 25};
        
        int[] result = SortedSquares.sortedSquares(input);
        
        System.out.println("[DEBUG_LOG] Input: " + Arrays.toString(input));
        System.out.println("[DEBUG_LOG] Expected: " + Arrays.toString(expected));
        System.out.println("[DEBUG_LOG] Result: " + Arrays.toString(result));
        
        Assert.assertEquals(result, expected);
    }
}