package com.boot.designpatterns.builder;

import java.util.ArrayList;
import java.util.List;

/**
 * Person class implemented using the Builder pattern.
 * This class demonstrates how to create a complex object with many optional parameters
 * using the Builder pattern to avoid telescoping constructors.
 */
public class Person {
    // Required parameters
    private final String name;
    
    // Optional parameters
    private final int age;
    private final String gender;
    private final String address;
    private final String phoneNumber;
    private final String email;
    private final boolean employed;
    private final List<String> hobbies;
    
    /**
     * Private constructor that takes a builder object.
     * This ensures that a Person object can only be created using the Builder.
     * 
     * @param builder The PersonBuilder object containing all the parameters
     */
    private Person(PersonBuilder builder) {
        this.name = builder.name;
        this.age = builder.age;
        this.gender = builder.gender;
        this.address = builder.address;
        this.phoneNumber = builder.phoneNumber;
        this.email = builder.email;
        this.employed = builder.employed;
        this.hobbies = builder.hobbies;
    }
    
    // Getters (no setters to ensure immutability)
    
    public String getName() {
        return name;
    }
    
    public int getAge() {
        return age;
    }
    
    public String getGender() {
        return gender;
    }
    
    public String getAddress() {
        return address;
    }
    
    public String getPhoneNumber() {
        return phoneNumber;
    }
    
    public String getEmail() {
        return email;
    }
    
    public boolean isEmployed() {
        return employed;
    }
    
    public List<String> getHobbies() {
        return new ArrayList<>(hobbies); // Return a copy to maintain immutability
    }
    
    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", employed=" + employed +
                ", hobbies=" + hobbies +
                '}';
    }
    
    /**
     * Builder class for Person.
     * This static nested class provides a fluent interface for constructing Person objects.
     */
    public static class PersonBuilder {
        // Required parameters
        private final String name;
        
        // Optional parameters - initialized with default values
        private int age = 0;
        private String gender = "Unspecified";
        private String address = "";
        private String phoneNumber = "";
        private String email = "";
        private boolean employed = false;
        private List<String> hobbies = new ArrayList<>();
        
        /**
         * Constructor with required parameters.
         * 
         * @param name The person's name (required)
         */
        public PersonBuilder(String name) {
            this.name = name;
        }
        
        /**
         * Set the person's age.
         * 
         * @param age The person's age
         * @return The builder instance for method chaining
         */
        public PersonBuilder age(int age) {
            this.age = age;
            return this;
        }
        
        /**
         * Set the person's gender.
         * 
         * @param gender The person's gender
         * @return The builder instance for method chaining
         */
        public PersonBuilder gender(String gender) {
            this.gender = gender;
            return this;
        }
        
        /**
         * Set the person's address.
         * 
         * @param address The person's address
         * @return The builder instance for method chaining
         */
        public PersonBuilder address(String address) {
            this.address = address;
            return this;
        }
        
        /**
         * Set the person's phone number.
         * 
         * @param phoneNumber The person's phone number
         * @return The builder instance for method chaining
         */
        public PersonBuilder phoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }
        
        /**
         * Set the person's email.
         * 
         * @param email The person's email
         * @return The builder instance for method chaining
         */
        public PersonBuilder email(String email) {
            this.email = email;
            return this;
        }
        
        /**
         * Set whether the person is employed.
         * 
         * @param employed True if the person is employed, false otherwise
         * @return The builder instance for method chaining
         */
        public PersonBuilder employed(boolean employed) {
            this.employed = employed;
            return this;
        }
        
        /**
         * Add a hobby to the person's list of hobbies.
         * 
         * @param hobby The hobby to add
         * @return The builder instance for method chaining
         */
        public PersonBuilder addHobby(String hobby) {
            this.hobbies.add(hobby);
            return this;
        }
        
        /**
         * Set the person's list of hobbies.
         * 
         * @param hobbies The list of hobbies
         * @return The builder instance for method chaining
         */
        public PersonBuilder hobbies(List<String> hobbies) {
            this.hobbies = new ArrayList<>(hobbies);
            return this;
        }
        
        /**
         * Build the Person object.
         * 
         * @return A new Person object with the specified parameters
         * @throws IllegalArgumentException If age is negative
         */
        public Person build() {
            // Validate the data if needed
            if (age < 0) {
                throw new IllegalArgumentException("Age cannot be negative");
            }
            
            return new Person(this);
        }
    }
}