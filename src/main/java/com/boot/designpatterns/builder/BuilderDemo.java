package com.boot.designpatterns.builder;

import java.util.Arrays;

/**
 * Demonstration of the Builder pattern using the Person class.
 * This class shows how to create Person objects with different combinations of parameters
 * using the Builder pattern.
 */
public class BuilderDemo {
    
    public static void main(String[] args) {
        System.out.println("===== Builder Pattern Demonstration =====\n");
        
        // Create a person with just the required name
        Person person1 = new Person.PersonBuilder("John Doe")
                .build();
        
        // Create a person with all details
        Person person2 = new Person.PersonBuilder("Jane Smith")
                .age(30)
                .gender("Female")
                .address("123 Main St, City, Country")
                .phoneNumber("555-1234")
                .email("jane.smith@example.com")
                .employed(true)
                .addHobby("Reading")
                .addHobby("Hiking")
                .build();
        
        // Create a person with some details
        Person person3 = new Person.PersonBuilder("Bob Johnson")
                .age(45)
                .gender("Male")
                .employed(true)
                .build();
        
        // Create a person with different details
        Person person4 = new Person.PersonBuilder("Alice Brown")
                .age(25)
                .gender("Female")
                .email("alice.brown@example.com")
                .hobbies(Arrays.asList("Swimming", "Painting", "Cooking"))
                .build();
        
        // Print the created persons
        System.out.println("Person 1 (minimal info):");
        System.out.println(person1);
        
        System.out.println("\nPerson 2 (complete info):");
        System.out.println(person2);
        
        System.out.println("\nPerson 3 (partial info):");
        System.out.println(person3);
        
        System.out.println("\nPerson 4 (different info):");
        System.out.println(person4);
        
        // Demonstrate validation
        try {
            Person invalidPerson = new Person.PersonBuilder("Invalid Person")
                    .age(-5)  // Negative age should throw an exception
                    .build();
        } catch (IllegalArgumentException e) {
            System.out.println("\nValidation Error:");
            System.out.println(e.getMessage());
        }
        
        // Demonstrate immutability
        System.out.println("\nDemonstrating immutability:");
        try {
            // Try to modify the hobbies list
            person2.getHobbies().add("This should not work");
            System.out.println("Hobbies list was modified (not immutable)!");
        } catch (UnsupportedOperationException e) {
            System.out.println("Hobbies list is immutable as expected!");
        }
    }
}