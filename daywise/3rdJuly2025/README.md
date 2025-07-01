# Builder Design Pattern Deep Dive
*Date: July 3rd, 2025*

## Table of Contents
- [Introduction](#introduction)
- [What is the Builder Pattern?](#what-is-the-builder-pattern)
- [Class Diagram](#class-diagram)
- [When to Use the Builder Pattern](#when-to-use-the-builder-pattern)
- [How Builder Pattern Provides Abstraction](#how-builder-pattern-provides-abstraction)
- [Implementation Examples](#implementation-examples)
  - [Aircraft Builder Example](#aircraft-builder-example)
  - [Document Builder Example](#document-builder-example)
  - [Person Builder Example](#person-builder-example)
- [Builder Pattern in Spring Boot](#builder-pattern-in-spring-boot)
- [Builder Pattern in Selenium](#builder-pattern-in-selenium)
- [Comparison with Other Patterns](#comparison-with-other-patterns)
- [Conclusion](#conclusion)
- [Running the Examples](#running-the-examples)

## Introduction

The Builder pattern is one of the creational design patterns that was introduced by the Gang of Four (GoF) in their book "Design Patterns: Elements of Reusable Object-Oriented Software." This pattern is designed to provide a flexible solution to various object creation problems in object-oriented programming.

## What is the Builder Pattern?

The Builder pattern separates the construction of a complex object from its representation, allowing the same construction process to create different representations. It's particularly useful when an object needs to be created with numerous parameters, some of which might be optional.

In simple terms, the Builder pattern is like ordering a custom meal at a restaurant. Instead of having one big constructor with all possible ingredients, you can specify exactly what you want step by step (e.g., "I want a burger with cheese, no onions, extra sauce").

### Real-World Analogy

Think of building a custom house. Instead of trying to specify every detail at once, you work with a builder who helps you construct the house step by step - first the foundation, then the walls, the roof, and finally the interior details.

## Class Diagram

```
┌───────────────────┐       ┌───────────────────┐
│      Product      │       │      Builder      │
├───────────────────┤       ├───────────────────┤
│ - attribute1      │       │ + buildPart1()    │
│ - attribute2      │◄──────│ + buildPart2()    │
│ - attribute3      │       │ + buildPart3()    │
│                   │       │ + getResult()     │
└───────────────────┘       └───────────────────┘
                                     ▲
                                     │
                            ┌────────┴───────┐
                            │  ConcreteBuilder│
                            ├────────────────┤
                            │ + buildPart1() │
                            │ + buildPart2() │
                            │ + buildPart3() │
                            │ + getResult()  │
                            └────────────────┘
                                     ▲
                                     │
                            ┌────────┴───────┐
                            │    Director    │
                            ├────────────────┤
                            │ + construct()  │
                            └────────────────┘
```

Key components:
1. **Product**: The complex object being built
2. **Builder**: Abstract interface for creating parts of the Product
3. **ConcreteBuilder**: Implements Builder to construct and assemble parts of the product
4. **Director**: Constructs the object using the Builder interface

## When to Use the Builder Pattern

Use the Builder pattern when:
- An object has many optional parameters or configurations
- The construction process should be separate from the object itself
- You need to create different representations of the same object
- You want to prevent "telescoping constructors" (multiple constructors with different parameter combinations)

Examples include:
- Document generators (HTML, PDF, etc.)
- Meal configurations at a restaurant
- Complex query builders
- UI component builders

## How Builder Pattern Provides Abstraction

The Builder pattern provides abstraction in several ways:

1. **Separation of Concerns**: It separates the complex construction process from the representation of the final object. This means the client code doesn't need to know how the object is constructed internally.

2. **Encapsulation of Construction Logic**: The construction logic is encapsulated within the builder classes, hiding the details of how parts are added and assembled.

3. **Interface-Based Construction**: By using an abstract builder interface, the client code can work with any concrete builder without knowing its specific type.

4. **Step-by-Step Construction**: The pattern allows for a step-by-step construction process, where each step can be abstracted and potentially overridden by concrete builders.

5. **Director-Builder Abstraction**: The Director class works with the abstract Builder interface, not concrete implementations, allowing for different builders to be used interchangeably.

## Implementation Examples

### Aircraft Builder Example

The Aircraft Builder example demonstrates how to build different types of aircraft (F-16 fighter jet and Boeing 747 passenger aircraft) using the same construction process.

#### Key Components:

1. **Product**: `IAircraft` interface implemented by `F16` and `Boeing747` classes
2. **Builder**: `AircraftBuilder` abstract class
3. **ConcreteBuilder**: `F16Builder` and `Boeing747Builder` classes
4. **Director**: `Director` class that manages the construction process
5. **Client**: `AircraftClient` class that demonstrates the pattern

#### Code Structure:

```
com.boot.designpatterns.builder.aircraft
├── IAircraft.java (Interface)
├── F16.java (Concrete Product)
├── Boeing747.java (Concrete Product)
├── AircraftBuilder.java (Abstract Builder)
├── F16Builder.java (Concrete Builder)
├── Boeing747Builder.java (Concrete Builder)
├── Director.java (Director)
└── AircraftClient.java (Client)
```

#### How It Works:

1. The `Director` takes an `AircraftBuilder` in its constructor
2. The client code creates concrete builders (`F16Builder` or `Boeing747Builder`)
3. The `Director` calls the builder methods in the correct order
4. The builder constructs the aircraft parts
5. The client gets the final aircraft from the builder

### Document Builder Example

The Document Builder example demonstrates how to build different types of documents (HTML and PDF) using the same construction process.

#### Key Components:

1. **Product**: `IDocument` interface implemented by `HTMLDocument` and `PDFDocument` classes
2. **Builder**: `DocumentBuilder` abstract class
3. **ConcreteBuilder**: `HTMLDocumentBuilder` and `PDFDocumentBuilder` classes
4. **Director**: `DocumentDirector` class that manages the construction process
5. **Client**: `DocumentClient` class that demonstrates the pattern

#### Code Structure:

```
com.boot.designpatterns.builder.document
├── IDocument.java (Interface)
├── HTMLDocument.java (Concrete Product)
├── PDFDocument.java (Concrete Product)
├── DocumentBuilder.java (Abstract Builder)
├── HTMLDocumentBuilder.java (Concrete Builder)
├── PDFDocumentBuilder.java (Concrete Builder)
├── DocumentDirector.java (Director)
└── DocumentClient.java (Client)
```

#### How It Works:

1. The `DocumentDirector` takes a `DocumentBuilder` in its constructor
2. The client code creates concrete builders (`HTMLDocumentBuilder` or `PDFDocumentBuilder`)
3. The `DocumentDirector` calls the builder methods in the correct order
4. The builder constructs the document parts
5. The client gets the final document from the builder

### Person Builder Example

The Person Builder example demonstrates how to build a Person object with many optional parameters using the Builder pattern.

#### Key Components:

1. **Product**: `Person` class
2. **Builder**: `Person.PersonBuilder` inner class
3. **Client**: `BuilderDemo` class that demonstrates the pattern

#### Code Structure:

```
com.boot.designpatterns.builder
├── Person.java (Product with inner Builder class)
└── BuilderDemo.java (Client)
```

#### How It Works:

1. The `Person` class has a private constructor that takes a `PersonBuilder`
2. The `PersonBuilder` inner class provides methods for setting optional parameters
3. Each setter method returns the builder itself for method chaining
4. The `build()` method creates and returns the final `Person` object
5. The client code creates a `PersonBuilder`, sets the desired parameters, and calls `build()`

## Builder Pattern in Spring Boot

Spring Framework uses the Builder pattern in various components:

### UriComponentsBuilder

Spring provides `UriComponentsBuilder` to build URIs in a fluent way:

```
URI uri = UriComponentsBuilder.newInstance()
    .scheme("https")
    .host("example.com")
    .path("/products")
    .queryParam("category", "electronics")
    .queryParam("sort", "price")
    .build()
    .toUri();
```

### MockMvcRequestBuilders

In Spring testing, `MockMvcRequestBuilders` uses the Builder pattern:

```
mockMvc.perform(MockMvcRequestBuilders.get("/api/users")
    .contentType(MediaType.APPLICATION_JSON)
    .header("Authorization", "Bearer token")
    .param("page", "1")
    .param("size", "10"));
```

### Spring Security Configuration

Spring Security uses a builder-like pattern for configuration:

```
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/public/**").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
            .logout()
                .permitAll();
    }
}
```

## Builder Pattern in Selenium

Selenium WebDriver uses the Builder pattern extensively:

### WebDriver Options and Window Management

```
WebDriver driver = new ChromeDriver();
driver.manage().window().maximize();
driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
```

### Actions Builder

The `Actions` class in Selenium uses the Builder pattern for creating complex user interactions:

```
WebElement element = driver.findElement(By.id("draggable"));
WebElement target = driver.findElement(By.id("droppable"));

new Actions(driver)
    .clickAndHold(element)
    .moveToElement(target)
    .release()
    .build()
    .perform();
```

### ChromeOptions Builder

```
ChromeOptions options = new ChromeOptions();
options.addArguments("--start-maximized");
options.addArguments("--disable-notifications");
options.addArguments("--incognito");
options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));

WebDriver driver = new ChromeDriver(options);
```

## Comparison with Other Patterns

### Builder vs. Factory Method

- **Factory Method**: Creates objects through inheritance and polymorphism
- **Builder**: Focuses on constructing complex objects step by step
- **When to choose**: Use Factory when the focus is on creating different types of objects; use Builder when the focus is on constructing a complex object with many optional parameters

### Builder vs. Abstract Factory

- **Abstract Factory**: Creates families of related objects
- **Builder**: Constructs complex objects step by step
- **When to choose**: Use Abstract Factory when you need to create families of related objects; use Builder when you need to create a complex object with many configurations

## Conclusion

The Builder pattern is a powerful tool for creating complex objects with many parameters or configurations. It provides a clean and readable way to construct objects step by step, separating the construction process from the representation. This pattern is widely used in modern frameworks and libraries, such as Spring Boot and Selenium, to provide fluent APIs for creating complex objects.

Key benefits of the Builder pattern:
1. Provides a clear and readable way to create complex objects
2. Allows for step-by-step construction of objects
3. Separates the construction process from the representation
4. Enables the creation of different representations using the same construction process
5. Prevents telescoping constructors and reduces the need for numerous constructor overloads

## Running the Examples

To run the examples, use the provided batch files:

- `run_aircraft_builder_demo.bat`: Runs the Aircraft Builder example
- `run_document_builder_demo.bat`: Runs the Document Builder example
- `run_builder_demo.bat`: Runs the Person Builder example
- `run_all_builder_demos.bat`: Runs all Builder pattern examples

These batch files compile and run the respective examples, demonstrating how the Builder pattern works in practice.