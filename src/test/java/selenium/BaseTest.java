package selenium;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.Properties;

import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.j2objc.annotations.Property;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest 
{		
		public static WebDriver driver;
		public static Properties p;
		public static Properties mainProp;
		public static FileInputStream fis;
		public static Properties childProp;
		public static Properties orProp;
		public static String projectPath = System.getProperty("user.dir")+"//src/test//resources//";
		public static void init() throws Exception
		{	
			
			fis = new FileInputStream(projectPath+"environment.properties");
			mainProp = new Properties();
			mainProp.load(fis);
			String e = mainProp.getProperty("env");
			System.out.println(e);
			
			fis = new FileInputStream(projectPath+e+".properties");
			childProp = new Properties();
			childProp.load(fis);
			String url = childProp.getProperty("amazonurl");
			System.out.println(url);
			
			System.out.println(System.getProperty("user.dir"));
			fis = new FileInputStream(projectPath+"data.properties");
			p=new Properties();
			p.load(fis);
			//String val=p.getProperty("chromebrowser");
			//System.out.println(val);
			
			fis = new FileInputStream(projectPath+"or.properties");
			orProp = new Properties();
			orProp.load(fis);
			
			fis = new FileInputStream(projectPath+"log4jconfig.properties");
			PropertyConfigurator.configure(fis);
			
			
		}
		
		
		
		public static void launch(String browser)
		{
			//if(browser.equals("chrome"))
			if(p.getProperty(browser).equals("chrome"))
			{	
				WebDriverManager.chromedriver().setup();
				
				ChromeOptions option = new ChromeOptions();
				option.addArguments("user-data-dir=C:\\Users\\alokk\\AppData\\Local\\Google\\Chrome\\User Data\\Profile 25");
				option.addArguments("--disable-notifications");
				option.addArguments("--start-maximized");
				//option.addArguments("--proxy-server=https://192.168.10.1:9090");
				option.addArguments("--ignore-certificate-errors-spki-list");
				driver=new ChromeDriver(option);
				//driver.manage().window().maximize();
			}
			else if(p.getProperty(browser).equals("firefox"))
			{	
				WebDriverManager.firefoxdriver().setup();
				driver=new FirefoxDriver();
				
			}
		}
		
		public static void navigateUrl(String url)
		{
			driver.get(childProp.getProperty(url));
			driver.navigate().to(childProp.getProperty(url));
			//driver.close();
			//driver.quit();
		}
		

		public static void clickElement(String locatorKey) {
			// TODO Auto-generated method stub
			//driver.findElement(By.xpath(orProp.getProperty(locatorKey))).click();
			getElement(locatorKey).click();
		}

		public static void typeText(String locatorKey, String option) {
			// TODO Auto-generated method stub
			//driver.findElement(By.id(orProp.getProperty(locatorKey))).sendKeys(text);
			//driver.findElement(By.name(orProp.getProperty(locatorKey))).sendKeys(text);
			getElement(locatorKey).sendKeys(option);
		}

		public static WebElement getElement(String locatorKey) {
			// TODO Auto-generated method stub
			WebElement element = null;
			
			// Check for Element Presence 
			
			if(!isElementPresent(locatorKey))
				// Report as Failure
				System.out.print("Element is not present"+locatorKey);
			
			element = driver.findElement(getLocator(locatorKey));
			/*if(locatorKey.endsWith("_id")) {
				element = driver.findElement(By.id(orProp.getProperty(locatorKey)));
			}else if(locatorKey.endsWith("_name")) {
				element = driver.findElement(By.name(orProp.getProperty(locatorKey)));
				
			}else if(locatorKey.endsWith("_className")) {
				element = driver.findElement(By.className(orProp.getProperty(locatorKey)));
				
			}else if(locatorKey.endsWith("_linktext")) {
				element = driver.findElement(By.linkText(orProp.getProperty(locatorKey)));
				
			}else if(locatorKey.endsWith("_partiallinktext")) {
				element = driver.findElement(By.partialLinkText(orProp.getProperty(locatorKey)));
				
			}else if(locatorKey.endsWith("_xpath")) {
				element = driver.findElement(By.xpath(orProp.getProperty(locatorKey)));
				
			}else if(locatorKey.endsWith("_css")) {
				element = driver.findElement(By.cssSelector(orProp.getProperty(locatorKey)));
				
			}*/
			
			return element;
		}



		public static boolean isElementPresent(String locatorKey) 
		{
			System.out.println("Checking for Element present : "+locatorKey);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

			try {
				
				wait.until(ExpectedConditions.presenceOfElementLocated(getLocator(locatorKey)));
				/*if(locatorKey.endsWith("_id"))
				{
				wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id(orProp.getProperty(locatorKey))));
				}else if(locatorKey.endsWith("_name"))
				{
				wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.name(orProp.getProperty(locatorKey))));
				}
				else if(locatorKey.endsWith("_class"))
				{
				wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className(orProp.getProperty(locatorKey))));
				}else if(locatorKey.endsWith("_link"))
				{
				wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.linkText(orProp.getProperty(locatorKey))));
				}else if(locatorKey.endsWith("_partiallink"))
				{
				wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.partialLinkText(orProp.getProperty(locatorKey))));
				}else if(locatorKey.endsWith("_xpath"))
				{
				wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(orProp.getProperty(locatorKey))));
				}else if(locatorKey.endsWith("_css"))
				{
				wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(orProp.getProperty(locatorKey))));
				}*/
			} catch (Exception e) {
				// TODO Auto-generated catch block
				return false;
			}
			
			
			return true;
		}



		public static void selectOption(String locatorKey, String option) {
			// TODO Auto-generated method stub
			//driver.findElement(By.id(orProp.getProperty(locatorKey))).sendKeys(option);
			getElement(locatorKey).sendKeys(option);
		}
		
		public static By getLocator(String locatorKey)
		{
			
			By by = null;
			
			if(locatorKey.endsWith("_id"))
			{
				by = By.id(orProp.getProperty(locatorKey));
			}else if(locatorKey.endsWith("_name"))
			{
				by = By.name(orProp.getProperty(locatorKey));
			}else if(locatorKey.endsWith("_class"))
			{
				by = By.className(orProp.getProperty(locatorKey));
			}else if(locatorKey.endsWith("_link"))
			{
				by = By.linkText(orProp.getProperty(locatorKey));
			}else if(locatorKey.endsWith("_partiallink"))
			{
				by = By.partialLinkText(orProp.getProperty(locatorKey));
			}else if(locatorKey.endsWith("_xpath"))
			{
				by = By.xpath(orProp.getProperty(locatorKey));
			}else if(locatorKey.endsWith("_css"))
			{
				by = By.cssSelector(orProp.getProperty(locatorKey));
			}
			return by;
			
		}
		
}
