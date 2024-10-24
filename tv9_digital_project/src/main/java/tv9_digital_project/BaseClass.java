package tv9_digital_project;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass extends WebDriverUtility {

	public static WebDriverUtility wUtil = new WebDriverUtility();
	protected WebDriver driver;
    protected WebDriverWait wait;
    private static final int TIMEOUT = 15;
    
    @BeforeSuite(groups ={"SmokeSuite","RegressionSuite"})//(alwaysRun= true)
	public void bsConfig() {
		System.out.println("----- database connection successful -----");
	}
    @BeforeClass
    public void setUp() {
    	try {
    		
        WebDriverManager.firefoxdriver().setup();// setup browser 
        
        driver = new FirefoxDriver();//launch firefox
        
        maximizeWindow(driver);//maximise window
        
        wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));
        //validation of browser setup
        System.out.println("Driver Setup complete");
    }
    	catch (Exception e){
    		System.err.println("Driver setup faied"+e.getMessage());
    		
    	}

    }

    @AfterClass(alwaysRun=true)
    public void tearDown() {
    	closeBrowser(driver);
    }
    protected void waitForPageload() {
    	wait.until(webDriver->((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));	
    	
    }
    
    @AfterSuite(groups ={"SmokeSuite","RegressionSuite"}) //(alwaysRun= true)
	public void asConfig() {
		System.out.println("----- database closed successfully -----");
	}

}