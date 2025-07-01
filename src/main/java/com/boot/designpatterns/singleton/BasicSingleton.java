package com.boot.designpatterns.singleton;

/**
 * Basic implementation of the Singleton pattern.
 * This implementation is not thread-safe.
 */
public class BasicSingleton {
    // The sole instance of the class
    private static BasicSingleton instance;
    
    // Private constructor prevents instantiation from other classes
    private BasicSingleton() {
        System.out.println("BasicSingleton is created!");
    }
    
    // Public static method to get the instance
    public static BasicSingleton getInstance() {
        // Lazy initialization: create the instance only when needed
        if (instance == null) {
            instance = new BasicSingleton();
        }
        return instance;
    }
    
    // Business method
    public void showMessage() {
        System.out.println("Hello from BasicSingleton!");
    }
}