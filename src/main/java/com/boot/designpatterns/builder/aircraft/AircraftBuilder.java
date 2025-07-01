package com.boot.designpatterns.builder.aircraft;

/**
 * Abstract builder class for aircraft construction.
 * This class defines the methods for building the different parts of an aircraft.
 * Concrete builders will implement these methods to construct specific aircraft types.
 */
public abstract class AircraftBuilder {
    
    /**
     * Build the engine part of the aircraft.
     */
    public void buildEngine() {
        // Default implementation does nothing
    }
    
    /**
     * Build the wings part of the aircraft.
     */
    public void buildWings() {
        // Default implementation does nothing
    }
    
    /**
     * Build the cockpit part of the aircraft.
     */
    public void buildCockpit() {
        // Default implementation does nothing
    }
    
    /**
     * Build the bathrooms part of the aircraft.
     * This is optional and may not be implemented by all aircraft types.
     */
    public void buildBathrooms() {
        // Default implementation does nothing
    }
    
    /**
     * Get the constructed aircraft.
     * 
     * @return The constructed aircraft
     */
    public abstract IAircraft getResult();
}