package tv9_digital_project;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ElectionPage_test extends BaseClass {

	private static final String URL = "https://www.tv9hindi.com/elections";//url
	
	@BeforeMethod
	
	public void beforeMethod() {
	 
	   
	        driver.get(URL);
	        System.out.println(" Navigated to URL:  " + URL );
	        
	        //Thread.sleep(5000);
	        waitForPageload();
	        
	        logCurrentUrl(driver);// it will get the url of each page
	        getCurrentDateTime();
	       // printInfo();
	    
	
	// Check if the page is using HTTPS
	        
    if (isPageUsingHttps(driver)) {
        System.out.println("The page is secure and uses HTTPS.");
    } else {
        System.out.println("Warning: The page does not use HTTPS.");
    }

}
	
	@Test(priority = 1 ,retryAnalyzer = tv9_digital_project.RetryAnalyzer.class)
    public void testCoreWebVitals() {
        System.out.println("Fetching Core Web Vitals...");
        Map<String, Object> coreWebVitals = getCoreWebVitals(driver);

        // Define acceptable performance thresholds
        double lcpThreshold = 2500; // 2.5 seconds
        double fidThreshold = 100;  // 0.1 seconds
        double clsThreshold = 0.1;  // 0.1

        double lcp = ((Number) coreWebVitals.getOrDefault("lcp", 0)).doubleValue();
        double fid = ((Number) coreWebVitals.getOrDefault("fid", 0)).doubleValue();
        double cls = ((Number) coreWebVitals.getOrDefault("cls", 0)).doubleValue();

        // Assert that each metric is within the acceptable threshold
        Assert.assertTrue(lcp <= lcpThreshold, "LCP exceeds threshold (" + lcp + " ms > " + lcpThreshold + " ms)");
        Assert.assertTrue(fid <= fidThreshold, "FID exceeds threshold (" + fid + " ms > " + fidThreshold + " ms)");
        Assert.assertTrue(cls <= clsThreshold, "CLS exceeds threshold (" + cls + " > " + clsThreshold + ")");

        // Print the core web vitals for reference
        System.out.println("Largest Contentful Paint (LCP): " + lcp + " ms");
        System.out.println("First Input Delay (FID): " + fid + " ms");
        System.out.println("Cumulative Layout Shift (CLS): " + cls);
    }
	 @Test(priority = 2)
	    public void testElectionHomePage() {
	        System.out.println("-----Validating Election Home Page------");
	        Assert.assertTrue(driver.getTitle().contains("Elections 2024"), "Homepage title validation failed");
	    }
	 
	      
	 @Test(priority= 3)
	    public void testElections2024() {
		 validateAndClick("div[class='sp9Navbar_Main'] a[title='चुनाव 2024']","Elections 2024");//elections 2024
	    }
	 
	 
	 @Test(dependsOnMethods = "testElectionHomePage" ,priority =4)
	 public void testAssemblyElections() {
		 validateAndClick("div[class='sp9Navbar_Main'] a[title='विधानसभा']","Assembly Elections");//assembly election
	 }
	 
	 
	 @Test(dependsOnMethods ="testElectionHomePage",priority=5)
		public void testMaharashtra(){
		 validateAndClick("div[class='sp9Navbar_Main'] a[title='महाराष्ट्र']","Maharashtra");//Maharashtra
	 }
	 
	 
	 @Test(dependsOnMethods ="testElectionHomePage",priority=6)
		public void testJharkhand(){
		 validateAndClick("div[class='sp9Navbar_Main'] a[title='झारखण्ड']","Jharkhand");//jharkhand
	 }
	 
	 
	 @Test(dependsOnMethods ="testElectionHomePage", priority=7)
			 public void testElectionConstituencies() {
		 validateAndClick("div[class='sp9Navbar_Main'] a[title='विधानसभा क्षेत्र']","Election Constituencies");//election constituencies
	 }
	 
	 
	 @Test(dependsOnMethods= "testElectionHomePage" ,priority=8)
	 public void testElelctionCandidates() {
		 validateAndClick("div[class='sp9Navbar_Main'] a[title='प्रत्याशी']","Election Candidates");//election candidates
	 }
	 
	 
	 @Test(dependsOnMethods ="testElectionHomePage" , priority=9)
	 public void testElectionSchedule() {
		 validateAndClick("div[class='sp9Navbar_Main'] a[title='शेड्यूल']","Schedule");//schedule
	 }
	 
	 
	 @Test(dependsOnMethods ="testElectionHomePage" , priority=10)
	 public void testLoksabhaElections() {
		 validateAndClick("div[class='sp9Navbar_Main'] a[title='लोकसभा']","Loksabha Elections");//Loksabha Election
	 }
	 
	 
	 @Test(dependsOnMethods ="testElectionHomePage",priority=11)
	 public void testElectionParty() {
		 validateAndClick("div[class='sp9Navbar_Main'] a[title='पार्टी']","Election Party");//Election Party
	 }
	 
	
	   @AfterMethod
	   
	/* private void performAction(String cssSelector) {
	        System.out.println("Attempting to click element with selector: " + cssSelector);
	        boolean actionComplete = clickElement(By.cssSelector(cssSelector));
	        Assert.assertTrue(actionComplete, "Failed to click on element: " + cssSelector);
	        waitForPageload();
	    }*/
	   
	   public void logTestDuration(ITestResult result) {
	    	
	        String testName = result.getMethod().getMethodName();
	        String status = result.isSuccess() ? "PASSED" : "FAILED";
	        long duration =System.currentTimeMillis()-startTime;
	        writeToNotepad(testName, duration, status);
	        System.out.println("Test '" + testName + "' status: " + status + ", duration: " + duration + " ms");
	    }

	   /* private boolean clickElement(By by) {
	        try {
	            wait.until(ExpectedConditions.elementToBeClickable(by)).click();
	            System.out.println("Element clicked: " + by);
	            return true;
	        } catch (Exception e) {
	            System.err.println("Failed to click on element: " + by + " - " + e.getMessage());
	            takeScreenshot("error-click-element.png");
	            return false;
	        }
	    } */

	    private void printInfo() {
	        String windowHandle = driver.getWindowHandle();
	        System.out.println("The handle of the website is: " + windowHandle);

	        String actualTitle = driver.getTitle();
	        System.out.println("The title of the webpage is: " + actualTitle);

	        String[] keywords = {
	            "Vidhan Sabha Chunav 2024",
	            "Maharashtra (MH)",
	            "Today Hindi News",
	            "Jharkhand (JH)",
	            "Assembly Polls Latest News,",
	            "Photos and Videos in Hindi",
	            "TV9 Bharatvarsh"
	        };

	        boolean titleMatches = false;
	        for (String keyword : keywords) {
	            if (actualTitle.contains(keyword)) {
	                titleMatches = true;
	                break;
	            }
	        }

	        if (titleMatches) {
	            System.out.println("TITLE VERIFICATION SUCCESSFUL");
	        } else {
	            System.out.println("TITLE VERIFICATION FAILED");
	            
	            takeScreenshot("title-verification-failed.png");
	        }
	    }

	    public void takeScreenshot(String fileName) {
	        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	        try {
	            FileUtils.copyFile(screenshot, new File("./screenshots/" + fileName));
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	
	    @AfterSuite
		public void afterSuite() {
	    	
			System.out.println("----- Test Suite Completed -----");
		}
       
	    private void validateAndClick(String cssSelector, String elementDescription) {
	        try {
	            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(cssSelector)));
	            element.click();
	            System.out.println("Clicked on " + elementDescription);
	            waitForPageload();
	        } catch (Exception e) {
	            System.err.println("Failed to click on " + elementDescription + ": " + e.getMessage());
	            takeScreenshot("error-" + elementDescription + ".png");
	        }
	    }
	
	
	
	
}