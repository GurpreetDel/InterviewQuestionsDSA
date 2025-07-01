# Singleton Pattern Deep Dive
*Date: July 4th, 2025*

## Table of Contents
- [Introduction](#introduction)
- [What is the Singleton Pattern?](#what-is-the-singleton-pattern)
- [Class Diagram](#class-diagram)
- [AirforceOne Implementation](#airforceone-implementation)
- [Thread Safety Considerations](#thread-safety-considerations)
- [Different Singleton Implementations](#different-singleton-implementations)
  - [Eager Initialization](#eager-initialization)
  - [Synchronized Method](#synchronized-method)
  - [Double-Checked Locking](#double-checked-locking)
  - [Bill Pugh Singleton (Static Inner Class)](#bill-pugh-singleton-static-inner-class)
  - [Enum Singleton](#enum-singleton)
- [When to Use the Singleton Pattern](#when-to-use-the-singleton-pattern)
- [Running the Example](#running-the-example)

## Introduction

The Singleton pattern is one of the simplest design patterns in software engineering. It belongs to the creational pattern category and ensures that a class has only one instance while providing a global point of access to that instance.

## What is the Singleton Pattern?

The Singleton pattern as the name suggests is used to create one and only instance of a class. There are several examples where only a single instance of a class should exist and the constraint be enforced. Caches, thread pools, registries are examples of objects that should only have a single instance.

It's trivial to new-up an object of a class but how do we ensure that only one object ever gets created? The answer is to make the constructor private of the class we intend to define as singleton. That way, only the members of the class can access the private constructor and no one else.

Formally the Singleton pattern is defined as ensuring that only a single instance of a class exists and a global point of access to it exists.

## Class Diagram

The class diagram for the Singleton pattern is very simple, consisting of only a single entity:

```
┌───────────────────────────────┐
│         Singleton             │
├───────────────────────────────┤
│ - instance: Singleton         │
├───────────────────────────────┤
│ - Singleton()                 │
│ + getInstance(): Singleton    │
│ + someBusinessMethod()        │
└───────────────────────────────┘
```

Key components:
1. **Private static instance variable**: Holds the single instance of the class
2. **Private constructor**: Prevents external instantiation
3. **Public static method**: Provides access to the single instance

## AirforceOne Implementation

As an example, let's say we want to model the American President's official aircraft called "Airforce One" in our software. There can only be one instance of Airforce One and a singleton class is the best suited representation.

Here's the implementation of our AirforceOne singleton class:

```java
public class AirforceOne {

    // The sole instance of the class
    private static AirforceOne onlyInstance;

    // Make the constructor private so its only accessible to
    // members of the class.
    private AirforceOne() {
    }

    public void fly() {
        System.out.println("Airforce one is flying...");
    }

    // Create a static method for object creation
    public static AirforceOne getInstance() {

        // Only instantiate the object when needed.
        if (onlyInstance == null) {
            onlyInstance = new AirforceOne();
        }

        return onlyInstance;
    }
}
```

And here's how a client would use this singleton:

```java
public class Client {

    public void main() {
        AirforceOne airforceOne = AirforceOne.getInstance();
        airforceOne.fly();
    }
}
```

## Thread Safety Considerations

The basic implementation shown above works fine in a single-threaded environment, but it can create multiple instances if accessed by multiple threads simultaneously. Here's one example scenario:

1. Thread A calls the method `getInstance()` and finds the `onlyInstance` to be null but before it can actually new-up the instance it gets context switched out.
2. Now thread B comes along and calls the `getInstance()` method and goes on to new-up the instance and returns the AirforceOne object.
3. When thread A is scheduled again, is when the mischief begins. The thread was already past the if null condition check and will proceed to new-up another object of AirforceOne and assign it to `onlyInstance`. Now there are two different AirforceOne objects out in the wild, one with thread A and one with thread B.

There are several ways to address this thread safety issue, as discussed in the next section.

## Different Singleton Implementations

### Eager Initialization

One way to ensure thread safety is to create the instance at class loading time:

```java
public class EagerAirforceOne {
    // Instance is created at class loading time
    private static final EagerAirforceOne INSTANCE = new EagerAirforceOne();

    private EagerAirforceOne() {
    }

    public static EagerAirforceOne getInstance() {
        return INSTANCE;
    }

    public void fly() {
        System.out.println("Eager Airforce one is flying...");
    }
}
```

Pros:
- Thread-safe without synchronization
- Simple implementation

Cons:
- Instance is created even if it's never used
- Cannot handle exceptions in the constructor

### Synchronized Method

Another approach is to use a synchronized method:

```java
public class SynchronizedAirforceOne {
    private static SynchronizedAirforceOne instance;

    private SynchronizedAirforceOne() {
    }

    // Synchronized method to prevent multiple threads from creating instances
    public static synchronized SynchronizedAirforceOne getInstance() {
        if (instance == null) {
            instance = new SynchronizedAirforceOne();
        }
        return instance;
    }

    public void fly() {
        System.out.println("Synchronized Airforce one is flying...");
    }
}
```

Pros:
- Thread-safe
- Lazy initialization

Cons:
- Performance overhead due to synchronization on every call

### Double-Checked Locking

Double-checked locking is an optimization that only synchronizes the first time:

```java
public class DoubleCheckedAirforceOne {
    // volatile ensures visibility across threads
    private static volatile DoubleCheckedAirforceOne instance;

    private DoubleCheckedAirforceOne() {
    }

    public static DoubleCheckedAirforceOne getInstance() {
        // Check 1: No synchronization needed if instance exists
        if (instance == null) {
            // Synchronize only when instance might be null
            synchronized (DoubleCheckedAirforceOne.class) {
                // Check 2: Double-check within synchronized block
                if (instance == null) {
                    instance = new DoubleCheckedAirforceOne();
                }
            }
        }
        return instance;
    }

    public void fly() {
        System.out.println("Double-checked Airforce one is flying...");
    }
}
```

Pros:
- Thread-safe
- Better performance than synchronized method
- Lazy initialization

Cons:
- Complex implementation
- Requires volatile keyword (for Java 5+)

### Bill Pugh Singleton (Static Inner Class)

Bill Pugh came up with a different approach using a static inner class:

```java
public class BillPughAirforceOne {

    private BillPughAirforceOne() {
    }

    // Inner static helper class
    private static class SingletonHelper {
        // Instance is created inside the helper class
        private static final BillPughAirforceOne INSTANCE = new BillPughAirforceOne();
    }

    public static BillPughAirforceOne getInstance() {
        return SingletonHelper.INSTANCE;
    }

    public void fly() {
        System.out.println("Bill Pugh Airforce one is flying...");
    }
}
```

Pros:
- Thread-safe without synchronization
- Lazy initialization (inner class is loaded only when getInstance is called)
- Simple and elegant

Cons:
- Cannot handle parameterized construction easily

### Enum Singleton

Using an enum is the simplest way to implement a singleton in Java:

```java
public enum EnumAirforceOne {
    INSTANCE;

    public void fly() {
        System.out.println("Enum Airforce one is flying...");
    }
}
```

Usage example:

```java
public class EnumSingletonDemo {
    public static void main(String[] args) {
        EnumAirforceOne.INSTANCE.fly();
    }
}
```

Pros:
- Thread-safe by design
- Serialization handled by JVM
- Protected from reflection attacks
- Simplest implementation

Cons:
- Less flexible (cannot extend other classes)
- Eager initialization

## When to Use the Singleton Pattern

Use the Singleton pattern when:
- Exactly one instance of a class is needed
- You need controlled access to a single shared resource (like a database connection)
- You want to prevent multiple instances from causing problems (like opening the same file multiple times)

Examples include:
- Configuration managers
- Connection pools
- Caches
- Logger instances
- Device drivers for printers

## Running the Example

To run the AirforceOne singleton example, use the provided batch file:

```
run_airforce_one_demo.bat
```

This will compile and run the demonstration, showing how the singleton pattern ensures that only one instance of AirforceOne is created.
