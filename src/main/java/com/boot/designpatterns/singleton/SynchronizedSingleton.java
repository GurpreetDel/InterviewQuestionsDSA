package com.boot.designpatterns.singleton;

/**
 * Synchronized method implementation of the Singleton pattern.
 * This implementation is thread-safe but has performance overhead due to synchronization on every call.
 */
public class SynchronizedSingleton {
    // The sole instance of the class
    private static SynchronizedSingleton instance;
    
    // Private constructor prevents instantiation from other classes
    private SynchronizedSingleton() {
        System.out.println("SynchronizedSingleton is created!");
    }
    
    // Synchronized method to prevent multiple threads from creating instances
    public static synchronized SynchronizedSingleton getInstance() {
        if (instance == null) {
            instance = new SynchronizedSingleton();
        }
        return instance;
    }
    
    // Business method
    public void showMessage() {
        System.out.println("Hello from SynchronizedSingleton!");
    }
}