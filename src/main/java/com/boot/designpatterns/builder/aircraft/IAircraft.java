package com.boot.designpatterns.builder.aircraft;

/**
 * Interface for aircraft objects.
 * This interface defines the common behavior for all aircraft types.
 */
public interface IAircraft {
    /**
     * Fly the aircraft.
     */
    void fly();
    
    /**
     * Get the type of the aircraft.
     * 
     * @return The type of the aircraft
     */
    String getType();
}