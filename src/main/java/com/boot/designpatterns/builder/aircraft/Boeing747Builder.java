package com.boot.designpatterns.builder.aircraft;

/**
 * Concrete builder for Boeing 747 passenger aircraft.
 * This class implements the methods to build the specific parts of a Boeing 747,
 * including the bathrooms which are not present in the F16.
 */
public class Boeing747Builder extends AircraftBuilder {
    
    private Boeing747 boeing747;
    
    /**
     * Constructor initializes a new Boeing747Builder.
     */
    public Boeing747Builder() {
        // The builder doesn't create the Boeing747 instance until buildCockpit is called
    }
    
    @Override
    public void buildEngine() {
        // Make sure the Boeing747 instance exists
        if (boeing747 == null) {
            System.out.println("Error: Cannot build engine before cockpit!");
            return;
        }
        
        // Build the Boeing 747 engines
        boeing747.setEngine("Four Pratt & Whitney JT9D-7 turbofan engines");
    }
    
    @Override
    public void buildWings() {
        // Make sure the Boeing747 instance exists
        if (boeing747 == null) {
            System.out.println("Error: Cannot build wings before cockpit!");
            return;
        }
        
        // Build the Boeing 747 wings
        boeing747.setWings("High-mounted swept wings with high-lift devices");
    }
    
    @Override
    public void buildCockpit() {
        // Create a new Boeing747 instance
        boeing747 = new Boeing747();
        
        // Build the Boeing 747 cockpit
        boeing747.setCockpit("Two-crew glass cockpit with flight engineer station");
    }
    
    @Override
    public void buildBathrooms() {
        // Make sure the Boeing747 instance exists
        if (boeing747 == null) {
            System.out.println("Error: Cannot build bathrooms before cockpit!");
            return;
        }
        
        // Build the Boeing 747 bathrooms
        boeing747.setBathrooms("Multiple lavatories on each deck");
    }
    
    @Override
    public IAircraft getResult() {
        return boeing747;
    }
}