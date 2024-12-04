package tv9_digital_project;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;

import javax.xml.xpath.XPath;

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
	
	public void beforeMethod() throws URISyntaxException {
	 
	   
	        driver.get(URL);//navigation tom url
	        System.out.println(" Navigated to URL: \n " + URL );
	        
	        //Thread.sleep(5000);
	        waitForPageload();
	        
	        logCurrentUrl(driver);// it will get the url of each page
	        getCurrentDateTime();
	        isPageUsingHttps(driver);
	        extractComScoreCode(driver);//verify the comscore script
	        extractChartbeatCode(driver);//verify the chartbeat script
	        extractIzootoCode(driver);//verify the izooto script
	        extractGoogleManager(driver);//verify gt script
	       // printInfo();
	    
	
	// Check if the page is using HTTPS
	        
    if (isPageUsingHttps(driver)) {
        System.out.println("The page is secure and uses HTTPS.\n");
    } else {
        System.out.println("Warning: The page does not use HTTPS.\n");
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
		 validateAndClick("div[class='sp9Navbar_Main'] a[title='चुनाव 2024']","css","Elections 2024");//elections 2024
	    }
	 
	 
	 @Test(dependsOnMethods = "testElectionHomePage" ,priority =4)
	 public void testAssemblyElections() {
		 validateAndClick("div[class='sp9Navbar_Main'] a[title='विधानसभा']","css","Assembly Elections");//assembly election
	 }
	 
	 
	 @Test(dependsOnMethods ="testElectionHomePage",priority=5)
		public void testMaharashtra(){
		 validateAndClick("div[class='sp9Navbar_Main'] a[title='महाराष्ट्र']","css","Maharashtra");//Maharashtra
	 }
	 
	 
	 @Test(dependsOnMethods ="testElectionHomePage",priority=6)
		public void testJharkhand(){
		 validateAndClick("div[class='sp9Navbar_Main'] a[title='झारखण्ड']","css","Jharkhand");//jharkhand
	 }
	 
	 
	 @Test(dependsOnMethods ="testElectionHomePage", priority=7)
			 public void testElectionConstituencies() {
		 validateAndClick("div[class='sp9Navbar_Main'] a[title='विधानसभा क्षेत्र']","css","Election Constituencies");//election constituencies
	 }
	 
	 
	 @Test(dependsOnMethods= "testElectionHomePage" ,priority=8)
	 public void testElelctionCandidates() {
		 validateAndClick("div[class='sp9Navbar_Main'] a[title='प्रत्याशी']","css","Election Candidates");//election candidates
	 }
	 
	 
	 @Test(dependsOnMethods ="testElectionHomePage" , priority=9)
	 public void testElectionSchedule() {
		 validateAndClick("div[class='sp9Navbar_Main'] a[title='शेड्यूल']","css","Schedule");//schedule
	 }
	 
	 
	 @Test(dependsOnMethods ="testElectionHomePage" , priority=10)
	 public void testLoksabhaElections() {
		 validateAndClick("div[class='sp9Navbar_Main'] a[title='लोकसभा']","css","Loksabha Elections");//Loksabha Election
	 }
	 
	 
	 @Test(dependsOnMethods ="testElectionHomePage",priority=11)
	 public void testElectionParty() {
		 validateAndClick("div[class='sp9Navbar_Main'] a[title='पार्टी']","css","Election Party");//Election Party
	 }
	 
	 @Test(dependsOnMethods ="testElectionHomePage",priority=12)
	 public void testViewMore() {
			 validateAndClick("//a[@class='view_more']", "xpath","View More");
	 }
	 
	 
	/* @Test(dependsOnMethods ="testMaharashtra",priority=13)
	 
	 public void testLoadMore() {
		validateAndClick("#category_more_posts", "css", "Load More"); 
	 }*/
	
	/* @Test(dependsOnMethods = "testElectionHomePage", priority = 13)
	 public void testLoadMore() {
	     int loadMoreClickCount = 0;
	     int maxClicks = 15;  // Optional limit to prevent infinite looping
	     
	     while (true) {
	    	 scrollToElement("//button[@id='category_more_posts']", "xpath", driver);
	    	 boolean isContentLoaded=validateAndClick("//button[@id='category_more_posts']", "xpath", "Load More");
	                 
	         // If the "Load More" button was clickable and clicked, counter +
	         if (isContentLoaded) {
	             loadMoreClickCount++;
	             System.out.println("Clicked 'Load More' button---- " + loadMoreClickCount + "- times.");

	             // Optional wait to allow content to load fully before checking the button again
	             waitForPageload();

	             // Optional limit to prevent infinite looping
	             if (loadMoreClickCount >= maxClicks) {
	                 System.out.println("Reached max click limit of " + maxClicks);
	                 break;
	             }
	         } else {
	             System.out.println("No more content to load or 'Load More' button is unavailable.");
	             break;
	         }
	     }

	     System.out.println("Total 'Load More' clicks performed:-- " + loadMoreClickCount);
	     Assert.assertTrue(loadMoreClickCount > 0, "'Load More' button was never clicked.");
	 }*/

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
       
	    private boolean validateAndClick(String locator,String locatorType, String elementDescription) {
	        try {
	            WebElement element;
	            if (locatorType.equalsIgnoreCase("css")) {
	                element = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(locator)));
	            } else if (locatorType.equalsIgnoreCase("xpath")) {
	                element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator)));
	            } else {
	                throw new IllegalArgumentException("Invalid locator type: " + locatorType);
	            }
	            element.click();
	           
	            System.out.println("Clicked on " + elementDescription);
	            waitForPageload();
	            handleAlert(driver, true);
	        } catch (Exception e) {
	            System.err.println("Failed to click on " + elementDescription + ": " + e.getMessage());
	            takeScreenshot("error-" + elementDescription + ".png");
	        }
			return false;
	    }
	
	
	
	
}
