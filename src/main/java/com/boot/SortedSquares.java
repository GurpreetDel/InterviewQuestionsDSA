package com.boot;

import java.util.Arrays;

public class SortedSquares {

    public static void main(String[] args) {
        int arr[] = {-5,-2, -1, 3, 4,6};
        int[] result = sortedSquares(arr);

        // Print the result
        System.out.println("Original array: " + Arrays.toString(arr));
        System.out.println("Sorted squares: " + Arrays.toString(result));
    }

    public static int[] sortedSquares(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];

        // Sort the input array first
        //Arrays.sort(nums);

        int smallerIdx=0;
        int largerIDx=nums.length-1;
        for (int i=nums.length-1;i>=0;i--){
            int smallerValue=nums[smallerIdx];
            int largerValue=nums[largerIDx];
            if (Math.abs(smallerValue)>Math.abs(largerValue)){
                result[i]=smallerValue*smallerValue;
                smallerIdx++;
            }
            else
            {
                result[i]=largerValue*largerValue;
                largerIDx--;
            }
        }

        return result;
    }
}
