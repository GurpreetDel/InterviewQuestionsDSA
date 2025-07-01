package com.boot.designpatterns.singleton;

/**
 * Demonstration of the AirforceOne Singleton pattern.
 * This class shows how to use the AirforceOne singleton and verifies
 * that only one instance is created.
 */
public class AirforceOneDemo {
    
    public static void main(String[] args) {
        System.out.println("===== AirforceOne Singleton Pattern Demonstration =====\n");
        
        // Get the first instance
        System.out.println("Getting first instance of AirforceOne...");
        AirforceOne airforceOne1 = AirforceOne.getInstance();
        airforceOne1.fly();
        
        // Get another instance
        System.out.println("\nGetting second instance of AirforceOne...");
        AirforceOne airforceOne2 = AirforceOne.getInstance();
        airforceOne2.fly();
        
        // Check if both references point to the same object
        System.out.println("\nAre both references the same object? " + 
                          (airforceOne1 == airforceOne2));
        System.out.println("airforceOne1 hash: " + airforceOne1.hashCode());
        System.out.println("airforceOne2 hash: " + airforceOne2.hashCode());
        
        // Demonstrate that we cannot create a new instance directly
        System.out.println("\nNote: We cannot create a new instance directly using 'new AirforceOne()'");
        System.out.println("because the constructor is private. We must use getInstance() method.");
        
        // Use the Client class to demonstrate usage
        System.out.println("\nUsing Client class to demonstrate usage:");
        Client client = new Client();
        client.main();
    }
}