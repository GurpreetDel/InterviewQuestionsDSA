# Sorted Squares Algorithm - Deep Dive

## Project Structure
```
SortedSquares/
├── 2023-06-01/
│   ├── initial_implementation/
│   │   ├── SortedSquares.java (10:30 AM)
│   │   └── README.md (11:15 AM)
├── 2023-06-02/
│   ├── optimized_implementation/
│   │   ├── SortedSquares.java (09:45 AM)
│   │   └── test_cases.md (10:30 AM)
├── 2023-06-03/
│   ├── final_implementation/
│   │   ├── SortedSquares.java (14:20 PM)
│   │   ├── SortedSquaresTest.java (15:10 PM)
│   │   └── performance_analysis.md (16:30 PM)
└── README.md (current file)
```

## Algorithm Concept Deep Dive

### Problem Statement
Given a sorted array of integers (which may include negative numbers), return an array of the squares of each number, also in sorted order.

### Naive Approach (O(n log n))
The simplest approach would be:
1. Square each element in the array
2. Sort the resulting array

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

This approach has a time complexity of O(n log n) due to the sorting operation.

### Optimized Approach (O(n))
We can achieve O(n) time complexity by using a two-pointer technique:

```
Original array (sorted): [-5, -2, -1, 3, 4, 6]
                          ^                  ^
                     smallerIdx         largerIdx
```

#### Key Insights:
1. In a sorted array, the largest squared values will be at the extremes (either very negative or very positive)
2. We can use two pointers (one at the beginning and one at the end) to compare absolute values
3. The larger absolute value will have the larger square
4. We build the result array from the end (largest values) to the beginning (smallest values)

## Python Implementation Explained

```python
def sortedSquaredArray(array):
    # Initialize an array of zeros with the same length as the input array
    sortedSquares = [0 for _ in array]
    
    # Set pointers to the beginning and end of the array
    smallerValueIdx = 0
    largerValueIdx = len(array) - 1
    
    # Iterate through the array in reverse (from end to beginning)
    for idx in reversed(range(len(array))):
        # Get the values at the current pointers
        smallerValue = array[smallerValueIdx]
        largerValue = array[largerValueIdx]
        
        # Compare absolute values to determine which one has the larger square
        if abs(smallerValue) > abs(largerValue):
            # If the absolute value at the smaller index is larger,
            # put its square at the current position in the result array
            sortedSquares[idx] = smallerValue * smallerValue
            # Move the smaller index pointer to the right
            smallerValueIdx += 1
        else:
            # Otherwise, put the square of the larger value
            sortedSquares[idx] = largerValue * largerValue
            # Move the larger index pointer to the left
            largerValueIdx -= 1
            
    return sortedSquares
```

## Java Equivalent Implementation

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

## Visual Explanation

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

## Time and Space Complexity

### Time Complexity
- O(n) where n is the length of the input array
- We process each element exactly once

### Space Complexity
- O(n) for the result array
- We don't use any additional space that scales with the input size

## Why This Algorithm Works

1. In a sorted array, the largest values (by absolute value) are at the extremes
2. The square of a number depends only on its absolute value
3. By comparing absolute values, we can determine which number will have the larger square
4. Building the result array from the end allows us to place the largest squared values first
5. This approach ensures that the result array is sorted in ascending order

## Conclusion

The two-pointer approach for the Sorted Squares problem is an elegant example of how understanding the properties of the problem can lead to an optimal solution. By recognizing that the largest squared values will be at the extremes of a sorted array, we can avoid the need for sorting after squaring, reducing the time complexity from O(n log n) to O(n).