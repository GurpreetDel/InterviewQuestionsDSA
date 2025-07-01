package com.boot.designpatterns.builder.aircraft;

/**
 * Client class to demonstrate the Builder pattern with the Aircraft example.
 * This class shows how to create different types of aircraft using the same construction process.
 */
public class AircraftClient {
    
    /**
     * Main method to run the demonstration.
     * 
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        System.out.println("===== Aircraft Builder Pattern Demonstration =====\n");
        
        // Create builders for different aircraft types
        F16Builder f16Builder = new F16Builder();
        Boeing747Builder boeing747Builder = new Boeing747Builder();
        
        // Create a director to manage the construction process
        Director director = new Director(f16Builder);
        
        // Construct an F-16 fighter jet (not a passenger aircraft)
        System.out.println("\n----- Building F-16 Fighter Jet -----");
        director.construct(false);
        
        // Get the constructed F-16
        IAircraft f16 = f16Builder.getResult();
        System.out.println("\nF-16 Details: " + f16);
        System.out.println("Aircraft Type: " + f16.getType());
        
        // Test flying the F-16
        System.out.println("\nTesting F-16:");
        f16.fly();
        
        // Switch to building a Boeing 747
        director.setAircraftBuilder(boeing747Builder);
        
        // Construct a Boeing 747 (a passenger aircraft)
        System.out.println("\n----- Building Boeing 747 Passenger Aircraft -----");
        director.construct(true);
        
        // Get the constructed Boeing 747
        IAircraft boeing747 = boeing747Builder.getResult();
        System.out.println("\nBoeing 747 Details: " + boeing747);
        System.out.println("Aircraft Type: " + boeing747.getType());
        
        // Test flying the Boeing 747
        System.out.println("\nTesting Boeing 747:");
        boeing747.fly();
        
        // Demonstrate building an F-16 with incorrect bathroom parameter
        System.out.println("\n----- Building F-16 with Incorrect Bathroom Parameter -----");
        director.setAircraftBuilder(f16Builder);
        director.construct(true); // F-16 doesn't have bathrooms, but this won't cause an error
        
        // Get the constructed F-16
        f16 = f16Builder.getResult();
        System.out.println("\nF-16 Details: " + f16);
        
        // Demonstrate how the Builder pattern separates construction from representation
        System.out.println("\n===== Builder Pattern Benefits =====");
        System.out.println("1. Same construction process creates different aircraft types");
        System.out.println("2. Construction steps are hidden from the client");
        System.out.println("3. New aircraft types can be added without changing the Director");
        System.out.println("4. Aircraft objects are created step by step");
    }
}