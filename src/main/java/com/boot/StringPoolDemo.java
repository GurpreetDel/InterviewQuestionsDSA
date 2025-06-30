package com.boot;

/**
 * Demonstration of String Pool concepts and string creation methods
 */
public class StringPoolDemo {
    public static void main(String[] args) {
        // String literals - stored in the String Pool
        String s1 = "hello";
        String s2 = "hello";
        
        // String object created with new - stored in the heap
        String s3 = new String("hello");
        
        // Compile-time constant expressions are also interned in the String Pool
        String s4 = "hel" + "lo";
        
        // Runtime expressions are not interned automatically
        String prefix = "hel";
        String s5 = prefix + "lo";
        
        // Using new String with another string as argument
        String s6 = new String(s1);
        
        // Using intern() to add a string to the pool
        String s7 = s3.intern();
        
        // Reference comparisons (==)
        System.out.println("Reference Comparisons (==):");
        System.out.println("s1 == s2: " + (s1 == s2));  // true - both reference the same object in the pool
        System.out.println("s1 == s3: " + (s1 == s3));  // false - s3 is a different object in the heap
        System.out.println("s1 == s4: " + (s1 == s4));  // true - compile-time constants are interned
        System.out.println("s1 == s5: " + (s1 == s5));  // false - runtime expressions create new objects
        System.out.println("s1 == s6: " + (s1 == s6));  // false - new String always creates a new object
        System.out.println("s1 == s7: " + (s1 == s7));  // true - s7 references the pooled instance
        
        // Content comparisons (equals)
        System.out.println("\nContent Comparisons (equals):");
        System.out.println("s1.equals(s2): " + s1.equals(s2));  // true
        System.out.println("s1.equals(s3): " + s1.equals(s3));  // true
        System.out.println("s1.equals(s4): " + s1.equals(s4));  // true
        System.out.println("s1.equals(s5): " + s1.equals(s5));  // true
        System.out.println("s1.equals(s6): " + s1.equals(s6));  // true
        
        // Memory addresses (for illustration)
        System.out.println("\nMemory Addresses (System.identityHashCode):");
        System.out.println("s1: " + System.identityHashCode(s1));
        System.out.println("s2: " + System.identityHashCode(s2));  // Same as s1
        System.out.println("s3: " + System.identityHashCode(s3));  // Different from s1
        System.out.println("s4: " + System.identityHashCode(s4));  // Same as s1
        System.out.println("s5: " + System.identityHashCode(s5));  // Different from s1
        System.out.println("s6: " + System.identityHashCode(s6));  // Different from s1
        System.out.println("s7: " + System.identityHashCode(s7));  // Same as s1
        
        // Demonstrating string concatenation and intern
        String longString1 = "Java" + " Programming" + " Language";
        String longString2 = "Java Programming Language";
        System.out.println("\nString Concatenation:");
        System.out.println("longString1 == longString2: " + (longString1 == longString2));  // true
        
        // Creating strings with the same value but different ways
        String str1 = "hello world";
        String str2 = new String("hello world");
        String str3 = str2.intern();
        
        System.out.println("\nIntern Example:");
        System.out.println("str1 == str2: " + (str1 == str2));  // false
        System.out.println("str1 == str3: " + (str1 == str3));  // true
        System.out.println("str2 == str3: " + (str2 == str3));  // false
    }
}