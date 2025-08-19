package selenium;

public class TC_001 extends BaseTest {

	public static void main(String[] args) throws Exception
	{
			
		init();
		launch("chromebrowser");
		//navigateUrl("https://www.amazon.com");
		navigateUrl("iciciurl");
			
			//driver.close();
			//driver.quit();
	}

}
