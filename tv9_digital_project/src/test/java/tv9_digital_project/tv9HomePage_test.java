package tv9_digital_project;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.ITestResult;

@SuppressWarnings("unused")
public class tv9HomePage_test  extends  BaseClass {

	private static final String URL = "https://www.tv9hindi.com/";//url
	
	@BeforeMethod
	
	public void beforeMethod() {
		
        driver.get(URL);
        System.out.println("Navigated to URL: " + URL);
        
        waitForPageload();
        getCurrentDateTime();
        logCurrentUrl(driver);
       // printInfo();
       
        
     // Check if the page is using HTTPS
        if (isPageUsingHttps(driver)) {
            System.out.println("The page is secure and uses HTTPS.");
        } else {
            System.out.println("Warning: The page does not use HTTPS.");
        }
    
    }
	
	  /*  @Test
	    public void testHomePage() throws InterruptedException {
	    	
	        driver.get(URL);
	        System.out.println(" Navigated to URL:  " + URL );
	        //Thread.sleep(5000);
	        waitForPageload();
	        //logCurrentUrl(driver);// it will get the url of each page
	        getCurrentDateTime();
	        printInfo();
	    }*/

	  
        /*
         * test cases related to tabs in website
         */
		
	@Test(priority = 1)
    public void testCoreWebVitals() {
        System.out.println("Fetching Core Web Vitals...");
        Map<String, Object> coreWebVitals = getCoreWebVitals(driver);

        // Define acceptable performance thresholds
        double lcpThreshold = 2500; // 2.5 seconds
        double fidThreshold = 100;  // 0.1 seconds
        double clsThreshold = 0.1;  // 0.1

        double lcp = (double) coreWebVitals.getOrDefault("lcp", 0);
        double fid = (double) coreWebVitals.getOrDefault("fid", 0);
        double cls = (double) coreWebVitals.getOrDefault("cls", 0);

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
	    public void testHomePage() {
	        System.out.println("-----Validating homepage-----");
	        Assert.assertTrue(driver.getTitle().contains("TV9 Bharatvarsh"), "Homepage title validation failed");
	    }
	 
	 @Test(dependsOnMethods = "testHomePage", priority = 3)
	    public void testWebStory() {
	        validateAndClick("a[title='वेब स्टोरी']","css", "Web Story");//webstory
	    }
	 
	 
	 @Test(dependsOnMethods = "testHomePage",priority=4)
	    public void testState() {
	        validateAndClick("div[class='tv9_catnavbar'] a[title='राज्य']","css","State");//state
	    }
	   
	    @Test(dependsOnMethods = "testHomePage" ,priority=5)
	    public void testNotifications() {
	        validateAndClick("#notiCount","css","Notifications");//notification
	    }


	    @Test(dependsOnMethods = "testHomePage",priority=6)
	    public void testCountry() {
	    	validateAndClick("div[class='tv9_catnavbar'] a[title='देश']","css","Country");//country tab
	    }
	    
	    @Test(dependsOnMethods = "testHomePage", priority= 7)
	    public void testAutomateLiveTv() throws InterruptedException {
	    	//Thread.sleep(5000);
	        automateLiveTv();
	    }

	    @Test(dependsOnMethods = "testHomePage" , priority= 8)
	    public void testElections2024() {
	    	validateAndClick("a[title='चुनाव 2024']","css","Elections 2024");//elections tab
	    }

	    @Test(dependsOnMethods = "testHomePage" , priority=9)
	    public void testHindiLanguage() {
	    	validateAndClick("img[title='TV9 Bharatvarsh | Hindi News']","css", "TV9LOGO");//logo button
	    }

	    @Test(dependsOnMethods = "testHomePage" , priority=10)
	    public void testTechnology() {
	    	validateAndClick("a[title='टेक']","css","Technology");//technology tab
	        
	    }
	   
	    @Test(dependsOnMethods = "testHomePage" , priority=10)
	    public void testReligion() {
	    	validateAndClick("div[class='tv9_catnavbar'] a[title='धर्म']","css","Religion");//religion tab
	    }

	    @Test(dependsOnMethods = "testHomePage" , priority=11)
	    public void testActiveVideo() {
	    	validateAndClick("a[class='active']","css","video");
	    }

	    @Test(dependsOnMethods = "testHomePage" , priority=12)
	    public void testEducation() {
	    	validateAndClick("div[class='tv9_catnavbar'] a[title='नॉलेज']","css","Knowledge");
	        
	    }

	    @Test(dependsOnMethods = "testHomePage" , priority=13)
	    public void testHamburgerMenu() {
	    	validateAndClick("i:nth-child(3)","css","Hamburger menu");//hamburger menu
	    }

	    @Test(dependsOnMethods = "testHamburgerMenu" , priority=14)
	    public void testCloseHamburgerMenu() {
	    	validateAndClick(".close_icon","css","Close icon on Hamburger menu");
	    }

	    @Test(dependsOnMethods = "testHomePage" , priority=15)
	    public void testHealth() {
	    	validateAndClick("div[class='tv9_catnavbar'] a[title='हेल्थ']","css","Health");//health tab
	    }

	    @Test(dependsOnMethods = "testHomePage" , priority=16)
	    public void testKnowledge() {
	    	validateAndClick("div[class='tv9_catnavbar'] a[title='नॉलेज']","css","Knowledge");//knowledge tab
	    }

	    @Test(dependsOnMethods = "testHomePage" , priority=17)
	    public void testSports() {
	    	validateAndClick("a[title='Sports9']","css","Sports");//sports tab 
	    }
	    
	    @Test(dependsOnMethods ="testSports" , priority=18)
	    public void testSportsLogo() {
	    	validateAndClick("img[title='Sports 9']","css","Sports 9 logo");//sports button logo
	    }
	    
	    @Test(dependsOnMethods = "testHomePage" ,priority=19)
	    public void testBusiness() {
	        validateAndClick("a[title='बिजनेस']","css","Business");//bussiness
	    }

	    @Test(dependsOnMethods = "testHomePage", priority=20)
	    public void testWorld() {
	    	validateAndClick("div[class='tv9_catnavbar'] a[title='दुनिया']","css","World");//world tab
	    }
	    
	    @Test(priority = 21)
	    public void testIsPageUsingHttps() {
	        Assert.assertTrue(isPageUsingHttps(driver), "The page is not using HTTPS.");
	        System.out.println("Page is using HTTPS.");
	    }
	    
	    @Test(dependsOnMethods= "testHomePage",priority=22)
	    public void testViewMore() {
	    	validateAndClick("//a[@href='/latest-news'][normalize-space()='View more']", "xpath", "View More");
	    }


	    
	    @AfterMethod
	    
	    public void logTestDuration(ITestResult result) {
	    	
	        String testName = result.getMethod().getMethodName();
	        String status = result.isSuccess() ? "PASSED" : "FAILED";
	        long duration =System.currentTimeMillis()-startTime;
	        writeToNotepad(testName, duration, status);
	        System.out.println("Test '" + testName + "' status: " + status + ", duration: " + duration + " ms");
	    }
   
	  
	    private void automateLiveTv() {
	        try {
	            WebElement liveTv = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@title='Live TV']//*[name()='svg']")));
	            liveTv.click();
	            System.out.println("Live TV started.");
	            //waitForPageload();
	        } catch (Exception e) {
	            System.err.println("Failed to start Live TV: " + e.getMessage());
	            takeScreenshot("error-live-tv.png");
	        }
	    }

