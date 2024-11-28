package tv9_digital_project;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class parallelbrowsertest {

	public class ParallelTest {
	    private WebDriver driver;

	    @BeforeMethod
	    @DataProvider(name="browser")
	    //@Parameters(name="browser")
	    public void setUp(String browser) {
	        if (browser.equalsIgnoreCase("chrome")) {
	            driver = new ChromeDriver();
	        } else if (browser.equalsIgnoreCase("firefox")) {
	            driver = new FirefoxDriver();
	        } else if (browser.equalsIgnoreCase("edge")){
	            	driver = new EdgeDriver();
	            }
	        
	        driver.manage().window().maximize();
	    }

	    @Test
	    public void website() {
	        driver.get("https://www.news9live.com/");
	        // my test code here
	    }

	    @AfterMethod
	    public void tearDown() {
	        if (driver != null) {
	            driver.quit();
	        }
	    }
	}

}
