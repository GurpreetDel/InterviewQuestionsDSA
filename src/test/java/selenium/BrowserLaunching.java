package selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserLaunching {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Hello");
		WebDriver driver;
		WebDriverManager.chromedriver().setup();
		
		driver = new ChromeDriver();
		driver.get("https://www.google.com");
		
		//WebDriverManager.firefoxdriver().setup();
		
		//driver = new FirefoxDriver();
		driver.close();
		driver.quit();
		//hey baby aww123456789
		
	}

}
