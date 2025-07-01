package com.boot.designpatterns.builder.aircraft;

/**
 * Concrete builder for F16 fighter jet.
 * This class implements the methods to build the specific parts of an F16 fighter jet.
 */
public class F16Builder extends AircraftBuilder {
    
    private F16 f16;
    
    /**
     * Constructor initializes a new F16Builder.
     */
    public F16Builder() {
        // The builder doesn't create the F16 instance until buildCockpit is called
    }
    
    @Override
    public void buildEngine() {
        // Make sure the F16 instance exists
        if (f16 == null) {
            System.out.println("Error: Cannot build engine before cockpit!");
            return;
        }
        
        // Build the F16 engine
        f16.setEngine("F110-GE-129 afterburning turbofan engine");
    }
    
    @Override
    public void buildWings() {
        // Make sure the F16 instance exists
        if (f16 == null) {
            System.out.println("Error: Cannot build wings before cockpit!");
            return;
        }
        
        // Build the F16 wings
        f16.setWings("Swept-back wings with leading-edge flaps");
    }
    
    @Override
    public void buildCockpit() {
        // Create a new F16 instance
        f16 = new F16();
        
        // Build the F16 cockpit
        f16.setCockpit("Single-seat cockpit with bubble canopy");
    }
    
    @Override
    public IAircraft getResult() {
        return f16;
    }
}