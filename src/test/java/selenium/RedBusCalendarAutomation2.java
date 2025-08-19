package selenium;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;
import java.util.List;

public class RedBusCalendarAutomation2 {
    private static WebDriver driver;
    private static WebDriverWait wait;

    public static void main(String[] args) {
        try {
            setupDriver();
            selectDate("25", "Dec", "2025");
        } catch (Exception e) {
            System.out.println("Error occurred: " + e.getMessage());
            e.printStackTrace();
        } finally {
            tearDown();
        }
    }

    private static void setupDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");
        
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        driver.get("https://www.redbus.in/");
        
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static String getDynamicClass(String baseElement) {
        try {
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector(baseElement)));
            String fullClassName = element.getAttribute("class");
            System.out.println("Found element with classes: " + fullClassName);
            
            // Return the full class name if found
            return fullClassName;
        } catch (Exception e) {
            System.out.println("Error finding dynamic class: " + e.getMessage());
            return null;
        }
    }

    private static WebElement findElementWithDynamicClass(String... partialClasses) {
        for (String partialClass : partialClasses) {
            try {
                String[] selectors = {
                    String.format("div[class*='%s']", partialClass),
                    String.format("div[class^='%s']", partialClass),
                    String.format("[class*='%s']", partialClass)
                };
                
                for (String selector : selectors) {
                    try {
                        return wait.until(ExpectedConditions.presenceOfElementLocated(
                            By.cssSelector(selector)));
                    } catch (Exception e) {
                        continue;
                    }
                }
            } catch (Exception e) {
                continue;
            }
        }
        throw new NoSuchElementException("Could not find element with any of the provided classes");
    }

    private static void selectDate(String targetDate, String targetMonth, String targetYear) {
        try {
            // Click calendar icon
            System.out.println("Clicking calendar icon...");
            WebElement calendarIcon = findElementWithDynamicClass("DatePicker", "calendar");
            calendarIcon.click();
            
            // Wait for calendar container
            System.out.println("Waiting for calendar container...");
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("div[class*='DatePicker__CalendarContainer']")));

            // Get current month and year using dynamic classes
            System.out.println("Finding month/year element...");
            WebElement monthYearElement = findElementWithDynamicClass("DayNavigator", "CalendarHeader");
            String currentMonthYear = monthYearElement.getText();
            System.out.println("Current month/year: " + currentMonthYear);
            
            // Navigate to required month and year
            while (!currentMonthYear.contains(targetMonth) || !currentMonthYear.contains(targetYear)) {
                System.out.println("Clicking next month button...");
                
                // Find next button using dynamic class
                WebElement nextButton = findElementWithDynamicClass("IconBlock", "DayNavigator");
                nextButton.click();
                
                Thread.sleep(1000);
                
                monthYearElement = findElementWithDynamicClass("DayNavigator", "CalendarHeader");
                currentMonthYear = monthYearElement.getText();
                System.out.println("Current view: " + currentMonthYear);
            }

            // Multiple approaches to select date
            System.out.println("Selecting date: " + targetDate);
            boolean dateSelected = false;
            
            // Approach 1: Using direct class
            if (!dateSelected) {
                try {
                    List<WebElement> allDates = driver.findElements(
                        By.cssSelector("div[class*='DayTiles'] span"));
                    for (WebElement date : allDates) {
                        if (date.getText().equals(targetDate)) {
                            date.click();
                            dateSelected = true;
                            System.out.println("Selected date using approach 1");
                            break;
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Approach 1 failed: " + e.getMessage());
                }
            }

            // Approach 2: Using JavaScript Executor
            if (!dateSelected) {
                try {
                    String jsScript = "document.evaluate(\"//span[text()='" + targetDate + "']\", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue.click();";
                    ((JavascriptExecutor) driver).executeScript(jsScript);
                    dateSelected = true;
                    System.out.println("Selected date using JavaScript executor");
                } catch (Exception e) {
                    System.out.println("JavaScript approach failed: " + e.getMessage());
                }
            }

            // Verify selection
            Thread.sleep(1000);
            try {
                // Find the selected date element using dynamic classes
                WebElement selectedDateElement = findElementWithDynamicClass("dateText", "DayTiles");
                String selectedDateText = selectedDateElement.getText();
                System.out.println("Selected date text: " + selectedDateText);

                if (selectedDateText.contains(targetDate) && 
                    selectedDateText.contains(targetMonth)) {
                    System.out.println("Successfully selected the date!");
                } else {
                    System.out.println("Date selection verification failed!");
                }
            } catch (Exception e) {
                System.out.println("Error verifying date selection: " + e.getMessage());
            }

        } catch (Exception e) {
            System.out.println("Error in date selection process: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void tearDown() {
        System.out.println("Closing browser...");
        if (driver != null) {
            driver.quit();
        }
    }
}