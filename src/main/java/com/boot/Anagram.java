package com.boot;

import java.util.Arrays;

/**
 * Anagram checker using natural sorting without built-in sort functions
 */
public class Anagram
{
    public static void main( String[] args )
    {
        String s1 = "cat";
        String s2 = "tac";

        // Check if the strings have the same length
        if (s1.length() != s2.length()) {
            System.out.println("Not anagrams: Different lengths");
            return;
        }

        // Convert strings to char arrays for sorting
        char[] charArray1 = s1.toCharArray();
        char[] charArray2 = s2.toCharArray();

        // Sort both char arrays using bubble sort
        sortCharArray(charArray1);
        sortCharArray(charArray2);

        // Convert sorted char arrays back to strings
        // Method 1: Using new String(charArray)
        String sortedS1 = new String(charArray1);
        String sortedS2 = new String(charArray2);

        // Method 2: Using String.valueOf(charArray) - commented out as we're using Method 1
        // String sortedS1 = String.valueOf(charArray1);
        // String sortedS2 = String.valueOf(charArray2);

        // Method 3: Using Arrays.toString(charArray) - commented out as it's not suitable for anagram checking
        // String sortedS1 = Arrays.toString(charArray1);
        // String sortedS2 = Arrays.toString(charArray2);

        // Compare sorted strings
        boolean areAnagrams = sortedS1.equals(sortedS2);

        // Print results
        System.out.println("Original strings: " + s1 + ", " + s2);
        System.out.println("Sorted strings: " + sortedS1 + ", " + sortedS2);
        System.out.println("Are anagrams: " + areAnagrams);
    }

    /**
     * Sorts a character array using bubble sort algorithm
     * @param charArray The character array to sort
     */
    private static void sortCharArray(char[] charArray) {
        for (int i = 0; i < charArray.length; i++) {
            for (int j = i + 1; j < charArray.length; j++) {
                if (charArray[i] > charArray[j]) {
                    // Swap characters
                    char temp = charArray[i];
                    charArray[i] = charArray[j];
                    charArray[j] = temp;
                }
            }
        }
    }
}
