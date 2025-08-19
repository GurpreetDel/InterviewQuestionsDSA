package selenium;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;

public class RedBusCalendarTest {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        try {
            driver.manage().window().maximize();
            driver.get("https://www.redbus.in/");
            
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            
            // Click calendar
            wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("i.sc-cSHVUG.NyvQv.icon.icon-datev2"))).click();
            Thread.sleep(2000);
            
         // Get calendar container element first
            WebElement calendarContainer = driver.findElement( By.cssSelector("i.sc-cSHVUG.NyvQv.icon.icon-datev2"));// or adjust class based on dev tools
            
            System.out.println("Calendar container HTML: " + calendarContainer.getAttribute("outerHTML"));

         // After clicking calendar icon
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("debugger;"); // This will pause execution

            // Now add a wait for calendar container
            calendarContainer = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("i.sc-cSHVUG.NyvQv.icon.icon-datev2"))); 

            // Print container details
            System.out.println("Container class: " + calendarContainer.getAttribute("class"));
            System.out.println("Container HTML: " + calendarContainer.getAttribute("outerHTML"));
            
            String targetMonth = "Dec";
            String targetYear = "2025";
            String targetDay = "25";
            
            // Get current month/year
            while(true) {
                String monthYearText = driver.findElement(
                    By.cssSelector("div[class='DayNavigator__CalendarHeader-qj8jdz-1 fxvMrr'] div:nth-child(2)")).getText();
                String[] parts = monthYearText.split(" ");
                
                if(parts[0].equals(targetMonth) && parts[1].equals(targetYear)) {
                    break;
                }
                
                // Click next month
                driver.findElement(By.cssSelector("div[class='DayNavigator__CalendarHeader-qj8jdz-1 fxvMrr'] div:nth-child(3)")).click();
                Thread.sleep(500);
            }
            
            // Select date
            driver.findElement(By.cssSelector(
                String.format("div[class*='DayTiles__CalendarDaysSpan'] span:contains('%s')", targetDay))).click();
            
            Thread.sleep(2000);
            
        } finally {
            driver.quit();
        }
    }
}