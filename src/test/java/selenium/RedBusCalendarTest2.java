package selenium;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RedBusCalendarTest2 {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        try {
            driver.manage().window().maximize();
            driver.get("https://www.redbus.in/");
            
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            
            // Click calendar icon
            wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("i.sc-cSHVUG.NyvQv.icon.icon-datev2"))).click();
            Thread.sleep(1500);

            // Wait for calendar container
            wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("div.labelCalendarContainer")));

            // Target date selectors 
            By dateText = By.cssSelector("text.dateText");
            By yearText = By.cssSelector("text.yearText");
            //driver.findElement(By.cssSelector("div[class='DayNavigator__CalendarHeader-qj8jdz-1 fxvMrr'] div:nth-child(3)")).click();
            
            // Get month/year and navigate
            while(true) {
            	driver.findElement(By.cssSelector("div[class='DayNavigator__CalendarHeader-qj8jdz-1 fxvMrr'] div:nth-child(3)")).click();
            	
                String currentDate = driver.findElement(dateText).getText();
                String currentYear = driver.findElement(yearText).getText();
                
                if(currentDate.contains("Dec") && currentYear.equals("2025")) {
                    break;
                }
                
                driver.findElement(By.cssSelector("button.next")).click();
                Thread.sleep(500);
            }

            // Select day
            driver.findElement(By.cssSelector("div[class*='DayTiles'] span:contains('25')")).click();
            
            Thread.sleep(1000);
        } finally {
            driver.quit();
        }
    }
}