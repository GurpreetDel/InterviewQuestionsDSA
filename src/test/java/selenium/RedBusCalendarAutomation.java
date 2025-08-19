package selenium;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;
import java.util.List;

public class RedBusCalendarAutomation {
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
        // Setup WebDriver
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");
        
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // Navigate to RedBus
        driver.get("https://www.redbus.in/");
        
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void selectDate(String targetDate, String targetMonth, String targetYear) {
        try {
            // Click calendar icon
            System.out.println("Clicking calendar icon...");
            WebElement calendarIcon = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("i.sc-cSHVUG.NyvQv.icon.icon-datev2")));
            calendarIcon.click();
            
            // Wait for calendar container
            System.out.println("Waiting for calendar container...");
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("div.labelCalendarContainer")));

            // Get current month and year
            WebElement monthYearElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("div[class='DayNavigator__CalendarHeader-qj8jdz-1 fxvMrr'] div:nth-child(2)")));
            String currentMonthYear = monthYearElement.getText();
            System.out.println("Current month/year: " + currentMonthYear);
            
            // Navigate to required month and year
            while (!currentMonthYear.contains(targetMonth) || !currentMonthYear.contains(targetYear)) {
                System.out.println("Clicking next month button...");
                
                WebElement nextButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.cssSelector("div[class='DayNavigator__CalendarHeader-qj8jdz-1 fxvMrr'] div:nth-child(3)")
                ));
                nextButton.click();
                
                Thread.sleep(1000);
                
                monthYearElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector("div[class='DayNavigator__CalendarHeader-qj8jdz-1 fxvMrr'] div:nth-child(2)")
                ));
                currentMonthYear = monthYearElement.getText();
                System.out.println("Current view: " + currentMonthYear);
            }

            // Multiple approaches to select date
            System.out.println("Selecting date: " + targetDate);
            boolean dateSelected = false;
            
            // Approach 1: Direct date search
            if (!dateSelected) {
                try {
                    List<WebElement> allDates = driver.findElements(By.xpath(
                        "//div[contains(@class, 'Day')]//div[text()='" + targetDate + "']"));
                    for (WebElement date : allDates) {
                        if (date.isDisplayed() && date.isEnabled()) {
                            date.click();
                            dateSelected = true;
                            break;
                        }
                    }
                    System.out.println("no errors but dint click on 25 date");
                } catch (Exception e) {
                    System.out.println("Approach 1 failed: " + e.getMessage());
                }
            }
            
            // Approach 3: Using JavaScript Executor
            if (!dateSelected) {
                try {
                    List<WebElement> dateElements = driver.findElements(
                        By.xpath("//*[text()='" + targetDate + "']"));
                    for (WebElement element : dateElements) {
                        if (element.isDisplayed()) {
                            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
                            dateSelected = true;
                            break;
                        }
                    }
                    System.out.println("This 3rd Approach only worked");
                } catch (Exception e) {
                    System.out.println("Approach 3 failed: " + e.getMessage());
                }
            }

            
            

            if (!dateSelected) {
                throw new RuntimeException("Failed to select date after trying all approaches");
            }

            // Verify selection
            Thread.sleep(1000);
            try {
                WebElement selectedDate = driver.findElement(By.cssSelector(".dateText"));
                String selectedDateText = selectedDate.getText();
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