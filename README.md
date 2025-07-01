# Java String and Array Conversions Guide and Selenium XPath Demo with TestNG

This guide provides an in-depth explanation of different methods for converting between strings and arrays in Java, with a focus on when to use each approach. It also includes a demonstration of XPath axes and CSS selectors using Selenium WebDriver, and advanced testing techniques with TestNG.

## Table of Contents
- [String to Array Conversions](#string-to-array-conversions)
- [Array to String Conversions](#array-to-string-conversions)
  - [new String(charArray)](#1-new-stringchararray)
  - [String.valueOf(charArray)](#2-stringvalueofchararray)
  - [Arrays.toString(charArray)](#3-arraystostring)
- [String.join() Method](#stringjoin-method)
  - [Basic Syntax and Parameters](#basic-syntax-and-parameters)
  - [Examples with Different Data Structures](#examples-with-different-data-structures)
  - [Comparison with Other Methods](#comparison-with-other-methods)
  - [Best Practices and Use Cases](#best-practices-and-use-cases)
- [String Literals vs. new String()](#string-literals-vs-new-string)
  - [String Pool in Java](#string-pool-in-java)
  - [Memory Allocation](#memory-allocation)
  - [String Comparison](#string-comparison)
  - [Performance Considerations](#performance-considerations)
  - [Common Interview Questions](#common-interview-questions)
- [Visual Comparison](#visual-comparison)
- [When to Use Each Method](#when-to-use-each-method)
- [Examples](#examples)
- [XPath and CSS Selector Demonstration](#xpath-and-css-selector-demonstration)
  - [Overview](#overview)
  - [XPath Axes Explained](#xpath-axes-explained)
  - [CSS Selectors](#css-selectors)
  - [Running the Demo](#running-the-demo)
- [TestNG Advanced Testing Features](#testng-advanced-testing-features)
  - [Parallel Test Execution](#parallel-test-execution)
  - [Test Groups and Filtering](#test-groups-and-filtering)
  - [Test Prioritization](#test-prioritization)
  - [XML Parameter Passing](#xml-parameter-passing)
  - [Data-Driven Testing with XML](#data-driven-testing-with-xml)
  - [Running TestNG Tests](#running-testng-tests)

## String to Array Conversions

### 1. `toCharArray()`
Converts a string to a character array.

```java
String str = "Hello";
char[] charArray = str.toCharArray(); // ['H', 'e', 'l', 'l', 'o']
```

**When to use**: When you need to manipulate individual characters, such as for sorting, counting, or replacing characters.

### 2. `split(String regex)`
Splits a string into an array of substrings based on a delimiter.

```java
String str = "apple,banana,orange";
String[] fruits = str.split(","); // ["apple", "banana", "orange"]
```

**When to use**: When you need to break a string into parts based on a specific delimiter.

### 3. `getBytes()`
Converts a string to a byte array using the platform's default charset.

```java
String str = "Hello";
byte[] bytes = str.getBytes(); // [72, 101, 108, 108, 111]
```

**When to use**: When working with binary data, file I/O, or network communications.

## Array to String Conversions

### 1. `new String(charArray)`

Creates a new String object from a character array.

```java
char[] charArray = {'H', 'e', 'l', 'l', 'o'};
String str = new String(charArray); // "Hello"
```

**Output**: `Hello`

**Behavior**:
- Creates a new String object that contains the same sequence of characters as the char array
- The resulting string is exactly the characters in the array, with no additional formatting
- Allocates new memory for the String object

### 2. `String.valueOf(charArray)`

Converts a character array to a String using a static method.

```java
char[] charArray = {'H', 'e', 'l', 'l', 'o'};
String str = String.valueOf(charArray); // "Hello"
```

**Output**: `Hello`

**Behavior**:
- Creates a new String that represents the character sequence in the array
- Internally calls `new String(charArray)` in most Java implementations
- Considered slightly more readable and is the preferred approach in modern Java

### 3. `Arrays.toString()`

Converts an array to a String representation that includes brackets and commas.

```java
char[] charArray = {'H', 'e', 'l', 'l', 'o'};
String str = Arrays.toString(charArray); // "[H, e, l, l, o]"
```

**Output**: `[H, e, l, l, o]`

**Behavior**:
- Returns a string representation of the array including brackets `[]` and commas separating elements
- Useful for debugging or displaying the array contents
- Not suitable for converting a char array back to its original string form
- Works with any type of array, not just char arrays

## Visual Comparison

Here's a visual representation of the three methods for converting a char array to a string:

```
Original char array: ['c', 'a', 't']

Method 1: new String(charArray)
┌─────────────────┐     ┌─────────┐
│ char[] charArray │ ──> │ "cat"   │
└─────────────────┘     └─────────┘

Method 2: String.valueOf(charArray)
┌─────────────────┐     ┌─────────┐
│ char[] charArray │ ──> │ "cat"   │
└─────────────────┘     └─────────┘

Method 3: Arrays.toString(charArray)
┌─────────────────┐     ┌───────────────┐
│ char[] charArray │ ──> │ "[c, a, t]"   │
└─────────────────┘     └───────────────┘
```

As you can see, Methods 1 and 2 produce identical output suitable for normal string operations, while Method 3 includes formatting characters (brackets and commas) that make it more suitable for display purposes.

### Visualization of String to Array and Array to String Conversions

```
┌───────────────────────────────────────────────────────────────┐
│                                                               │
│  String to Array                     Array to String          │
│  ───────────────                     ───────────────          │
│                                                               │
│  "Hello"                             ['H','e','l','l','o']    │
│     │                                        │                │
│     │ toCharArray()                          │ new String()   │
│     ▼                                        ▼                │
│  ['H','e','l','l','o']               "Hello"                  │
│                                                               │
│  "apple,banana,orange"               ['H','e','l','l','o']    │
│     │                                        │                │
│     │ split(",")                             │ String.valueOf()│
│     ▼                                        ▼                │
│  ["apple","banana","orange"]         "Hello"                  │
│                                                               │
│  "Hello"                             ['H','e','l','l','o']    │
│     │                                        │                │
│     │ getBytes()                             │ Arrays.toString()│
│     ▼                                        ▼                │
│  [72,101,108,108,111]                "[H, e, l, l, o]"       │
│                                                               │
└───────────────────────────────────────────────────────────────┘
```

## When to Use Each Method

### Use `new String(charArray)` when:
- You need to explicitly create a new String object
- You're working in older Java code bases
- You need to specify a character encoding (using overloaded constructors)

### Use `String.valueOf(charArray)` when:
- You want more readable code
- You're working with modern Java code
- You need a null-safe operation (returns "null" for null input)

### Use `Arrays.toString(array)` when:
- You need to debug or display the contents of an array
- You want to include the array structure (brackets and commas) in the output
- You're working with non-char arrays (int[], Object[], etc.)
- You need a consistent string representation of any array type

## Examples

### Example 1: Anagram Checker
When checking if two strings are anagrams (like in our Anagram.java), we need to compare the actual characters:

```java
char[] charArray1 = "cat".toCharArray();
char[] charArray2 = "tac".toCharArray();

// Sort both arrays
sortCharArray(charArray1);
sortCharArray(charArray2);

// CORRECT: Use new String() or valueOf() for character-by-character comparison
String sorted1 = new String(charArray1);  // "act"
String sorted2 = new String(charArray2);  // "act"
boolean areAnagrams = sorted1.equals(sorted2);  // true

// INCORRECT: Using Arrays.toString() adds formatting characters
String formatted1 = Arrays.toString(charArray1);  // "[a, c, t]"
String formatted2 = Arrays.toString(charArray2);  // "[a, c, t]"
// This still works but is inefficient and confusing
```

### Example 2: Displaying Array Contents
When you want to show the contents of an array for debugging:

```java
int[] numbers = {1, 2, 3, 4, 5};
// CORRECT: Use Arrays.toString() for readable output
System.out.println(Arrays.toString(numbers));  // "[1, 2, 3, 4, 5]"

// INCORRECT: This won't work for non-char arrays
// System.out.println(new String(numbers));  // Compilation error
```

### Example 3: Building a String from Characters
When constructing a string from individual characters:

```java
char[] greeting = {'H', 'e', 'l', 'l', 'o'};
// Both of these are correct and equivalent
String str1 = new String(greeting);
String str2 = String.valueOf(greeting);
System.out.println(str1);  // "Hello"
System.out.println(str2);  // "Hello"
```

## String.join() Method

The `String.join()` method is a powerful utility introduced in Java 8 that allows you to join multiple strings together with a specified delimiter. It provides a clean and efficient way to concatenate strings from arrays, collections, or other iterable sources.

### Basic Syntax and Parameters

`String.join()` has two overloaded versions:

1. **Join with delimiter and elements**:
   ```java
   public static String join(CharSequence delimiter, CharSequence... elements)
   ```

2. **Join with delimiter and iterable elements**:
   ```java
   public static String join(CharSequence delimiter, Iterable<? extends CharSequence> elements)
   ```

**Parameters**:
- `delimiter`: The sequence of characters to be used between each element
- `elements`: The elements to join together (either as varargs or an Iterable)

**Return Value**:
- A new String that is composed of the elements joined together with the delimiter between each element

### Examples with Different Data Structures

#### 1. Joining String Array Elements

```java
String[] fruits = {"Apple", "Banana", "Cherry"};
String result = String.join(", ", fruits);
System.out.println(result);  // Output: "Apple, Banana, Cherry"
```

#### 2. Joining Collection Elements (List)

```java
List<String> colors = Arrays.asList("Red", "Green", "Blue");
String result = String.join(" - ", colors);
System.out.println(result);  // Output: "Red - Green - Blue"
```

#### 3. Joining Set Elements (HashSet)

```java
HashSet<String> uniqueWords = new HashSet<>();
uniqueWords.add("Hello");
uniqueWords.add("baby");
String result = String.join(" ", uniqueWords);
System.out.println(result);  // Output: "Hello baby" (order may vary)
```

#### 4. Joining Individual Strings (Varargs)

```java
String joined = String.join("/", "2023", "05", "15");
System.out.println(joined);  // Output: "2023/05/15"
```

### Visual Representation of String.join()

```
┌─────────────────────────────────────────────────────────────┐
│                                                             │
│  Input Collection/Array         Delimiter        Output     │
│  ─────────────────────          ─────────       ──────     │
│                                                             │
│  ["Apple","Banana","Cherry"]       ", "      "Apple, Banana, Cherry"
│         │                           │                │      │
│         └───────────┬───────────────┘                │      │
│                     │                                │      │
│                     │       String.join()            │      │
│                     └────────────────────────────────┘      │
│                                                             │
│  ["Red","Green","Blue"]            " - "     "Red - Green - Blue"
│         │                           │                │      │
│         └───────────┬───────────────┘                │      │
│                     │                                │      │
│                     │       String.join()            │      │
│                     └────────────────────────────────┘      │
│                                                             │
│  ["Hello","baby"]                  " "         "Hello baby" │
│         │                           │                │      │
│         └───────────┬───────────────┘                │      │
│                     │                                │      │
│                     │       String.join()            │      │
│                     └────────────────────────────────┘      │
│                                                             │
└─────────────────────────────────────────────────────────────┘
```

### Comparison with Other Methods

#### String.join() vs StringBuilder with loop

```java
// Using String.join()
String[] words = {"Java", "is", "awesome"};
String joinedString = String.join(" ", words);  // "Java is awesome"

// Using StringBuilder with loop
StringBuilder sb = new StringBuilder();
for (int i = 0; i < words.length; i++) {
    sb.append(words[i]);
    if (i < words.length - 1) {
        sb.append(" ");
    }
}
String manualJoin = sb.toString();  // "Java is awesome"
```

#### String.join() vs String concatenation with +

```java
// Using String.join()
String[] parts = {"2023", "05", "15"};
String date = String.join("-", parts);  // "2023-05-15"

// Using String concatenation
String dateConcat = parts[0] + "-" + parts[1] + "-" + parts[2];  // "2023-05-15"
```

### Best Practices and Use Cases

#### When to Use String.join()

- **Joining elements with a consistent delimiter**: Perfect for creating CSV lines, path strings, or any delimited text
- **Working with collections**: Efficiently converts collections to strings without manual iteration
- **Building formatted strings**: Creates readable output from data collections
- **Creating SQL IN clauses**: Useful for dynamically building query parameters
- **Formatting display data**: Ideal for user-facing output that needs consistent formatting

#### Performance Considerations

- **Efficiency**: String.join() is optimized for joining multiple strings and is generally more efficient than manual concatenation
- **Memory usage**: Creates fewer intermediate String objects compared to using the + operator in loops
- **Large collections**: For very large collections, consider using a StringBuilder directly if you need more control

#### Example: Real-world Usage in App.java

```java
String s = "Hello baby Hello baby";
String[] words = s.split(" ");
HashSet<String> hs = new HashSet<>();

for (String word : words) {
    hs.add(word);
}

// Before: [Hello, baby]
System.out.println(hs);

// After: "Hello baby" (using String.join)
String result = String.join(" ", hs);
System.out.println(result);
```

This example demonstrates how String.join() can be used to convert a HashSet (which removes duplicates) back into a space-delimited string.

## String Literals vs. new String()

Understanding the difference between string literals and strings created with the `new` keyword is crucial for Java developers. This section explores what happens when we use different approaches to create strings in Java.

### String Pool in Java

The String Pool (also known as the String Constant Pool) is a special memory area in Java's heap memory where string literals are stored. It's designed to optimize memory usage by reusing string objects.

```java
String s1 = "hello";        // Creates a string literal in the String Pool
String s2 = "hello";        // Reuses the same string object from the Pool
String s3 = new String("hello");  // Creates a new object in the heap memory
```

#### How the String Pool Works

1. When a string literal is created (e.g., `String s = "hello"`), Java first checks if the string already exists in the pool.
2. If it exists, Java returns a reference to the pooled instance.
3. If it doesn't exist, a new string object is created in the pool, and a reference is returned.

### Memory Allocation

The key difference between string literals and strings created with `new String()` is where they are stored in memory:

```
┌─────────────────────────────────────────────────────────────┐
│                                                             │
│                        Java Heap Memory                     │
│                                                             │
│  ┌─────────────────────┐         ┌─────────────────────┐   │
│  │                     │         │                     │   │
│  │   String Pool       │         │   Regular Heap      │   │
│  │                     │         │                     │   │
│  │   ┌─────────────┐   │         │   ┌─────────────┐   │   │
│  │   │ "hello"     │◄──┼─────────┼───┤ s1, s2      │   │   │
│  │   └─────────────┘   │         │   └─────────────┘   │   │
│  │                     │         │                     │   │
│  │                     │         │   ┌─────────────┐   │   │
│  │                     │         │   │ new String  │   │   │
│  │                     │         │   │ ("hello")   │◄──┼───┤ s3
│  │                     │         │   └─────────────┘   │   │
│  │                     │         │                     │   │
│  └─────────────────────┘         └─────────────────────┘   │
│                                                             │
└─────────────────────────────────────────────────────────────┘
```

#### String Literals (s1, s2)
- Stored in the String Pool
- Memory efficient as identical literals share the same memory
- Created at compile time

#### new String() (s3)
- Always creates a new object in the heap memory
- Not stored in the String Pool by default
- Created at runtime

### String Comparison

The way strings are stored affects how they should be compared:

```java
String s1 = "hello";
String s2 = "hello";
String s3 = new String("hello");

// Reference comparison (checks if they are the same object)
System.out.println(s1 == s2);      // true (both reference the same object in the pool)
System.out.println(s1 == s3);      // false (different objects in memory)

// Content comparison (checks if they contain the same characters)
System.out.println(s1.equals(s3)); // true (both contain "hello")
```

#### Forcing a String into the Pool

You can explicitly add a string to the pool using the `intern()` method:

```java
String s3 = new String("hello");
String s4 = s3.intern();  // Returns the pooled instance of "hello"

System.out.println(s1 == s4);  // true (s4 now references the pooled instance)
```

### Performance Considerations

Using string literals instead of `new String()` can lead to better performance:

1. **Memory Efficiency**: String literals reduce memory usage through reuse
2. **Faster Comparison**: `==` operator can be used for comparing pooled strings (though using `.equals()` is still recommended for safety)
3. **Reduced Garbage Collection**: Fewer objects created means less work for the garbage collector

### Common Interview Questions

Here are some common interview questions about string literals vs. `new String()`:

#### 1. What will be the output of the following code?

```java
String s1 = "hello";
String s2 = "hello";
String s3 = new String("hello");
String s4 = "hel" + "lo";  // Compile-time constant
String s5 = "hel" + new String("lo");  // Not a compile-time constant

System.out.println(s1 == s2);  // true
System.out.println(s1 == s3);  // false
System.out.println(s1 == s4);  // true
System.out.println(s1 == s5);  // false
```

#### 2. How many string objects are created in the following code?

```java
String s1 = "hello";  // 1 object in the pool
String s2 = "hello";  // 0 new objects (reuses the pooled object)
String s3 = new String("hello");  // 1 new object in the heap
```

Answer: 2 objects (1 in the pool, 1 in the heap)

#### 3. What's the difference between `==` and `.equals()` for string comparison?

- `==` compares object references (memory addresses)
- `.equals()` compares the content of the strings

#### 4. How can you force a string created with `new` to be added to the String Pool?

```java
String s3 = new String("hello");
String s4 = s3.intern();  // Adds "hello" to the pool if not already there
```

#### 5. Why is using string literals generally preferred over `new String()`?

- Memory efficiency (reuse through the String Pool)
- Better performance for string-heavy applications
- Simpler code (no need for explicit object creation)

### Running the StringPoolDemo Program

To see these concepts in action, you can run the `StringPoolDemo` program included in this repository:

1. Compile the project using Maven or your IDE
2. Run the `run_string_pool_demo.bat` file or execute:
   ```
   java -cp target\classes com.boot.StringPoolDemo
   ```

The program demonstrates:
- String literals vs. new String() creation
- String Pool behavior
- Reference vs. content comparison
- Memory addresses of string objects
- The effect of the intern() method

Sample output:
```
Reference Comparisons (==):
s1 == s2: true
s1 == s3: false
s1 == s4: true
s1 == s5: false
s1 == s6: false
s1 == s7: true

Content Comparisons (equals):
s1.equals(s2): true
s1.equals(s3): true
s1.equals(s4): true
s1.equals(s5): true
s1.equals(s6): true

Memory Addresses (System.identityHashCode):
s1: 746292446
s2: 746292446
s3: 1072591677
s4: 746292446
s5: 1523554304
s6: 1175962212
s7: 746292446

String Concatenation:
longString1 == longString2: true

Intern Example:
str1 == str2: false
str1 == str3: true
str2 == str3: false
```

This output confirms that:
- String literals with the same value share the same memory address
- Strings created with `new` have different memory addresses even with the same content
- The `intern()` method returns references to pooled string instances
- All strings with the same content are equal according to `equals()` regardless of how they were created

## Test Results

Here are the results of running our anagram checker with different examples:

```
Running Anagram with default example (cat, tac):
Original strings: cat, tac
Sorted strings: act, act
Are anagrams: true

Additional test cases:
Testing: listen, silent
Original strings: listen, silent
Sorted strings: eilnst, eilnst
Are anagrams: true

Testing: hello, world
Original strings: hello, world
Sorted strings: ehllo, dlorw
Are anagrams: false

Testing: anagram, nagaram
Original strings: anagram, nagaram
Sorted strings: aaagmnr, aaagmnr
Are anagrams: true

Testing: rat, car
Original strings: rat, car
Sorted strings: art, acr
Are anagrams: false
```

As you can see, using `new String(charArray)` correctly identifies anagrams by comparing the sorted character arrays. If we had used `Arrays.toString(charArray)` instead, we would still get the correct results but with unnecessary formatting characters in the output.

## Summary

| Method | Output Format | Use Case | Works With |
|--------|--------------|----------|------------|
| `new String(charArray)` | Raw characters | Creating strings from characters | char[] only |
| `String.valueOf(charArray)` | Raw characters | Modern, readable code | char[] only |
| `Arrays.toString(array)` | [elem1, elem2, ...] | Debugging, display | Any array type |

Choose the right method based on your specific needs to ensure your code is both correct and readable.

## XPath and CSS Selector Demonstration

### Overview

This project includes a comprehensive demonstration of various XPath axes and CSS selectors using Selenium WebDriver to interact with the Rediff website, particularly focusing on the Money Gainers page (https://money.rediff.com/gainers). The demonstration shows how to navigate the DOM (Document Object Model) of a webpage using different XPath axes and CSS selectors.

The demonstration is implemented in the `XPathDemo.java` class and includes:

1. Setting up a Chrome WebDriver using WebDriverManager
2. Navigating to the Rediff homepage (https://www.rediff.com/)
3. Demonstrating various XPath axes on the homepage
4. Navigating to the Money Gainers page (https://money.rediff.com/gainers)
5. Demonstrating more XPath axes and CSS selectors on the Money Gainers page

### The DANCE Acronym for XPath Axes

XPath axes can be remembered using the DANCE acronym:

| Letter | Axis | Description |
|--------|------|-------------|
| **D** | Descendant | Elements below the current node (direct or indirect children) |
| **A** | Ancestor | Elements above the current node (parents, grandparents, etc.) |
| **N** | Next-sibling | Elements that come after the current node at the same level |
| **C** | Child | Direct children of the current node |
| **E** | Equals (self) | The current node itself |

Additional important axes include:
- **P**arent: The direct parent of the current node
- **P**receding-sibling: Elements that come before the current node at the same level
- **F**ollowing: All elements that come after the current node in document order
- **P**receding: All elements that come before the current node in document order

### XPath Axes Explained with Visual Representation

XPath is a powerful language for navigating XML and HTML documents. It uses various axes to define relationships between elements:

```
┌─────────────────────────────────────────────────────────────────┐
│                                                                 │
│                      HTML DOM Structure                         │
│                                                                 │
│                           html                                  │
│                            │                                    │
│                            ▼                                    │
│                           body                                  │
│                            │                                    │
│                            ▼                                    │
│         ┌─────────────────┴─────────────────┐                  │
│         │                                   │                   │
│         ▼                                   ▼                   │
│       header                              main                  │
│         │                                   │                   │
│         ▼                                   ▼                   │
│  ┌──────┴──────┐                    ┌──────┴──────┐            │
│  │             │                    │             │             │
│  ▼             ▼                    ▼             ▼             │
│ logo         navbar               table        sidebar          │
│                │                    │                           │
│                ▼                    ▼                           │
│         ┌──────┴──────┐     ┌──────┴──────┐                    │
│         │             │     │             │                     │
│         ▼             ▼     ▼             ▼                     │
│       link1         link2  row1          row2                   │
│                             │             │                     │
│                             ▼             ▼                     │
│                      ┌──────┴──────┐    ┌─┴───┐                 │
│                      │             │    │     │                 │
│                      ▼             ▼    ▼     ▼                 │
│                     cell1         cell2 ...   ...               │
│                                                                 │
└─────────────────────────────────────────────────────────────────┘
```

#### 1. Parent to Child (Direct Child)
- XPath: `//div[@class='navbar']/ul/li`
- Selects all `li` elements that are direct children of a `ul` element, which is a direct child of a `div` with class 'navbar'
- Visual representation:
  ```
  div.navbar → ul → li
  ```

#### 2. Parent to Child (All Descendants)
- XPath: `//div[@class='navbar']//a`
- Selects all `a` elements that are descendants (at any level) of a `div` with class 'navbar'
- Visual representation:
  ```
  div.navbar
     ↓
    ...
     ↓
     a
  ```

#### 3. Child to Parent
- XPath: `//a[contains(text(), 'Money')]/parent::*`
- Selects the parent element of an `a` element containing the text 'Money'
- Visual representation:
  ```
  parent-element
     ↑
  a[text()='Money']
  ```

#### 4. Child to Ancestor
- XPath: `//a[contains(text(), 'Money')]/ancestor::div[1]`
- Selects the first `div` ancestor of an `a` element containing the text 'Money'
- Visual representation:
  ```
  div (ancestor)
     ↑
    ...
     ↑
  a[text()='Money']
  ```

#### 5. Following-sibling (Child to Child)
- XPath: `//a[contains(text(), 'Money')]/following-sibling::*`
- Selects all siblings that come after an `a` element containing the text 'Money'
- Visual representation:
  ```
  parent
     ↓
  a[text()='Money'] → sibling1 → sibling2 → ...
  ```

#### 6. Preceding-sibling (Child to Child)
- XPath: `//a[contains(text(), 'Money')]/preceding-sibling::*`
- Selects all siblings that come before an `a` element containing the text 'Money'
- Visual representation:
  ```
  parent
     ↓
  ... ← sibling2 ← sibling1 ← a[text()='Money']
  ```

### CSS Selectors with Visual Representation

CSS selectors provide an alternative way to select elements in a webpage. They are often more concise than XPath but have some limitations in traversing up the DOM tree.

#### Hierarchy Selectors

```
┌─────────────────────────────────────────────────────────────────┐
│                                                                 │
│                 CSS Selector Relationships                      │
│                                                                 │
│  ┌─────────────┐                                                │
│  │  Selector1  │                                                │
│  └─────────────┘                                                │
│        │                                                        │
│        ├─────────────────┬─────────────────┬─────────────────┐  │
│        │                 │                 │                 │  │
│        ▼                 ▼                 ▼                 ▼  │
│  ┌─────────────┐  ┌─────────────┐  ┌─────────────┐  ┌─────────────┐
│  │ Descendant  │  │Direct Child │  │  Adjacent   │  │  General    │
│  │  (space)    │  │     (>)     │  │Sibling (+) │  │Sibling (~)  │
│  └─────────────┘  └─────────────┘  └─────────────┘  └─────────────┘
│                                                                 │
└─────────────────────────────────────────────────────────────────┘
```

1. **Space (Descendant Selector)**
   - CSS: `div.navbar a`
   - Selects all `a` elements that are descendants (at any level) of a `div` with class 'navbar'
   - Example on Money Gainers page: `table.dataTable td` (selects all cells in the table)

2. **> (Direct Child Selector)**
   - CSS: `div.navbar > ul > li`
   - Selects all `li` elements that are direct children of a `ul` element, which is a direct child of a `div` with class 'navbar'
   - Example on Money Gainers page: `table.dataTable > tbody > tr` (selects all rows that are direct children of tbody)

3. **+ (Adjacent Sibling Selector)**
   - CSS: `td:first-child + td`
   - Selects the `td` element that immediately follows the first `td` element
   - Example on Money Gainers page: `table.dataTable > tbody > tr > td:first-child + td` (selects the second column in each row)

4. **~ (General Sibling Selector)**
   - CSS: `td:first-child ~ td`
   - Selects all `td` elements that follow the first `td` element
   - Example on Money Gainers page: `table.dataTable > tbody > tr > td:first-child ~ td` (selects all columns after the first one)

#### Attribute Selectors

```
┌─────────────────────────────────────────────────────────────────┐
│                                                                 │
│                 CSS Attribute Selectors                         │
│                                                                 │
│  ┌─────────────────┐  ┌─────────────────┐  ┌─────────────────┐  │
│  │[attribute]      │  │[attribute=value]│  │[attribute*=value]  │
│  │Has attribute    │  │Exact match      │  │Contains value    │  │
│  └─────────────────┘  └─────────────────┘  └─────────────────┘  │
│                                                                 │
│  ┌─────────────────┐  ┌─────────────────┐  ┌─────────────────┐  │
│  │[attribute^=value]  │[attribute$=value]  │[attribute~=value]  │
│  │Starts with value│  │Ends with value  │  │Contains word     │  │
│  └─────────────────┘  └─────────────────┘  └─────────────────┘  │
│                                                                 │
└─────────────────────────────────────────────────────────────────┘
```

1. **[attribute='value'] (Exact Match)**
   - CSS: `[class='dataTable']`
   - Selects elements with class attribute exactly equal to 'dataTable'
   - Example on Money Gainers page: `table[class='dataTable']` (selects the main table)

2. **[attribute*='value'] (Contains)**
   - CSS: `[class*='data']`
   - Selects elements with class attribute containing the substring 'data'
   - Example on Money Gainers page: `table[class*='data']` (selects tables with 'data' in their class)

3. **[attribute^='value'] (Starts With)**
   - CSS: `[class^='data']`
   - Selects elements with class attribute starting with 'data'
   - Example on Money Gainers page: `a[href^='https']` (selects links with HTTPS URLs)

4. **[attribute$='value'] (Ends With)**
   - CSS: `[class$='Table']`
   - Selects elements with class attribute ending with 'Table'
   - Example on Money Gainers page: `a[href$='.html']` (selects links to HTML pages)

#### Pseudo-classes and Pseudo-elements

```
┌─────────────────────────────────────────────────────────────────┐
│                                                                 │
│                 CSS Pseudo Selectors                            │
│                                                                 │
│  ┌─────────────────┐  ┌─────────────────┐  ┌─────────────────┐  │
│  │:nth-child(n)    │  │:first-child     │  │:last-child      │  │
│  │Nth child        │  │First child      │  │Last child       │  │
│  └─────────────────┘  └─────────────────┘  └─────────────────┘  │
│                                                                 │
│  ┌─────────────────┐  ┌─────────────────┐  ┌─────────────────┐  │
│  │:nth-of-type(n)  │  │:first-of-type   │  │:last-of-type    │  │
│  │Nth of type      │  │First of type    │  │Last of type     │  │
│  └─────────────────┘  └─────────────────┘  └─────────────────┘  │
│                                                                 │
└─────────────────────────────────────────────────────────────────┘
```

1. **:nth-child(n)**
   - CSS: `tr:nth-child(3)`
   - Selects the 3rd child element that is a `tr`
   - Example on Money Gainers page: `table.dataTable > tbody > tr:nth-child(3)` (selects the 3rd row in the table)

2. **:first-child**
   - CSS: `td:first-child`
   - Selects the first `td` child of its parent
   - Example on Money Gainers page: `table.dataTable > tbody > tr > td:first-child` (selects the first column in each row)

3. **:last-child**
   - CSS: `td:last-child`
   - Selects the last `td` child of its parent
   - Example on Money Gainers page: `table.dataTable > tbody > tr > td:last-child` (selects the last column in each row)

### Practical Examples on Money Gainers Page (https://money.rediff.com/gainers)

#### XPath Examples

1. **Selecting all company names in the table**
   - XPath: `//table[@class='dataTable']/tbody/tr/td[1]/a`
   - This selects all company name links in the first column of the table

2. **Selecting the current price of a specific company**
   - XPath: `//a[text()='Company Name']/parent::td/following-sibling::td[3]`
   - This selects the price cell (4th column) for a specific company

3. **Selecting all rows with price greater than 100**
   - XPath: `//table[@class='dataTable']/tbody/tr[td[4] > 100]`
   - This selects rows where the price (4th column) is greater than 100

4. **Selecting the table header row**
   - XPath: `//table[@class='dataTable']/tbody/tr[1]`
   - This selects the header row of the table

5. **Selecting all cells in the % Change column**
   - XPath: `//table[@class='dataTable']/tbody/tr/td[3]`
   - This selects all cells in the % Change column (3rd column)

#### CSS Selector Examples

1. **Selecting all company names in the table**
   - CSS: `table.dataTable > tbody > tr > td:first-child > a`
   - This selects all company name links in the first column of the table

2. **Selecting the second row in the table**
   - CSS: `table.dataTable > tbody > tr:nth-child(2)`
   - This selects the second row in the table (first data row after the header)

3. **Selecting all cells in the last column**
   - CSS: `table.dataTable > tbody > tr > td:last-child`
   - This selects all cells in the last column of the table

4. **Selecting all links in the table**
   - CSS: `table.dataTable a`
   - This selects all links within the table

5. **Selecting cells containing specific text**
   - CSS: `table.dataTable > tbody > tr > td:contains('text')`
   - Note: `:contains()` is not a standard CSS selector but is available in jQuery

### When to Use XPath vs. CSS Selectors

```
┌─────────────────────────────────────────────────────────────────┐
│                                                                 │
│             XPath vs CSS Selector Decision Tree                 │
│                                                                 │
│                        Start                                    │
│                          │                                      │
│                          ▼                                      │
│           ┌─────────────────────────────┐                       │
│           │Need to traverse up the DOM? │                       │
│           └─────────────────────────────┘                       │
│                          │                                      │
│                ┌─────────┴─────────┐                            │
│                │                   │                            │
│                ▼                   ▼                            │
│               Yes                  No                           │
│                │                   │                            │
│                ▼                   ▼                            │
│        ┌──────────────┐   ┌───────────────────┐                 │
│        │  Use XPath   │   │Need text matching?│                 │
│        └──────────────┘   └───────────────────┘                 │
│                               │                                 │
│                     ┌─────────┴─────────┐                       │
│                     │                   │                       │
│                     ▼                   ▼                       │
│                    Yes                  No                      │
│                     │                   │                       │
│                     ▼                   ▼                       │
│             ┌──────────────┐    ┌──────────────┐                │
│             │  Use XPath   │    │   Use CSS    │                │
│             └──────────────┘    └──────────────┘                │
│                                                                 │
└─────────────────────────────────────────────────────────────────┘
```

#### Use XPath when:
1. You need to traverse up the DOM tree (parent, ancestor)
2. You need to select elements based on text content
3. You need to use complex predicates or conditions
4. You need to select elements based on their position (e.g., last())
5. You need to use axes like following-sibling, preceding-sibling

#### Use CSS Selectors when:
1. Performance is critical (CSS selectors are generally faster)
2. You only need to traverse down the DOM tree
3. You need simpler, more readable selectors
4. You're working with modern web applications
5. You're familiar with CSS styling

### Running the Demo

To run the XPath and CSS Selector demonstration:

1. Make sure you have Maven installed
2. Run the `run_xpath_demo.bat` file or execute:
   ```
   mvn compile exec:java -Dexec.mainClass="com.boot.XPathDemo"
   ```

The demonstration will:
1. Open a Chrome browser
2. Navigate to the Rediff homepage
3. Print examples of various XPath axes and CSS selectors
4. Navigate to the Money Gainers page
5. Print more examples of XPath axes and CSS selectors
6. Close the browser

Note: The demonstration requires an internet connection to access the Rediff website.

## TestNG Advanced Testing Features

This project demonstrates advanced testing features using TestNG, a powerful testing framework for Java. TestNG provides numerous features that extend beyond basic unit testing, including parallel test execution, test grouping, prioritization, and data-driven testing.

### Parallel Test Execution

TestNG allows you to run tests in parallel to reduce the overall execution time. This is particularly useful for large test suites or tests that involve time-consuming operations like UI testing.

#### How Parallel Execution Works in TestNG

```
┌─────────────────────────────────────────────────────────────────┐
│                                                                 │
│                 TestNG Parallel Execution                       │
│                                                                 │
│  ┌─────────────┐                                                │
│  │  Test Suite │                                                │
│  └─────────────┘                                                │
│        │                                                        │
│        ▼                                                        │
│  ┌─────────────────────────────────────────────────────────┐   │
│  │                Thread Pool (thread-count="2")           │   │
│  └─────────────────────────────────────────────────────────┘   │
│        │                           │                           │
│        ▼                           ▼                           │
│  ┌─────────────┐            ┌─────────────┐                    │
│  │  Thread 1   │            │  Thread 2   │                    │
│  └─────────────┘            └─────────────┘                    │
│        │                           │                           │
│        ▼                           ▼                           │
│  ┌─────────────┐            ┌─────────────┐                    │
│  │ LoginTest   │            │ ProfileTest │                    │
│  └─────────────┘            └─────────────┘                    │
│                                                                 │
└─────────────────────────────────────────────────────────────────┘
```

#### Configuration in testng.xml

Parallel execution is configured in the testng.xml file:

```xml
<suite name="ParallelTestSuite" parallel="classes" thread-count="2">
  <!-- Test configurations -->
</suite>
```

The `parallel` attribute can have the following values:
- `methods`: Run test methods in parallel
- `classes`: Run test classes in parallel
- `tests`: Run test tags in parallel
- `instances`: Run test instances in parallel

The `thread-count` attribute specifies the number of threads to use for parallel execution.

#### Benefits of Parallel Execution

1. **Reduced Execution Time**: Tests run simultaneously, reducing the overall execution time
2. **Better Resource Utilization**: Makes better use of multi-core processors
3. **Earlier Feedback**: Get test results faster, especially for large test suites
4. **Realistic Load Testing**: Can simulate multiple users accessing the system simultaneously

### Test Groups and Filtering

TestNG allows you to organize tests into groups and selectively run tests based on these groups. This is useful for categorizing tests by functionality, importance, or execution time.

#### How Test Groups Work in TestNG

```
┌─────────────────────────────────────────────────────────────────┐
│                                                                 │
│                 TestNG Test Groups                              │
│                                                                 │
│  ┌─────────────────────────────────────────────────────────┐   │
│  │                     All Tests                           │   │
│  └─────────────────────────────────────────────────────────┘   │
│        │                  │                  │                  │
│        ▼                  ▼                  ▼                  │
│  ┌─────────────┐    ┌─────────────┐    ┌─────────────┐         │
│  │ login group │    │profile group│    │security group│         │
│  └─────────────┘    └─────────────┘    └─────────────┘         │
│        │                  │                  │                  │
│        ▼                  ▼                  ▼                  │
│  ┌─────────────┐    ┌─────────────┐    ┌─────────────┐         │
│  │testValidLogin│    │testViewProfile│  │testAccountLockout│    │
│  └─────────────┘    └─────────────┘    └─────────────┘         │
│        │                                                        │
│        ▼                                                        │
│  ┌─────────────┐                                                │
│  │testInvalidLogin│                                             │
│  └─────────────┘                                                │
│                                                                 │
└─────────────────────────────────────────────────────────────────┘
```

#### Defining Groups in Test Methods

Groups are defined using the `groups` attribute in the `@Test` annotation:

```java
@Test(groups = {"login", "positive"})
public void testValidLogin() {
    // Test code
}

@Test(groups = {"login", "negative"})
public void testInvalidLogin() {
    // Test code
}
```

#### Selecting Groups in testng.xml

You can select which groups to run in the testng.xml file:

```xml
<test name="Login Tests">
  <groups>
    <run>
      <include name="login" />
    </run>
  </groups>
  <classes>
    <class name="com.boot.LoginTest" />
  </classes>
</test>
```

You can also exclude groups:

```xml
<groups>
  <run>
    <include name="login" />
    <exclude name="negative" />
  </run>
</groups>
```

#### Benefits of Test Groups

1. **Organized Test Suite**: Categorize tests by functionality, importance, or execution time
2. **Selective Execution**: Run only the tests that are relevant to the current development or testing phase
3. **Flexible Test Configuration**: Easily create different test configurations for different purposes
4. **Better Test Management**: Easier to manage and maintain large test suites

### Test Prioritization

TestNG allows you to specify the order in which tests should be executed using priorities. This is useful when you have dependencies between tests or when you want to run the most important tests first.

#### How Test Prioritization Works in TestNG

```
┌─────────────────────────────────────────────────────────────────┐
│                                                                 │
│                 TestNG Test Prioritization                      │
│                                                                 │
│  ┌─────────────────────────────────────────────────────────┐   │
│  │                     Test Class                          │   │
│  └─────────────────────────────────────────────────────────┘   │
│        │                                                        │
│        ▼                                                        │
│  ┌─────────────┐                                                │
│  │ priority=1  │ ──> testValidLogin()                          │
│  └─────────────┘                                                │
│        │                                                        │
│        ▼                                                        │
│  ┌─────────────┐                                                │
│  │ priority=2  │ ──> testInvalidLogin()                        │
│  └─────────────┘                                                │
│        │                                                        │
│        ▼                                                        │
│  ┌─────────────┐                                                │
│  │ priority=3  │ ──> testAccountLockout()                      │
│  └─────────────┘                                                │
│        │                                                        │
│        ▼                                                        │
│  ┌─────────────┐                                                │
│  │ priority=4  │ ──> testPasswordReset()                       │
│  └─────────────┘                                                │
│                                                                 │
└─────────────────────────────────────────────────────────────────┘
```

#### Setting Priorities in Test Methods

Priorities are set using the `priority` attribute in the `@Test` annotation:

```java
@Test(priority = 1)
public void testValidLogin() {
    // Test code
}

@Test(priority = 2)
public void testInvalidLogin() {
    // Test code
}
```

Lower priority values run first. If no priority is specified, the default priority is 0.

#### Benefits of Test Prioritization

1. **Controlled Execution Order**: Ensure tests run in a specific order
2. **Dependency Management**: Handle dependencies between tests
3. **Fail Fast**: Run the most important tests first to get early feedback
4. **Logical Test Flow**: Create a logical flow of tests that mirrors user behavior

### XML Parameter Passing

TestNG allows you to pass parameters to test methods from the testng.xml file. This is useful for configuring tests without changing the code.

#### How XML Parameter Passing Works in TestNG

```
┌─────────────────────────────────────────────────────────────────┐
│                                                                 │
│                 TestNG XML Parameter Passing                    │
│                                                                 │
│  ┌─────────────────────────────────────────────────────────┐   │
│  │                     testng.xml                          │   │
│  │  <parameter name="username" value="gurpreet" />         │   │
│  │  <parameter name="password" value="password1" />        │   │
│  └─────────────────────────────────────────────────────────┘   │
│                           │                                     │
│                           ▼                                     │
│  ┌─────────────────────────────────────────────────────────┐   │
│  │                     Test Method                         │   │
│  │  @Parameters({"username", "password"})                  │   │
│  │  public void testValidLogin(String username,            │   │
│  │                            String password) {           │   │
│  │      // Test code using username and password           │   │
│  │  }                                                      │   │
│  └─────────────────────────────────────────────────────────┘   │
│                                                                 │
└─────────────────────────────────────────────────────────────────┘
```

#### Defining Parameters in testng.xml

Parameters are defined in the testng.xml file:

```xml
<parameter name="username" value="gurpreet" />
<parameter name="password" value="password1" />
```

Parameters can be defined at the suite, test, or class level.

#### Using Parameters in Test Methods

Parameters are accessed using the `@Parameters` annotation:

```java
@Test
@Parameters({"username", "password"})
public void testValidLogin(String username, String password) {
    System.out.println("Testing login with username: " + username + " and password: " + password);
    // Test code
}
```

#### Benefits of XML Parameter Passing

1. **Configuration Without Code Changes**: Change test parameters without modifying the code
2. **Environment-Specific Configuration**: Use different parameters for different environments
3. **Reusable Test Code**: Write test code once and run it with different parameters
4. **Centralized Configuration**: Manage all test parameters in one place

### Data-Driven Testing with XML

TestNG supports data-driven testing, where the same test is run multiple times with different data. This project demonstrates how to read test data from an XML file and use it with TestNG's DataProvider feature.

#### How Data-Driven Testing Works with XML

```
┌─────────────────────────────────────────────────────────────────┐
│                                                                 │
│                 TestNG Data-Driven Testing with XML             │
│                                                                 │
│  ┌─────────────────────────────────────────────────────────┐   │
│  │                     credentials.xml                     │   │
│  │  <user>                                                 │   │
│  │    <username>gurpreet</username>                        │   │
│  │    <password>password1</password>                       │   │
│  │  </user>                                                │   │
│  │  <user>                                                 │   │
│  │    <username>username2</username>                       │   │
│  │    <password>password2</password>                       │   │
│  │  </user>                                                │   │
│  └─────────────────────────────────────────────────────────┘   │
│                           │                                     │
│                           ▼                                     │
│  ┌─────────────────────────────────────────────────────────┐   │
│  │                 XmlCredentialReader                     │   │
│  │  public static List<Map<String, String>> readCredentials(│  │
│  │                                  String xmlFilePath) {   │   │
│  │      // Read XML and return credentials                 │   │
│  │  }                                                      │   │
│  └─────────────────────────────────────────────────────────┘   │
│                           │                                     │
│                           ▼                                     │
│  ┌─────────────────────────────────────────────────────────┐   │
│  │                     DataProvider                        │   │
│  │  @DataProvider(name = "credentialsProvider")            │   │
│  │  public Object[][] credentialsProvider() {              │   │
│  │      // Convert credentials to DataProvider format      │   │
│  │  }                                                      │   │
│  └─────────────────────────────────────────────────────────┘   │
│                           │                                     │
│                           ▼                                     │
│  ┌─────────────────────────────────────────────────────────┐   │
│  │                     Test Method                         │   │
│  │  @Test(dataProvider = "credentialsProvider")            │   │
│  │  public void testLoginWithXmlCredentials(               │   │
│  │      String username, String password, String email) {  │   │
│  │      // Test code using credentials                     │   │
│  │  }                                                      │   │
│  └─────────────────────────────────────────────────────────┘   │
│                                                                 │
└─────────────────────────────────────────────────────────────────┘
```

#### Creating the XML Data File

The credentials.xml file contains user credentials:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<credentials>
  <user>
    <username>gurpreet</username>
    <password>password1</password>
    <email>gurpreet@example.com</email>
  </user>
  <user>
    <username>username2</username>
    <password>password2</password>
    <email>user2@example.com</email>
  </user>
</credentials>
```

#### Reading XML Data with a Utility Class

The XmlCredentialReader class reads credentials from the XML file:

```java
public static List<Map<String, String>> readCredentials(String xmlFilePath) {
    List<Map<String, String>> users = new ArrayList<>();

    try {
        File xmlFile = new File(xmlFilePath);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(xmlFile);

        // Process XML and extract credentials
        // ...

    } catch (Exception e) {
        System.err.println("Error reading credentials XML: " + e.getMessage());
    }

    return users;
}
```

#### Using XML Data with DataProvider

The DataProvider method converts the XML data to the format required by TestNG:

```java
@DataProvider(name = "credentialsProvider")
public Object[][] credentialsProvider() {
    List<Map<String, String>> users = XmlCredentialReader.readCredentials("credentials.xml");

    Object[][] data = new Object[users.size()][3];
    for (int i = 0; i < users.size(); i++) {
        Map<String, String> user = users.get(i);
        data[i][0] = user.get("username");
        data[i][1] = user.get("password");
        data[i][2] = user.get("email");
    }

    return data;
}
```

#### Using the DataProvider in Test Methods

The test method uses the DataProvider to run with multiple sets of credentials:

```java
@Test(dataProvider = "credentialsProvider", groups = {"dataprovider"})
public void testLoginWithXmlCredentials(String username, String password, String email) {
    System.out.println("Testing login with credentials from XML:");
    System.out.println("  Username: " + username);
    System.out.println("  Password: " + password);
    System.out.println("  Email: " + email);

    // Test code
}
```

#### Benefits of Data-Driven Testing with XML

1. **Separation of Test Data and Test Code**: Store test data separately from test code
2. **Reusable Test Data**: Use the same test data for multiple tests
3. **Easy Data Maintenance**: Update test data without changing test code
4. **Comprehensive Test Coverage**: Test with multiple data sets to ensure thorough coverage

### Running TestNG Tests

This project includes a batch file to run the TestNG tests using Maven.

#### Running Tests with Maven

The run_testng_tests.bat file contains:

```batch
@echo off
echo Running TestNG Tests...
call mvn clean test -DsuiteXmlFile=testng.xml
echo Tests completed.
pause
```

This command runs the tests defined in the testng.xml file.

#### Running Specific Test Groups

To run specific test groups, you can use the testng.xml file to define which groups to include or exclude, or you can use Maven command-line options:

```batch
mvn clean test -DsuiteXmlFile=testng.xml -Dgroups=login,positive
```

#### Running Tests in Parallel

The parallel execution is configured in the testng.xml file:

```xml
<suite name="ParallelTestSuite" parallel="classes" thread-count="2">
  <!-- Test configurations -->
</suite>
```

#### Viewing Test Results

TestNG generates detailed test reports in the target/surefire-reports directory, including:
- HTML reports
- XML reports
- Plain text reports

These reports show test results, execution times, and any failures or errors.

#### Benefits of Using TestNG with Maven

1. **Integrated Build Process**: Run tests as part of the build process
2. **Consistent Execution Environment**: Ensure tests run in the same environment
3. **Detailed Reporting**: Generate comprehensive test reports
4. **Flexible Configuration**: Configure tests using XML or command-line options
