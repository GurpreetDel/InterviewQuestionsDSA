package com.boot.designpatterns.builder.aircraft;

/**
 * Director class for aircraft construction.
 * This class is responsible for directing the construction process of the aircraft
 * by calling the builder methods in the correct order.
 */
public class Director {
    
    private AircraftBuilder aircraftBuilder;
    
    /**
     * Constructor that takes an AircraftBuilder.
     * 
     * @param aircraftBuilder The builder to use for aircraft construction
     */
    public Director(AircraftBuilder aircraftBuilder) {
        this.aircraftBuilder = aircraftBuilder;
    }
    
    /**
     * Set a different builder for the director.
     * 
     * @param aircraftBuilder The new builder to use
     */
    public void setAircraftBuilder(AircraftBuilder aircraftBuilder) {
        this.aircraftBuilder = aircraftBuilder;
    }
    
    /**
     * Construct the aircraft by calling the builder methods in the correct order.
     * 
     * @param isPassenger Whether the aircraft is a passenger aircraft (determines if bathrooms are built)
     */
    public void construct(boolean isPassenger) {
        System.out.println("Starting aircraft construction...");
        
        // Build the aircraft parts in a specific order
        aircraftBuilder.buildCockpit();
        aircraftBuilder.buildEngine();
        aircraftBuilder.buildWings();
        
        // Only build bathrooms for passenger aircraft
        if (isPassenger) {
            aircraftBuilder.buildBathrooms();
        }
        
        System.out.println("Aircraft construction completed.");
    }
}