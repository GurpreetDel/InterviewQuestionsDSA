# SortedSquares Algorithm Visualization

This document provides a visual representation of how the SortedSquares algorithm works, using ASCII art to illustrate the step-by-step execution.

## Algorithm Flow Diagram

```
┌─────────────────────────────────────────────────────────────┐
│                                                             │
│  Input: Sorted Array [-5, -2, -1, 3, 4, 6]                  │
│                                                             │
└───────────────────────────┬─────────────────────────────────┘
                            │
                            ▼
┌─────────────────────────────────────────────────────────────┐
│                                                             │
│  Initialize:                                                │
│  - smallerIdx = 0 (pointing to -5)                          │
│  - largerIdx = 5 (pointing to 6)                            │
│  - result = [0, 0, 0, 0, 0, 0]                              │
│                                                             │
└───────────────────────────┬─────────────────────────────────┘
                            │
                            ▼
┌─────────────────────────────────────────────────────────────┐
│                                                             │
│  Iteration 1 (i = 5):                                       │
│  - Compare: |-5| = 5 vs |6| = 6                             │
│  - 6 is larger, so result[5] = 6² = 36                      │
│  - Decrement largerIdx to 4                                 │
│                                                             │
└───────────────────────────┬─────────────────────────────────┘
                            │
                            ▼
┌─────────────────────────────────────────────────────────────┐
│                                                             │
│  Iteration 2 (i = 4):                                       │
│  - Compare: |-5| = 5 vs |4| = 4                             │
│  - 5 is larger, so result[4] = (-5)² = 25                   │
│  - Increment smallerIdx to 1                                │
│                                                             │
└───────────────────────────┬─────────────────────────────────┘
                            │
                            ▼
┌─────────────────────────────────────────────────────────────┐
│                                                             │
│  Iteration 3 (i = 3):                                       │
│  - Compare: |-2| = 2 vs |4| = 4                             │
│  - 4 is larger, so result[3] = 4² = 16                      │
│  - Decrement largerIdx to 3                                 │
│                                                             │
└───────────────────────────┬─────────────────────────────────┘
                            │
                            ▼
┌─────────────────────────────────────────────────────────────┐
│                                                             │
│  Iteration 4 (i = 2):                                       │
│  - Compare: |-2| = 2 vs |3| = 3                             │
│  - 3 is larger, so result[2] = 3² = 9                       │
│  - Decrement largerIdx to 2                                 │
│                                                             │
└───────────────────────────┬─────────────────────────────────┘
                            │
                            ▼
┌─────────────────────────────────────────────────────────────┐
│                                                             │
│  Iteration 5 (i = 1):                                       │
│  - Compare: |-2| = 2 vs |-1| = 1                            │
│  - 2 is larger, so result[1] = (-2)² = 4                    │
│  - Increment smallerIdx to 2                                │
│                                                             │
└───────────────────────────┬─────────────────────────────────┘
                            │
                            ▼
┌─────────────────────────────────────────────────────────────┐
│                                                             │
│  Iteration 6 (i = 0):                                       │
│  - Compare: |-1| = 1 vs |-1| = 1                            │
│  - They're equal, so result[0] = (-1)² = 1                  │
│  - (We could use either value)                              │
│                                                             │
└───────────────────────────┬─────────────────────────────────┘
                            │
                            ▼
┌─────────────────────────────────────────────────────────────┐
│                                                             │
│  Final Result: [1, 4, 9, 16, 25, 36]                        │
│                                                             │
└─────────────────────────────────────────────────────────────┘
```

## Array State Visualization

This visualization shows how the array state changes during each iteration:

### Initial State
```
Input array:  [-5, -2, -1,  3,  4,  6]
               ^                    ^
          smallerIdx           largerIdx
              
Result array:  [0,  0,  0,  0,  0,  0]
                                    ^
                                We start here (i=5)
```

### After Iteration 1
```
Input array:  [-5, -2, -1,  3,  4,  6]
               ^                 ^
          smallerIdx        largerIdx (moved)
              
Result array:  [0,  0,  0,  0,  0, 36]
                               ^
                           Next position (i=4)
```

### After Iteration 2
```
Input array:  [-5, -2, -1,  3,  4,  6]
                   ^              ^
          smallerIdx (moved)  largerIdx
              
Result array:  [0,  0,  0,  0, 25, 36]
                            ^
                        Next position (i=3)
```

### After Iteration 3
```
Input array:  [-5, -2, -1,  3,  4,  6]
                   ^           ^
          smallerIdx       largerIdx (moved)
              
Result array:  [0,  0,  0, 16, 25, 36]
                         ^
                     Next position (i=2)
```

### After Iteration 4
```
Input array:  [-5, -2, -1,  3,  4,  6]
                   ^        ^
          smallerIdx    largerIdx (moved)
              
Result array:  [0,  0,  9, 16, 25, 36]
                      ^
                  Next position (i=1)
```

### After Iteration 5
```
Input array:  [-5, -2, -1,  3,  4,  6]
                      ^     ^
          smallerIdx (moved) largerIdx
              
Result array:  [0,  4,  9, 16, 25, 36]
                   ^
               Next position (i=0)
```

### After Iteration 6 (Final State)
```
Input array:  [-5, -2, -1,  3,  4,  6]
                         ^  ^
                         Both pointers move
              
Result array:  [1,  4,  9, 16, 25, 36]
```

## Memory Access Pattern

This diagram shows which elements are accessed during each iteration:

```
Iteration 1: [-5, --, --, --, --, 6]  → result[5] = 36
Iteration 2: [-5, --, --, --, 4, --]  → result[4] = 25
Iteration 3: [--, -2, --, --, 4, --]  → result[3] = 16
Iteration 4: [--, -2, --, 3, --, --]  → result[2] = 9
Iteration 5: [--, -2, -1, --, --, --] → result[1] = 4
Iteration 6: [--, --, -1, --, --, --] → result[0] = 1
```

This visualization demonstrates how the algorithm efficiently processes the array in a single pass, comparing elements from both ends and placing the larger squared values at the appropriate positions in the result array.