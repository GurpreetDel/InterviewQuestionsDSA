package com.boot.java8Programs;

import java.util.List;
import java.util.stream.Stream;

/**
 * This class demonstrates how to generate Fibonacci series using Java 8 Streams.
 * The Fibonacci sequence is a series where each number is the sum of the two preceding ones,
 * usually starting with 0 and 1. Example: 0, 1, 1, 2, 3, 5, 8, 13, 21, 34, ...
 */
public class FibonacciSeriesUsingJava8
{
    public static void main(String[] args) {
        int n = 10; // Number of Fibonacci numbers to generate

        // Step 1: Generate the Fibonacci sequence using Stream.iterate
        List<Integer> fibonacciSeries = 
                Stream.iterate(
                        // Initial seed: We start with an array containing the first two Fibonacci numbers [0,1]
                        new int[]{0, 1},

                        // Lambda function that generates the next state from the current state
                        // f is the current state (an array with two elements)
                        // We return a new array where:
                        // - First element is the second element of the current state
                        // - Second element is the sum of both elements in the current state
                        f -> new int[]{f[1], f[0] + f[1]}
                ) 
                // Step 2: Limit the stream to generate only n elements
                // This ensures we only generate the first n Fibonacci numbers
                .limit(n)

                // Step 3: Map the array to just the first element
                // For each state array [a,b], we only want to keep 'a' as part of our sequence
                // This extracts the actual Fibonacci number from each state
                .map(f -> f[0])

                // Step 4: Collect the results into a List
                .toList();

        // Print the generated Fibonacci series
        System.out.println("Fibonacci Series (first " + n + " numbers):");
        System.out.println(fibonacciSeries);

        // Explanation of the approach:
        System.out.println("\nDetailed explanation of the approach:");
        System.out.println("1. We use Stream.iterate to create an infinite stream of states.");
        System.out.println("2. Each state is an array of two integers [a,b] representing consecutive Fibonacci numbers.");
        System.out.println("3. The initial state is [0,1] - the first two numbers in the Fibonacci sequence.");
        System.out.println("4. For each state [a,b], the next state is [b, a+b], following the Fibonacci rule.");
        System.out.println("5. We limit the stream to 'n' elements to get only the first 'n' states.");
        System.out.println("6. We map each state [a,b] to just 'a' to get the actual Fibonacci number.");
        System.out.println("7. Finally, we collect all numbers into a List.");

        // Example of how the states evolve:
        System.out.println("\nEvolution of states:");
        System.out.println("Initial state: [0,1]");
        System.out.println("Next state: [1,1] (from [0,1] -> [1, 0+1])");
        System.out.println("Next state: [1,2] (from [1,1] -> [1, 1+1])");
        System.out.println("Next state: [2,3] (from [1,2] -> [2, 1+2])");
        System.out.println("And so on...");
    }
}
