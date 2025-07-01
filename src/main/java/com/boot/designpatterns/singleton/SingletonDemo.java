package com.boot.designpatterns.singleton;

/**
 * Demonstration of different Singleton pattern implementations.
 * This class shows how to use each Singleton implementation and verifies
 * that they indeed return the same instance every time.
 */
public class SingletonDemo {
    
    public static void main(String[] args) {
        System.out.println("===== Singleton Pattern Demonstration =====\n");
        
        // Test BasicSingleton
        testBasicSingleton();
        
        // Test EagerSingleton
        testEagerSingleton();
        
        // Test SynchronizedSingleton
        testSynchronizedSingleton();
        
        // Test DoubleCheckedSingleton
        testDoubleCheckedSingleton();
        
        // Test BillPughSingleton
        testBillPughSingleton();
        
        // Test EnumSingleton
        testEnumSingleton();
    }
    
    private static void testBasicSingleton() {
        System.out.println("\n----- Basic Singleton -----");
        
        // Get the first instance
        BasicSingleton singleton1 = BasicSingleton.getInstance();
        singleton1.showMessage();
        
        // Get another instance
        BasicSingleton singleton2 = BasicSingleton.getInstance();
        
        // Check if both references point to the same object
        System.out.println("Are both references the same object? " + 
                          (singleton1 == singleton2));
        System.out.println("singleton1 hash: " + singleton1.hashCode());
        System.out.println("singleton2 hash: " + singleton2.hashCode());
    }
    
    private static void testEagerSingleton() {
        System.out.println("\n----- Eager Singleton -----");
        
        // Get the first instance
        EagerSingleton singleton1 = EagerSingleton.getInstance();
        singleton1.showMessage();
        
        // Get another instance
        EagerSingleton singleton2 = EagerSingleton.getInstance();
        
        // Check if both references point to the same object
        System.out.println("Are both references the same object? " + 
                          (singleton1 == singleton2));
        System.out.println("singleton1 hash: " + singleton1.hashCode());
        System.out.println("singleton2 hash: " + singleton2.hashCode());
    }
    
    private static void testSynchronizedSingleton() {
        System.out.println("\n----- Synchronized Singleton -----");
        
        // Get the first instance
        SynchronizedSingleton singleton1 = SynchronizedSingleton.getInstance();
        singleton1.showMessage();
        
        // Get another instance
        SynchronizedSingleton singleton2 = SynchronizedSingleton.getInstance();
        
        // Check if both references point to the same object
        System.out.println("Are both references the same object? " + 
                          (singleton1 == singleton2));
        System.out.println("singleton1 hash: " + singleton1.hashCode());
        System.out.println("singleton2 hash: " + singleton2.hashCode());
    }
    
    private static void testDoubleCheckedSingleton() {
        System.out.println("\n----- Double-Checked Singleton -----");
        
        // Get the first instance
        DoubleCheckedSingleton singleton1 = DoubleCheckedSingleton.getInstance();
        singleton1.showMessage();
        
        // Get another instance
        DoubleCheckedSingleton singleton2 = DoubleCheckedSingleton.getInstance();
        
        // Check if both references point to the same object
        System.out.println("Are both references the same object? " + 
                          (singleton1 == singleton2));
        System.out.println("singleton1 hash: " + singleton1.hashCode());
        System.out.println("singleton2 hash: " + singleton2.hashCode());
    }
    
    private static void testBillPughSingleton() {
        System.out.println("\n----- Bill Pugh Singleton -----");
        
        // Get the first instance
        BillPughSingleton singleton1 = BillPughSingleton.getInstance();
        singleton1.showMessage();
        
        // Get another instance
        BillPughSingleton singleton2 = BillPughSingleton.getInstance();
        
        // Check if both references point to the same object
        System.out.println("Are both references the same object? " + 
                          (singleton1 == singleton2));
        System.out.println("singleton1 hash: " + singleton1.hashCode());
        System.out.println("singleton2 hash: " + singleton2.hashCode());
    }
    
    private static void testEnumSingleton() {
        System.out.println("\n----- Enum Singleton -----");
        
        // Get the instance
        EnumSingleton singleton1 = EnumSingleton.INSTANCE;
        singleton1.showMessage();
        singleton1.doSomething();
        
        // Get another reference to the instance
        EnumSingleton singleton2 = EnumSingleton.INSTANCE;
        
        // Check if both references point to the same object
        System.out.println("Are both references the same object? " + 
                          (singleton1 == singleton2));
        System.out.println("singleton1 hash: " + singleton1.hashCode());
        System.out.println("singleton2 hash: " + singleton2.hashCode());
    }
}