package com.boot.designpatterns.singleton;

/**
 * Bill Pugh Singleton implementation using a static inner helper class.
 * This implementation is thread-safe without synchronization and uses lazy initialization.
 * The inner class is only loaded when the getInstance method is called.
 */
public class BillPughSingleton {
    
    // Private constructor prevents instantiation from other classes
    private BillPughSingleton() {
        System.out.println("BillPughSingleton is created!");
    }
    
    // Inner static helper class
    private static class SingletonHelper {
        // Instance is created inside the helper class
        private static final BillPughSingleton INSTANCE = new BillPughSingleton();
    }
    
    // Public static method to get the instance
    public static BillPughSingleton getInstance() {
        return SingletonHelper.INSTANCE;
    }
    
    // Business method
    public void showMessage() {
        System.out.println("Hello from BillPughSingleton!");
    }
}