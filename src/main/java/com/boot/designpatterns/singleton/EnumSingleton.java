package com.boot.designpatterns.singleton;

/**
 * Enum Singleton implementation.
 * This implementation is thread-safe by design, handles serialization automatically,
 * and is protected from reflection attacks.
 * 
 * Considered the simplest and most effective way to implement a Singleton in Java.
 */
public enum EnumSingleton {
    INSTANCE;
    
    // Constructor is automatically private in enums
    EnumSingleton() {
        System.out.println("EnumSingleton is created!");
    }
    
    // Business method
    public void showMessage() {
        System.out.println("Hello from EnumSingleton!");
    }
    
    // Additional methods can be added as needed
    public void doSomething() {
        System.out.println("Doing something in EnumSingleton...");
    }
}