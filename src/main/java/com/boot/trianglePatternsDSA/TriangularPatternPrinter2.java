package com.boot.trianglePatternsDSA;

public class TriangularPatternPrinter {

    public static void main(String[] args) {
        int n = 5; // Change this value to create different sized patterns
        printTriangularPatternDirect(n);
        printCompactTriangularPattern(n);
        System.out.println("\n" + "=".repeat(30) + "\n");

        // Example with n=3
        System.out.println("Pattern with n=3:");
        printTriangularPattern(3);

        System.out.println("\n" + "=".repeat(30) + "\n");

        // Example with n=7
        System.out.println("Pattern with n=7:");
        printTriangularPattern(7);
    }

    public static void printTriangularPattern(int n) {
        int totalWidth = 2 * n - 1; // Total width of the pattern

        for (int i = 0; i < n; i++) {
            // Create array to store the pattern for current row
            char[] row = new char[totalWidth];

            // Fill with dashes first
            for (int j = 0; j < totalWidth; j++) {
                row[j] = '_';
            }

            // Place asterisks at correct positions
            // For row i: asterisks at positions (n-1-i), (n-1-i+2), (n-1-i+4), ..., (n-1+i)
            for (int k = 0; k <= i; k++) {
                int position = (n - 1 - i) + (2 * k);
                if (position >= 0 && position < totalWidth) {
                    row[position] = '*';
                }
            }

            // Print the row with spaces between characters
            for (int j = 0; j < totalWidth; j++) {
                System.out.print(row[j]);
                if (j < totalWidth - 1) {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

    // Alternative method without using array
    public static void printTriangularPatternDirect(int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 2 * n - 1; j++) {
                // Check if current position should have an asterisk
                boolean shouldPrintAsterisk = false;

                // For row i, asterisks are at positions: (n-1-i), (n-1-i+2), (n-1-i+4), ..., (n-1+i)
                for (int k = 0; k <= i; k++) {
                    int asteriskPosition = (n - 1 - i) + (2 * k);
                    if (j == asteriskPosition) {
                        shouldPrintAsterisk = true;
                        break;
                    }
                }

                if (shouldPrintAsterisk) {
                    System.out.print("*");
                } else {
                    System.out.print(" ");
                }

                // Add space between characters (except last character)
                if (j < 2 * n - 2) {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

    // Method to print pattern without spaces between characters
    public static void printCompactTriangularPattern(int n) {
        System.out.println("Compact version (no spaces):");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 2 * n - 1; j++) {
                boolean shouldPrintAsterisk = false;

                for (int k = 0; k <= i; k++) {
                    int asteriskPosition = (n - 1 - i) + (2 * k);
                    if (j == asteriskPosition) {
                        shouldPrintAsterisk = true;
                        break;
                    }
                }

                System.out.print(shouldPrintAsterisk ? "*" : "  ");
            }
            System.out.println();
        }
    }
}