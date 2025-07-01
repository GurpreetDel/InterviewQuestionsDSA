package com.boot.designpatterns.builder.aircraft;

/**
 * Boeing747 passenger aircraft implementation of the IAircraft interface.
 * This class represents a Boeing 747 passenger aircraft with its specific components.
 */
public class Boeing747 implements IAircraft {
    // Components of the Boeing 747
    private String engine;
    private String cockpit;
    private String wings;
    private String bathrooms;
    
    /**
     * Default constructor.
     */
    public Boeing747() {
        System.out.println("Creating Boeing 747 aircraft");
    }
    
    /**
     * Set the engine for the Boeing 747.
     * 
     * @param engine The engine to set
     */
    public void setEngine(String engine) {
        this.engine = engine;
        System.out.println("Boeing 747 engine set: " + engine);
    }
    
    /**
     * Set the cockpit for the Boeing 747.
     * 
     * @param cockpit The cockpit to set
     */
    public void setCockpit(String cockpit) {
        this.cockpit = cockpit;
        System.out.println("Boeing 747 cockpit set: " + cockpit);
    }
    
    /**
     * Set the wings for the Boeing 747.
     * 
     * @param wings The wings to set
     */
    public void setWings(String wings) {
        this.wings = wings;
        System.out.println("Boeing 747 wings set: " + wings);
    }
    
    /**
     * Set the bathrooms for the Boeing 747.
     * 
     * @param bathrooms The bathrooms to set
     */
    public void setBathrooms(String bathrooms) {
        this.bathrooms = bathrooms;
        System.out.println("Boeing 747 bathrooms set: " + bathrooms);
    }
    
    @Override
    public void fly() {
        // Check if all components are set
        if (engine == null || cockpit == null || wings == null) {
            System.out.println("Boeing 747 cannot fly! Some components are missing.");
            return;
        }
        
        System.out.println("Boeing 747 is flying with " + engine + ", " + cockpit + ", and " + wings);
        if (bathrooms != null) {
            System.out.println("Passengers can use the " + bathrooms);
        }
    }
    
    @Override
    public String getType() {
        return "Boeing 747 Jumbo Jet";
    }
    
    @Override
    public String toString() {
        return "Boeing747{" +
                "engine='" + engine + '\'' +
                ", cockpit='" + cockpit + '\'' +
                ", wings='" + wings + '\'' +
                ", bathrooms='" + bathrooms + '\'' +
                '}';
    }
}