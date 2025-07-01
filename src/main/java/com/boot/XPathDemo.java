package com.boot;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;
import java.util.List;

/**
 * This class demonstrates various XPath axes and CSS selectors
 * for navigating the DOM of the Rediff website.
 */
public class XPathDemo {

    public static void main(String[] args) {
        // Setup ChromeDriver using WebDriverManager with auto-detection for Chrome 137
        WebDriverManager.chromedriver()
            .clearDriverCache() // Clear any cached drivers to ensure we get the right one
            .clearResolutionCache() // Clear resolution cache for fresh detection
            .setup();

        System.out.println("WebDriverManager has set up ChromeDriver for Chrome version: " + 
                          WebDriverManager.chromedriver().getDownloadedDriverVersion());

        // Configure ChromeOptions for compatibility with newer Chrome versions
        ChromeOptions options = new ChromeOptions();

        // Essential options for Chrome 137+
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");

        // Disable automation flags and other settings that might cause issues
        options.setExperimentalOption("excludeSwitches", 
            new String[]{"enable-automation", "enable-logging"});
        options.setExperimentalOption("useAutomationExtension", false);

        // Add logging preference to minimize console output
        options.addArguments("--log-level=3"); // Set log level to errors only

        // Initialize ChromeDriver with the configured options
        System.setProperty("webdriver.chrome.silentOutput", "true"); // Reduce logging
        WebDriver driver = new ChromeDriver(options);

        try {
            // Maximize window and set implicit wait
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

            // Navigate to Rediff homepage
            driver.get("https://www.rediff.com/");
            System.out.println("Navigated to Rediff homepage");

            // XPath Axes Examples on Rediff homepage
            System.out.println("\n=== XPath Axes Examples on Rediff Homepage ===\n");

            // 1. Parent to Child (Direct Child)
            System.out.println("1. Parent to Child (Direct Child):");
            List<WebElement> directChildren = driver.findElements(By.xpath("//div[@class='navbar']/ul/li"));
            System.out.println("   Found " + directChildren.size() + " direct child elements");
            for (int i = 0; i < Math.min(3, directChildren.size()); i++) {
                System.out.println("   - " + directChildren.get(i).getText());
            }

            // 2. Parent to Child (All Descendants)
            System.out.println("\n2. Parent to Child (All Descendants):");
            List<WebElement> allDescendants = driver.findElements(By.xpath("//div[@class='navbar']//a"));
            System.out.println("   Found " + allDescendants.size() + " descendant link elements");
            for (int i = 0; i < Math.min(3, allDescendants.size()); i++) {
                System.out.println("   - " + allDescendants.get(i).getText());
            }

            // 3. Child to Parent
            System.out.println("\n3. Child to Parent:");
            WebElement childElement = driver.findElement(By.xpath("//a[contains(text(), 'Money')]"));
            WebElement parentElement = driver.findElement(By.xpath("//a[contains(text(), 'Money')]/parent::*"));
            System.out.println("   Child element: " + childElement.getText());
            System.out.println("   Parent element tag: " + parentElement.getTagName());

            // 4. Child to Ancestor
            System.out.println("\n4. Child to Ancestor:");
            WebElement ancestor = driver.findElement(By.xpath("//a[contains(text(), 'Money')]/ancestor::div[1]"));
            System.out.println("   Ancestor element class: " + ancestor.getAttribute("class"));

            // 5. Following-sibling
            System.out.println("\n5. Following-sibling (Child to Child):");
            List<WebElement> followingSiblings = driver.findElements(By.xpath("//a[contains(text(), 'Money')]/following-sibling::*"));
            System.out.println("   Found " + followingSiblings.size() + " following siblings");
            for (int i = 0; i < Math.min(3, followingSiblings.size()); i++) {
                System.out.println("   - " + followingSiblings.get(i).getTagName() + ": " + followingSiblings.get(i).getText());
            }

            // 6. Preceding-sibling
            System.out.println("\n6. Preceding-sibling (Child to Child):");
            List<WebElement> precedingSiblings = driver.findElements(By.xpath("//a[contains(text(), 'Money')]/preceding-sibling::*"));
            System.out.println("   Found " + precedingSiblings.size() + " preceding siblings");
            for (int i = 0; i < Math.min(3, precedingSiblings.size()); i++) {
                System.out.println("   - " + precedingSiblings.get(i).getTagName() + ": " + precedingSiblings.get(i).getText());
            }

            // CSS Selectors on Rediff homepage
            System.out.println("\n=== CSS Selectors Examples on Rediff Homepage ===\n");

            // 1. Direct Child CSS Selector
            System.out.println("1. Direct Child CSS Selector:");
            List<WebElement> directChildrenCSS = driver.findElements(By.cssSelector("div.navbar > ul > li"));
            System.out.println("   Found " + directChildrenCSS.size() + " direct child elements");
            for (int i = 0; i < Math.min(3, directChildrenCSS.size()); i++) {
                System.out.println("   - " + directChildrenCSS.get(i).getText());
            }

            // 2. Descendant CSS Selector
            System.out.println("\n2. Descendant CSS Selector:");
            List<WebElement> allDescendantsCSS = driver.findElements(By.cssSelector("div.navbar a"));
            System.out.println("   Found " + allDescendantsCSS.size() + " descendant link elements");
            for (int i = 0; i < Math.min(3, allDescendantsCSS.size()); i++) {
                System.out.println("   - " + allDescendantsCSS.get(i).getText());
            }

            // Navigate to Money Gainers page
            System.out.println("\nNavigating to Money Gainers page...");
            driver.get("https://money.rediff.com/gainers");
            System.out.println("Navigated to Money Gainers page");

            // XPath Axes Examples on Money Gainers page
            System.out.println("\n=== XPath Axes Examples on Money Gainers Page ===\n");

            // 1. Parent to Child (Table rows)
            System.out.println("1. Parent to Child (Table rows):");
            List<WebElement> tableRows = driver.findElements(By.xpath("//table[@class='dataTable']/tbody/tr"));
            System.out.println("   Found " + tableRows.size() + " table rows");
            for (int i = 1; i < Math.min(4, tableRows.size()); i++) { // Skip header row
                WebElement companyCell = driver.findElement(By.xpath("//table[@class='dataTable']/tbody/tr[" + (i+1) + "]/td[1]"));
                System.out.println("   - " + companyCell.getText());
            }

            // 2. Child to Parent (From company name to row)
            System.out.println("\n2. Child to Parent (From company name to row):");
            WebElement firstCompany = driver.findElement(By.xpath("//table[@class='dataTable']/tbody/tr[2]/td[1]/a"));
            WebElement companyRow = driver.findElement(By.xpath("//table[@class='dataTable']/tbody/tr[2]/td[1]/a/parent::td/parent::tr"));
            System.out.println("   Company: " + firstCompany.getText());
            System.out.println("   Row cells count: " + driver.findElements(By.xpath("//table[@class='dataTable']/tbody/tr[2]/td")).size());

            // 3. Following-sibling (Price cell from company name cell)
            System.out.println("\n3. Following-sibling (Price cell from company name cell):");
            WebElement priceCell = driver.findElement(By.xpath("//table[@class='dataTable']/tbody/tr[2]/td[1]/following-sibling::td[3]"));
            System.out.println("   Price: " + priceCell.getText());

            // 4. Ancestor (From company link to table)
            System.out.println("\n4. Ancestor (From company link to table):");
            WebElement tableAncestor = driver.findElement(By.xpath("//table[@class='dataTable']/tbody/tr[2]/td[1]/a/ancestor::table"));
            System.out.println("   Table class: " + tableAncestor.getAttribute("class"));

            // CSS Selectors on Money Gainers page
            System.out.println("\n=== CSS Selectors Examples on Money Gainers Page ===\n");

            // 1. Direct Child CSS Selector (Table rows)
            System.out.println("1. Direct Child CSS Selector (Table rows):");
            List<WebElement> tableRowsCSS = driver.findElements(By.cssSelector("table.dataTable > tbody > tr"));
            System.out.println("   Found " + tableRowsCSS.size() + " table rows");
            for (int i = 1; i < Math.min(4, tableRowsCSS.size()); i++) { // Skip header row
                WebElement companyCellCSS = driver.findElement(By.cssSelector("table.dataTable > tbody > tr:nth-child(" + (i+1) + ") > td:first-child"));
                System.out.println("   - " + companyCellCSS.getText());
            }

            // 2. Nth-child and Nth-of-type CSS Selectors
            System.out.println("\n2. Nth-child and Nth-of-type CSS Selectors:");
            WebElement secondRowThirdCell = driver.findElement(By.cssSelector("table.dataTable > tbody > tr:nth-child(3) > td:nth-child(4)"));
            System.out.println("   Second row, third cell value: " + secondRowThirdCell.getText());

            // 3. Adjacent Sibling CSS Selector
            System.out.println("\n3. Adjacent Sibling CSS Selector:");
            WebElement adjacentCell = driver.findElement(By.cssSelector("table.dataTable > tbody > tr:nth-child(3) > td:first-child + td"));
            System.out.println("   Cell adjacent to company name: " + adjacentCell.getText());

        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Close the browser
            System.out.println("\nClosing browser...");
            driver.quit();
        }
    }
}
