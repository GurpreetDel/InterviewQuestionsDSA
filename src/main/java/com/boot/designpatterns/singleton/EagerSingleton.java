package com.boot.designpatterns.singleton;

/**
 * Eager initialization implementation of the Singleton pattern.
 * This implementation is thread-safe because the instance is created at class loading time.
 */
public class EagerSingleton {
    // Instance is created at class loading time
    private static final EagerSingleton INSTANCE = new EagerSingleton();
    
    // Private constructor prevents instantiation from other classes
    private EagerSingleton() {
        System.out.println("EagerSingleton is created!");
    }
    
    // Public static method to get the instance
    public static EagerSingleton getInstance() {
        return INSTANCE;
    }
    
    // Business method
    public void showMessage() {
        System.out.println("Hello from EagerSingleton!");
    }
}