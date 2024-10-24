package tv9_digital_project;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class crossbrowsertest {

	private WebDriver driver;
	
	@DataProvider(name="browsers")
	
	public Object[][] browswers(){
		return new Object[][]{
				{"chrome"},
		        {"firefox"},
		        {"edge"}
				
		};
	}
		@BeforeClass
		
		
		
		 @Test(dataProvider = "browsers")
	    public void setUp(String browser) {
	        switch (browser) {
	            case "chrome":
	                WebDriverManager.chromedriver().setup();
	                driver = new ChromeDriver();
	                break;
	            case "firefox":
	                WebDriverManager.firefoxdriver().setup();
	                driver = new FirefoxDriver();
	                break;
	            case "edge":
	            	WebDriverManager.edgedriver().setup();
	            	driver=new EdgeDriver();
	            	break;
	                
	            	default:
	            		throw new IllegalArgumentException("Browser not supported");
	        }
	        driver.get("https://www.tv9hindi.com/");
	        
		}
		@AfterClass
		 public void teardown() {
			if(driver!=null) {
				driver.close();	
		
	                
       }
	}
}