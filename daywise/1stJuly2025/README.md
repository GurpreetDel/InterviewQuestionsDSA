# SortedSquares Algorithm - Deep Dive (July 1st, 2023)

## Algorithm Overview

The SortedSquares algorithm efficiently squares each element in a sorted array and returns the result in sorted order. This document provides an in-depth explanation of how the algorithm works, with step-by-step illustrations and analysis.

## Problem Statement

Given a sorted array of integers (which may include negative numbers), return an array of the squares of each number, also in sorted order.

## Java Implementation

```java
public static int[] sortedSquares(int[] nums) {
    int n = nums.length;
    int[] result = new int[n];

    int smallerIdx = 0;
    int largerIdx = nums.length - 1;
    for (int i = nums.length - 1; i >= 0; i--) {
        int smallerValue = nums[smallerIdx];
        int largerValue = nums[largerIdx];
        if (Math.abs(smallerValue) > Math.abs(largerValue)) {
            result[i] = smallerValue * smallerValue;
            smallerIdx++;
        } else {
            result[i] = largerValue * largerValue;
            largerIdx--;
        }
    }

    return result;
}
```

## Key Concepts Explained

### 1. Why We Iterate from the End of the Array to the Beginning

When we square numbers in a sorted array, the largest squared values will be at the extremes of the array (either very negative or very positive numbers). By starting from the end of the result array, we can place the largest squared values first and work our way down to the smallest.

```
Original sorted array: [-5, -2, -1, 3, 4, 6]
Squared values:        [25,  4,  1, 9, 16, 36]
                        ^            ^  ^  ^
                        |            |  |  |
                        |            |  |  largest squared value
                        |            |  |
                        |            |  third largest squared value
                        |            |
                        |            fourth largest squared value
                        |
                        second largest squared value
```

Notice how the largest squared values are at the extremes of the array, not in the middle. This is why we need a special approach to sort them efficiently.

### 2. Why We Compare Absolute Values

The square of a number depends only on its absolute value. For example:
- (-5)² = 25
- (5)² = 25

By comparing absolute values, we can determine which number will have the larger square. This is crucial for our algorithm because we need to place the largest squared values at the end of our result array.

### 3. Two-Pointer Technique

We use two pointers:
- `smallerIdx`: starts at the beginning of the array (index 0)
- `largerIdx`: starts at the end of the array (index n-1)

For each position in the result array (starting from the end), we:
1. Compare the absolute values of the elements at both pointers
2. Place the larger squared value at the current position
3. Move the corresponding pointer (increment `smallerIdx` or decrement `largerIdx`)

This technique allows us to build the result array in a single pass, without having to sort it afterward.

## Step-by-Step Illustration

Let's walk through the algorithm with an example: `[-5, -2, -1, 3, 4, 6]`

### Initial State
```
Input array: [-5, -2, -1, 3, 4, 6]
              ^                  ^
         smallerIdx         largerIdx
              
Result array: [0, 0, 0, 0, 0, 0]
                              ^
                          We start here (i=5)
```

### Step 1
Compare: |-5| = 5 vs |6| = 6
6 is larger, so we put 6² = 36 at position 5 in the result array.
```
Input array: [-5, -2, -1, 3, 4, 6]
              ^               ^
         smallerIdx      largerIdx (moved left)
              
Result array: [0, 0, 0, 0, 0, 36]
                           ^
                       Next position (i=4)
```

### Step 2
Compare: |-5| = 5 vs |4| = 4
5 is larger, so we put 5² = 25 at position 4 in the result array.
```
Input array: [-5, -2, -1, 3, 4, 6]
                 ^            ^
         smallerIdx (moved)  largerIdx
              
Result array: [0, 0, 0, 0, 25, 36]
                        ^
                    Next position (i=3)
```

### Step 3
Compare: |-2| = 2 vs |4| = 4
4 is larger, so we put 4² = 16 at position 3 in the result array.
```
Input array: [-5, -2, -1, 3, 4, 6]
                 ^         ^
         smallerIdx     largerIdx (moved)
              
Result array: [0, 0, 0, 16, 25, 36]
                     ^
                 Next position (i=2)
```

### Step 4
Compare: |-2| = 2 vs |3| = 3
3 is larger, so we put 3² = 9 at position 2 in the result array.
```
Input array: [-5, -2, -1, 3, 4, 6]
                 ^      ^
         smallerIdx  largerIdx (moved)
              
Result array: [0, 0, 9, 16, 25, 36]
                  ^
              Next position (i=1)
```

### Step 5
Compare: |-2| = 2 vs |-1| = 1
2 is larger, so we put 2² = 4 at position 1 in the result array.
```
Input array: [-5, -2, -1, 3, 4, 6]
                    ^   ^
         smallerIdx (moved) largerIdx
              
Result array: [0, 4, 9, 16, 25, 36]
               ^
           Next position (i=0)
```

### Step 6
Compare: |-1| = 1 vs |-1| = 1
They're equal, so we can use either. We put 1² = 1 at position 0 in the result array.
```
Input array: [-5, -2, -1, 3, 4, 6]
                       ^ ^
                       Both pointers move
              
Result array: [1, 4, 9, 16, 25, 36]
```

## Important Assumption: Input Array Must Be Sorted

The algorithm assumes that the input array is already sorted in ascending order. This is crucial because:

1. The algorithm relies on the property that the largest squared values will be at the extremes of the array
2. This property only holds if the array is sorted
3. If the array is not sorted, we would need to sort it first (which would make the overall time complexity O(n log n))

## Time and Space Complexity Analysis

### Time Complexity
- **O(n)** where n is the length of the input array
- We process each element exactly once
- No additional sorting is needed if the input array is already sorted

### Space Complexity
- **O(n)** for the result array
- We don't use any additional space that scales with the input size

## Comparison with Naive Approach

A naive approach would be to square each element and then sort the resulting array:

```java
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
```

This approach has a time complexity of **O(n log n)** due to the sorting operation, which is less efficient than our two-pointer approach with **O(n)** time complexity.

## Why This Algorithm Works

1. In a sorted array, the largest values (by absolute value) are at the extremes
2. The square of a number depends only on its absolute value
3. By comparing absolute values, we can determine which number will have the larger square
4. Building the result array from the end allows us to place the largest squared values first
5. This approach ensures that the result array is sorted in ascending order

## Conclusion

The two-pointer approach for the Sorted Squares problem is an elegant example of how understanding the properties of the problem can lead to an optimal solution. By recognizing that the largest squared values will be at the extremes of a sorted array, we can avoid the need for sorting after squaring, reducing the time complexity from O(n log n) to O(n).