package selenium;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class WebTablePagination {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://datatables.net/examples/advanced_init/dt_events.html");
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        List<String> allNames = new ArrayList<>();
        
        // Get total pages
        WebElement lastPageButton = driver.findElement(By.cssSelector("div[class='dt-paging'] > nav > button:nth-last-child(3)"));
        int totalPages = Integer.parseInt(lastPageButton.getText());
        
        for (int page = 1; page <= totalPages; page++) {
            // Wait for table to load
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("table#example tbody tr")));
            
            // Get names from current page
            List<WebElement> names = driver.findElements(By.cssSelector("table[class='display dataTable'] > tbody > tr > td:first-child"));
            names.forEach(name -> allNames.add(name.getText()));
            
            if (page < totalPages) {
                // Click next page button
                driver.findElement(By.cssSelector("div[class='dt-paging'] > nav > button.dt-paging-button.next")).click();
                
                // Wait for table update
                Thread.sleep(1000); // Add small delay for table refresh
            }
        }
        
        // Print results
        System.out.println("Total names: " + allNames.size());
        allNames.forEach(System.out::println);
        
        driver.quit();
    }
}