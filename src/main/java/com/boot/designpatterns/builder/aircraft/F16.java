package com.boot.designpatterns.builder.aircraft;

/**
 * F16 fighter jet implementation of the IAircraft interface.
 * This class represents an F-16 fighter jet with its specific components.
 */
public class F16 implements IAircraft {
    // Components of the F-16
    private String engine;
    private String cockpit;
    private String wings;
    
    /**
     * Default constructor.
     */
    public F16() {
        System.out.println("Creating F-16 aircraft");
    }
    
    /**
     * Set the engine for the F-16.
     * 
     * @param engine The engine to set
     */
    public void setEngine(String engine) {
        this.engine = engine;
        System.out.println("F-16 engine set: " + engine);
    }
    
    /**
     * Set the cockpit for the F-16.
     * 
     * @param cockpit The cockpit to set
     */
    public void setCockpit(String cockpit) {
        this.cockpit = cockpit;
        System.out.println("F-16 cockpit set: " + cockpit);
    }
    
    /**
     * Set the wings for the F-16.
     * 
     * @param wings The wings to set
     */
    public void setWings(String wings) {
        this.wings = wings;
        System.out.println("F-16 wings set: " + wings);
    }
    
    @Override
    public void fly() {
        // Check if all components are set
        if (engine == null || cockpit == null || wings == null) {
            System.out.println("F-16 cannot fly! Some components are missing.");
            return;
        }
        
        System.out.println("F-16 is flying with " + engine + ", " + cockpit + ", and " + wings);
    }
    
    @Override
    public String getType() {
        return "F-16 Fighting Falcon";
    }
    
    @Override
    public String toString() {
        return "F16{" +
                "engine='" + engine + '\'' +
                ", cockpit='" + cockpit + '\'' +
                ", wings='" + wings + '\'' +
                '}';
    }
}