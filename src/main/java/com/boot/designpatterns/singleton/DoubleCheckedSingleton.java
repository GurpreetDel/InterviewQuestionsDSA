package com.boot.designpatterns.singleton;

/**
 * Double-checked locking implementation of the Singleton pattern.
 * This implementation is thread-safe and has better performance than the synchronized method approach
 * because it only synchronizes when the instance is null.
 */
public class DoubleCheckedSingleton {
    // volatile ensures visibility across threads
    private static volatile DoubleCheckedSingleton instance;
    
    // Private constructor prevents instantiation from other classes
    private DoubleCheckedSingleton() {
        System.out.println("DoubleCheckedSingleton is created!");
    }
    
    // Double-checked locking method to get the instance
    public static DoubleCheckedSingleton getInstance() {
        // Check 1: No synchronization needed if instance exists
        if (instance == null) {
            // Synchronize only when instance might be null
            synchronized (DoubleCheckedSingleton.class) {
                // Check 2: Double-check within synchronized block
                if (instance == null) {
                    instance = new DoubleCheckedSingleton();
                }
            }
        }
        return instance;
    }
    
    // Business method
    public void showMessage() {
        System.out.println("Hello from DoubleCheckedSingleton!");
    }
}