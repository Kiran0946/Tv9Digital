package tv9_digital_project;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass extends WebDriverUtility {

	public static WebDriverUtility wUtil = new WebDriverUtility();
	protected WebDriver driver;
    protected WebDriverWait wait;
    private static final int TIMEOUT = 15;
    protected long startTime;  //record the time for each test
    
    
    
    @BeforeMethod
	public void beforeMethod() {
        startTime = System.currentTimeMillis(); // Record the start time of each test
    }
    @BeforeSuite
    public void beforeSuite() {
    	
        System.out.println("----- Starting Test Suite -----");
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
    
    @AfterMethod
    public void afterMethod(ITestResult result) {
        long endTime = System.currentTimeMillis(); // Record the end time of the test
        long duration = endTime - startTime; // Calculate the time taken

        String testName = result.getMethod().getMethodName(); // Get the name of the test
        System.out.println("Test '" + testName + "' took " + TimeUnit.MILLISECONDS.toSeconds(duration) + " seconds to execute.");
        
         
       
    }
    
    
    
    @AfterSuite //(alwaysRun= true)
	public void afterSuite() {
		System.out.println("----- Test Suite Sucessfully Completed  -----");
	}

}