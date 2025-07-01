# Design Patterns Deep Dive: Singleton and Builder Patterns
*Date: July 2nd, 2025*

## Table of Contents
- [Singleton Pattern](#singleton-pattern)
  - [What is the Singleton Pattern?](#what-is-the-singleton-pattern)
  - [Class Diagram](#singleton-class-diagram)
  - [Implementation in Java](#singleton-implementation-in-java)
  - [Thread Safety Considerations](#thread-safety-considerations)
  - [Usage in Spring/Spring Boot](#singleton-in-spring)
  - [Usage in Selenium](#singleton-in-selenium)
- [Builder Pattern](#builder-pattern)
  - [What is the Builder Pattern?](#what-is-the-builder-pattern)
  - [Class Diagram](#builder-class-diagram)
  - [Implementation in Java](#builder-implementation-in-java)
  - [Comparison with Other Patterns](#comparison-with-other-patterns)
  - [Usage in Spring/Spring Boot](#builder-in-spring)
  - [Usage in Selenium](#builder-in-selenium)
  - [Person Example with Builder Pattern](#person-example)

## Singleton Pattern

### What is the Singleton Pattern?

The Singleton pattern is one of the simplest design patterns in software engineering. It belongs to the creational pattern category and ensures that **a class has only one instance** while providing a global point of access to that instance.

In simple words, the Singleton pattern is like having just one special key to a room that everyone needs to use. Instead of making multiple keys (instances), we create just one key and let everyone use that same key when they need to access the room.

#### Real-World Analogy

Think of a country's president. A country can have only one president at a time. Regardless of who needs to interact with the president, they're all dealing with the same person.

#### When to Use the Singleton Pattern

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

### Singleton Class Diagram

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

### Singleton Implementation in Java

#### Basic Implementation

```java
public class BasicSingleton {
    // The sole instance of the class
    private static BasicSingleton instance;
    
    // Private constructor prevents instantiation from other classes
    private BasicSingleton() {
        System.out.println("BasicSingleton is created!");
    }
    
    // Public static method to get the instance
    public static BasicSingleton getInstance() {
        // Lazy initialization: create the instance only when needed
        if (instance == null) {
            instance = new BasicSingleton();
        }
        return instance;
    }
    
    // Business method
    public void showMessage() {
        System.out.println("Hello from BasicSingleton!");
    }
}
```

#### Usage Example

```java
public class SingletonDemo {
    public static void main(String[] args) {
        // Cannot do this: BasicSingleton singleton = new BasicSingleton();
        
        // Get the only instance
        BasicSingleton singleton = BasicSingleton.getInstance();
        singleton.showMessage();
        
        // Try to get another instance - will return the same one
        BasicSingleton anotherReference = BasicSingleton.getInstance();
        
        // Both references point to the same object
        System.out.println("Are both references the same object? " + 
                          (singleton == anotherReference));
    }
}
```

Output:
```
BasicSingleton is created!
Hello from BasicSingleton!
Are both references the same object? true
```

### Thread Safety Considerations

The basic implementation above works fine in a single-threaded environment, but it can create multiple instances if accessed by multiple threads simultaneously. Here are different approaches to make it thread-safe:

#### 1. Eager Initialization

```java
public class EagerSingleton {
    // Instance is created at class loading time
    private static final EagerSingleton INSTANCE = new EagerSingleton();
    
    private EagerSingleton() {
        System.out.println("EagerSingleton is created!");
    }
    
    public static EagerSingleton getInstance() {
        return INSTANCE;
    }
}
```

Pros:
- Thread-safe without synchronization
- Simple implementation

Cons:
- Instance is created even if it's never used
- Cannot handle exceptions in the constructor

#### 2. Synchronized Method

```java
public class SynchronizedSingleton {
    private static SynchronizedSingleton instance;
    
    private SynchronizedSingleton() {
        System.out.println("SynchronizedSingleton is created!");
    }
    
    // Synchronized method to prevent multiple threads from creating instances
    public static synchronized SynchronizedSingleton getInstance() {
        if (instance == null) {
            instance = new SynchronizedSingleton();
        }
        return instance;
    }
}
```

Pros:
- Thread-safe
- Lazy initialization

Cons:
- Performance overhead due to synchronization on every call

#### 3. Double-Checked Locking

```java
public class DoubleCheckedSingleton {
    // volatile ensures visibility across threads
    private static volatile DoubleCheckedSingleton instance;
    
    private DoubleCheckedSingleton() {
        System.out.println("DoubleCheckedSingleton is created!");
    }
    
    public static DoubleCheckedSingleton getInstance() {
        // Check 1: No synchronization needed if instance exists
        if (instance == null) {
            // Synchronize only when instance might be null
            synchronized (DoubleCheckedSingleton.class) {
                // Check 2: Double-check within synchronized block
                if (instance == null) {
                    instance = new DoubleCheckedSingleton();
                }
            }
        }
        return instance;
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

#### 4. Bill Pugh Singleton (Static Inner Class)

```java
public class BillPughSingleton {
    private BillPughSingleton() {
        System.out.println("BillPughSingleton is created!");
    }
    
    // Inner static helper class
    private static class SingletonHelper {
        // Instance is created inside the helper class
        private static final BillPughSingleton INSTANCE = new BillPughSingleton();
    }
    
    public static BillPughSingleton getInstance() {
        return SingletonHelper.INSTANCE;
    }
}
```

Pros:
- Thread-safe without synchronization
- Lazy initialization (inner class is loaded only when getInstance is called)
- Simple and elegant

Cons:
- Cannot handle parameterized construction easily

#### 5. Enum Singleton (Java 5+)

```java
public enum EnumSingleton {
    INSTANCE;
    
    // Constructor is automatically private in enums
    EnumSingleton() {
        System.out.println("EnumSingleton is created!");
    }
    
    public void showMessage() {
        System.out.println("Hello from EnumSingleton!");
    }
}
```

Usage:
```java
EnumSingleton.INSTANCE.showMessage();
```

Pros:
- Thread-safe by design
- Serialization handled by JVM
- Protected from reflection attacks
- Simplest implementation

Cons:
- Less flexible (cannot extend other classes)
- Eager initialization

### Singleton in Spring

Spring Framework treats beans as singletons by default. When you define a bean in Spring, it creates only one instance of that bean and reuses it whenever that bean is needed.

#### Spring Bean Singleton Example

```java
@Configuration
public class AppConfig {
    
    @Bean
    public DatabaseConnection databaseConnection() {
        return new DatabaseConnection();
    }
}
```

In this example, Spring will create only one instance of `DatabaseConnection` and reuse it throughout the application.

#### Scope Annotation

You can explicitly define a bean as singleton using the `@Scope` annotation:

```java
@Component
@Scope("singleton")
public class UserService {
    // ...
}
```

Or using the constant:

```java
@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class UserService {
    // ...
}
```

#### Spring Boot Singleton Components

In Spring Boot, any component annotated with `@Component`, `@Service`, `@Repository`, or `@Controller` is a singleton by default:

```java
@Service
public class ProductService {
    // This is a singleton by default
}
```

### Singleton in Selenium

In Selenium WebDriver automation, the Singleton pattern is commonly used to manage WebDriver instances to ensure that only one browser window is used throughout the test suite.

#### WebDriver Singleton Example

```java
public class WebDriverSingleton {
    private static WebDriver driver;
    
    private WebDriverSingleton() {
        // Private constructor
    }
    
    public static WebDriver getDriver() {
        if (driver == null) {
            // Initialize the driver
            System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        }
        return driver;
    }
    
    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
```

Usage in test classes:

```java
public class LoginTest {
    
    @Test
    public void testLogin() {
        WebDriver driver = WebDriverSingleton.getDriver();
        driver.get("https://example.com/login");
        // Perform login actions
    }
    
    @AfterClass
    public static void tearDown() {
        WebDriverSingleton.quitDriver();
    }
}
```

Benefits:
- Ensures all tests use the same browser session
- Reduces resource usage by not creating multiple browser instances
- Simplifies WebDriver management

## Builder Pattern

### What is the Builder Pattern?

The Builder pattern is a creational design pattern that separates the construction of a complex object from its representation. It allows you to create different variations of an object while avoiding constructor pollution.

In simple words, the Builder pattern is like ordering a custom meal at a restaurant. Instead of having one big constructor with all possible ingredients, you can specify exactly what you want step by step (e.g., "I want a burger with cheese, no onions, extra sauce").

#### Real-World Analogy

Think of building a custom house. Instead of trying to specify every detail at once, you work with a builder who helps you construct the house step by step - first the foundation, then the walls, the roof, and finally the interior details.

#### When to Use the Builder Pattern

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

### Builder Class Diagram

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
```

Key components:
1. **Product**: The complex object being built
2. **Builder**: Abstract interface for creating parts of the Product
3. **ConcreteBuilder**: Implements Builder to construct and assemble parts of the product
4. **Director** (optional): Constructs the object using the Builder interface

### Builder Implementation in Java

#### Basic Implementation

Let's implement a `Person` class with name, age, and gender using the Builder pattern:

```java
public class Person {
    // Required parameters
    private final String name;
    
    // Optional parameters
    private final int age;
    private final String gender;
    private final String address;
    private final String phoneNumber;
    private final String email;
    
    private Person(PersonBuilder builder) {
        this.name = builder.name;
        this.age = builder.age;
        this.gender = builder.gender;
        this.address = builder.address;
        this.phoneNumber = builder.phoneNumber;
        this.email = builder.email;
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
    
    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
    
    // Builder class
    public static class PersonBuilder {
        // Required parameters
        private final String name;
        
        // Optional parameters - initialized with default values
        private int age = 0;
        private String gender = "Unspecified";
        private String address = "";
        private String phoneNumber = "";
        private String email = "";
        
        public PersonBuilder(String name) {
            this.name = name;
        }
        
        public PersonBuilder age(int age) {
            this.age = age;
            return this;
        }
        
        public PersonBuilder gender(String gender) {
            this.gender = gender;
            return this;
        }
        
        public PersonBuilder address(String address) {
            this.address = address;
            return this;
        }
        
        public PersonBuilder phoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }
        
        public PersonBuilder email(String email) {
            this.email = email;
            return this;
        }
        
        public Person build() {
            return new Person(this);
        }
    }
}
```

#### Usage Example

```java
public class BuilderDemo {
    public static void main(String[] args) {
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
                .build();
        
        // Create a person with some details
        Person person3 = new Person.PersonBuilder("Bob Johnson")
                .age(45)
                .gender("Male")
                .build();
        
        System.out.println(person1);
        System.out.println(person2);
        System.out.println(person3);
    }
}
```

Output:
```
Person{name='John Doe', age=0, gender='Unspecified', address='', phoneNumber='', email=''}
Person{name='Jane Smith', age=30, gender='Female', address='123 Main St, City, Country', phoneNumber='555-1234', email='jane.smith@example.com'}
Person{name='Bob Johnson', age=45, gender='Male', address='', phoneNumber='', email=''}
```

### Comparison with Other Patterns

#### Builder vs. Factory Method

- **Factory Method**: Creates objects through inheritance and polymorphism
- **Builder**: Focuses on constructing complex objects step by step
- **When to choose**: Use Factory when the focus is on creating different types of objects; use Builder when the focus is on constructing a complex object with many optional parameters

#### Builder vs. Abstract Factory

- **Abstract Factory**: Creates families of related objects
- **Builder**: Constructs complex objects step by step
- **When to choose**: Use Abstract Factory when you need to create families of related objects; use Builder when you need to create a complex object with many configurations

### Builder in Spring

Spring Framework uses the Builder pattern in various components:

#### UriComponentsBuilder

Spring provides `UriComponentsBuilder` to build URIs in a fluent way:

```java
URI uri = UriComponentsBuilder.newInstance()
    .scheme("https")
    .host("example.com")
    .path("/products")
    .queryParam("category", "electronics")
    .queryParam("sort", "price")
    .build()
    .toUri();
```

#### MockMvcRequestBuilders

In Spring testing, `MockMvcRequestBuilders` uses the Builder pattern:

```java
mockMvc.perform(MockMvcRequestBuilders.get("/api/users")
    .contentType(MediaType.APPLICATION_JSON)
    .header("Authorization", "Bearer token")
    .param("page", "1")
    .param("size", "10"));
```

#### Spring Security Configuration

Spring Security uses a builder-like pattern for configuration:

```java
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

### Builder in Selenium

Selenium WebDriver uses the Builder pattern extensively:

#### WebDriver Options and Window Management

```java
WebDriver driver = new ChromeDriver();
driver.manage().window().maximize();
driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
```

#### Actions Builder

The `Actions` class in Selenium uses the Builder pattern for creating complex user interactions:

```java
WebElement element = driver.findElement(By.id("draggable"));
WebElement target = driver.findElement(By.id("droppable"));

new Actions(driver)
    .clickAndHold(element)
    .moveToElement(target)
    .release()
    .build()
    .perform();
```

#### ChromeOptions Builder

```java
ChromeOptions options = new ChromeOptions();
options.addArguments("--start-maximized");
options.addArguments("--disable-notifications");
options.addArguments("--incognito");
options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));

WebDriver driver = new ChromeDriver(options);
```

### Person Example with Builder Pattern

Let's create a complete example of a `Person` class with a builder pattern that we can use in a real application:

```java
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
    
    // Getters
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
    
    // Builder class
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
        
        public PersonBuilder(String name) {
            this.name = name;
        }
        
        public PersonBuilder age(int age) {
            this.age = age;
            return this;
        }
        
        public PersonBuilder gender(String gender) {
            this.gender = gender;
            return this;
        }
        
        public PersonBuilder address(String address) {
            this.address = address;
            return this;
        }
        
        public PersonBuilder phoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }
        
        public PersonBuilder email(String email) {
            this.email = email;
            return this;
        }
        
        public PersonBuilder employed(boolean employed) {
            this.employed = employed;
            return this;
        }
        
        public PersonBuilder addHobby(String hobby) {
            this.hobbies.add(hobby);
            return this;
        }
        
        public PersonBuilder hobbies(List<String> hobbies) {
            this.hobbies = new ArrayList<>(hobbies);
            return this;
        }
        
        public Person build() {
            // Validate the data if needed
            if (age < 0) {
                throw new IllegalArgumentException("Age cannot be negative");
            }
            
            return new Person(this);
        }
    }
}
```

#### Usage in a Real Application

```java
public class PersonRegistry {
    private List<Person> people = new ArrayList<>();
    
    public void registerPerson(Person person) {
        people.add(person);
        System.out.println("Registered: " + person.getName());
    }
    
    public static void main(String[] args) {
        PersonRegistry registry = new PersonRegistry();
        
        // Register a person with minimal information
        Person john = new Person.PersonBuilder("John Doe")
                .build();
        registry.registerPerson(john);
        
        // Register a person with complete information
        Person jane = new Person.PersonBuilder("Jane Smith")
                .age(28)
                .gender("Female")
                .address("456 Oak St, Springfield")
                .phoneNumber("555-6789")
                .email("jane.smith@example.com")
                .employed(true)
                .addHobby("Reading")
                .addHobby("Hiking")
                .build();
        registry.registerPerson(jane);
        
        // Register a person with partial information
        Person bob = new Person.PersonBuilder("Bob Johnson")
                .age(45)
                .employed(true)
                .addHobby("Fishing")
                .build();
        registry.registerPerson(bob);
    }
}
```

This example demonstrates how the Builder pattern allows you to create objects with different combinations of properties without needing multiple constructors or setters, resulting in more readable and maintainable code.