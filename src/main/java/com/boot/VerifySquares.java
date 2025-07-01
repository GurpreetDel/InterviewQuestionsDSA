package com.boot;

import java.util.Arrays;

public class VerifySquares {
    public static void main(String[] args) {
        int[] input = {-5, -1, -2, -4, 3};
        
        // Square each element
        int[] squared = new int[input.length];
        for (int i = 0; i < input.length; i++) {
            squared[i] = input[i] * input[i];
        }
        
        // Print the squared values
        System.out.println("Input: " + Arrays.toString(input));
        System.out.println("Squared: " + Arrays.toString(squared));
        
        // Sort the squared values
        Arrays.sort(squared);
        
        // Print the sorted squared values
        System.out.println("Sorted squares: " + Arrays.toString(squared));
    }
}