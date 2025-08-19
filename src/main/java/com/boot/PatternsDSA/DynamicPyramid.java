package com.boot.PatternsDSA;

import java.util.Scanner;

public class DynamicPyramid {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number of rows: ");
        int rows = scanner.nextInt();

        printPyramid(rows);
        scanner.close();
    }

    public static void printPyramid(int rows) {
        for (int i = 1; i <= rows; i++) {
            // Print leading spaces
            for (int j = 1; j <= rows - i; j++) {
                System.out.print(" ");
            }

            // Print asterisks with spaces
            for (int k = 1; k <= i; k++) {
                System.out.print("* ");
            }

            System.out.println();
        }
    }
}