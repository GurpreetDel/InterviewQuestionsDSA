package com.boot;

/**
 * Simple runner class to execute the Anagram main method
 */
public class AnagramRunner {
    public static void main(String[] args) {
        System.out.println("Running Anagram with default example (cat, tac):");
        Anagram.main(args);
        
        // Add more test cases
        System.out.println("\nAdditional test cases:");
        testAnagram("listen", "silent");
        testAnagram("hello", "world");
        testAnagram("anagram", "nagaram");
        testAnagram("rat", "car");
    }
    
    private static void testAnagram(String s1, String s2) {
        System.out.println("\nTesting: " + s1 + ", " + s2);
        
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
        String sortedS1 = new String(charArray1);
        String sortedS2 = new String(charArray2);
        
        // Compare sorted strings
        boolean areAnagrams = sortedS1.equals(sortedS2);
        
        // Print results
        System.out.println("Original strings: " + s1 + ", " + s2);
        System.out.println("Sorted strings: " + sortedS1 + ", " + sortedS2);
        System.out.println("Are anagrams: " + areAnagrams);
    }
    
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