	  /*  private void performAction(String cssSelector) {
	        System.out.println("Attempting to click element with selector: " + cssSelector);
	        boolean actionComplete = clickElement(By.cssSelector(cssSelector));
	        Assert.assertTrue(actionComplete, "Failed to click on element: " + cssSelector);
	        waitForPageload();
	    }*/

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
	    }*/

	    @SuppressWarnings("unused")
		private void printInfo() {
	        String windowHandle = driver.getWindowHandle();
	        System.out.println("The handle of the website is: " + windowHandle);

	        String actualTitle = driver.getTitle();
	        System.out.println("The title of the webpage is: " + actualTitle);

	        String[] keywords = {
	            "Hindi News",
	            ":हिंदी न्यूज़",
	            "Latest Hindi News",
	            "Today Hindi News",
	            "Breaking News in Hindi",
	            "हिंदी समाचार | TV9 Bharatvarsh",
	            "TV9 Bharatvarsh LIVE,Hindi News"
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
       
	    /**
		 * Handles alert popup if it appears.
		 *
		 * @param acceptAlert if true, the alert will be accepted; if false, it will be dismissed
		 * @return the text of the alert if present, otherwise null
		 */
		/*private String handleAlert(boolean acceptAlert) {
			try {
				Alert alert = driver.switchTo().alert();
				String alertText = alert.getText();
				if (acceptAlert) {
					alert.accept();
					System.out.println("Accepted alert with text: " + alertText);
				} else {
					alert.dismiss();
					System.out.println("Dismissed alert with text: " + alertText);
				}
				return alertText;
			} catch (NoAlertPresentException e) {
				System.out.println("No alert is present.");
				return null;
			}
		}*/
	    private void validateAndClick(String locator,String locatorType, String elementDescription) {
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
	    
	  
	    }
	
}
