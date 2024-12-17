package tv9_digital_project;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
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

	private static final String url = "https://www.tv9hindi.com/";//url
	
	@BeforeMethod
	
	public void beforeMethod() throws URISyntaxException {
		
        driver.get(url);  
        System.out.println("Navigated to URL:---"+url);
        
        //methods invoked from webdriver class
        
        waitForPageload();//wait for the page to load
        getCurrentDateTime();//get tge current date and time.
      //getCoreWebVitals(driver);
        logCurrentUrl(driver);//log the url of the webpage
        isPageUsingHttps(driver);//check the url if https
        extractComScoreCode(driver);//verify the comscore script
        extractChartbeatCode(driver);//verify the chartbeat script
        extractIzootoCode(driver);//verify the izooto script
        extractGoogleManager(driver);//verify gt script
       //mouseHoverAction(driver,null);
       //printInfo();
       //waitforElementToBeVisible(driver, null); 
       
        //Check if the page is using HTTPS
       /* if (isPageUsingHttps(driver)) {
            System.out.println("The page is secure and uses HTTPS. \n");
          //  System.out.println("====================================\n");
        } else {
            System.out.println("Warning: The page does not use HTTPS. \n");
        }*/
    
    }
		
	@Test(enabled=false)
    public void testCoreWebVitals() {
        System.out.println("---Fetching Core Web Vitals...\n ");
        Map<String, Object> coreWebVitals = getCoreWebVitals(driver);
        
        // Check if coreWebVitals is null or empty & exit the method if no data is retrieved
        if (coreWebVitals == null || coreWebVitals.isEmpty()){
            System.err.println("Failed to retrieve Core Web Vitals. Its null or empty.");
            return; 
        }

        // Define acceptable performance thresholds
        double lcpThreshold = 2500; // 2.5 seconds
        double fidThreshold = 100;  // 0.1 seconds
        double clsThreshold = 0.1;  // 0.1

        double lcp = (double) coreWebVitals.getOrDefault("lcp", -1);
        double fid = (double) coreWebVitals.getOrDefault("fid", -1);
        double cls = (double) coreWebVitals.getOrDefault("cls", -1);
        
        // Check if any metric is invalid & exit the method if metrics are invalid
        if (lcp < 0 || fid < 0 || cls < 0) {
            System.err.println("One or more metrics are invalid. LCP: " + lcp + ", FID: " + fid + ", CLS: " + cls);
            return; 
        }
        // Assert that each metric is within the acceptable threshold
        Assert.assertTrue(lcp <= lcpThreshold, "LCP exceeds threshold (" + lcp + " ms > " + lcpThreshold + " ms)");
        Assert.assertTrue(fid <= fidThreshold, "FID exceeds threshold (" + fid + " ms > " + fidThreshold + " ms)");
        Assert.assertTrue(cls <= clsThreshold, "CLS exceeds threshold (" + cls + " > " + clsThreshold + ")");

        // Print the core web vitals for reference
        System.out.println("Largest Contentful Paint (LCP): \n " + lcp + " ms");
        System.out.println("First Input Delay (FID): \n  " + fid + " ms");
        System.out.println("Cumulative Layout Shift (CLS): \n " + cls);
    }
	 @Test(priority = 2)
	    public void testHomePage() {
		   // System.out.println("=======================");
	        System.out.println("--Validating homepage--\n");
	        Assert.assertTrue(driver.getTitle().contains("TV9 Bharatvarsh"), "Homepage title validation failed");
	      //  System.out.println("Home Page Validated");
	    }
	 
	 @Test(/*dependsOnMethods = "testHomePage"*/ priority = 3)
	    public void testWebStory() {
	      /* WebElement Webstory= */ validateAndClick("a[title='वेब स्टोरी']","css", "Web Story");//webstory
	       //mouseHoverAction(driver, Webstory);
	       
	    }
	 //////////////////////////////
	 @Test(priority=4)
	    public void testState() {
		 validateAndClick("div[class='tv9_catnavbar'] a[title='राज्य']","css","State");//state
		 //mouseHoverAction(driver,State);
		
	    }
	   
	    @Test(/*dependsOnMethods = "testHomePage" ,priority=5,*/enabled=false)
	    public void testNotifications() {
	    /* WebElement Notifications=*/validateAndClick("#notiCount","css","Notifications \n ");//notification
	   //  mouseHoverAction(driver, Notifications);
	    }


	    @Test(dependsOnMethods = "testHomePage",priority=6)
	    public void testCountry() {
	   /* WebElement Country=*/	validateAndClick("div[class='tv9_catnavbar'] a[title='देश']","css","Country");//country tab
	  //  mouseHoverAction(driver, Country);
	    }
	    
	    @Test(/*dependsOnMethods = "testHomePage", priority= 7,*/enabled=false)
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

	    @Test(priority=10)
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

	    @Test(/*dependsOnMethods = "testHomePage" , priority=13,*/enabled=false)
	    public void testHamburgerMenu() {
	    	validateAndClick("i:nth-child(3)","css","Hamburger menu");//hamburger menu
	    }

	    @Test(/*dependsOnMethods = "testHamburgerMenu" , priority=14,*/enabled=false)
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
	    
	    @Test(/*dependsOnMethods ="testSports" , priority=18,*/enabled=false)
	    public void testSportsLogo() {
	    	validateAndClick("img[title='Sports 9']","css","Sports 9 logo");//sports button logo
	    }
	    
	    @Test(dependsOnMethods = "testHomePage" ,priority=19)
	    public void testBusiness() {
	      validateAndClick("//a[contains(text(),'बिजनेस')]","xpath","Business");//bussiness
	    }
	    

	    @Test(dependsOnMethods = "testHomePage", priority=20)
	    public void testWorld() {
	    	validateAndClick("div[class='tv9_catnavbar'] a[title='दुनिया']","css","World");//world tab
	    }
	    
	   @Test(priority = 21)
	    public void testIsPageUsingHttps() {
	        Assert.assertTrue(isPageUsingHttps(driver), "The page is not using HTTPS. \n ");
	        System.out.println("Page is using HTTPS.\n ");
	    }
	    
	    @Test(/*dependsOnMethods= "testHomePage",priority=22,*/enabled=false)
	    public void testViewMore() {
	    	validateAndClick("//a[@href='/latest-news'][normalize-space()='View more']", "xpath", "View More");
	    }


	    
	    @AfterMethod
	    
	    public void logTestDuration(ITestResult result) {
	    	
	        String testName = result.getMethod().getMethodName();
	        String status = result.isSuccess() ? "PASSED" : "FAILED";
	        long duration =System.currentTimeMillis()-startTime;
	        writeToNotepad(testName, duration, status);
	        System.out.println("Test '" + testName + "' status: " + status + ",duration: " + duration + " ms");
	    }
   
	  
	    private void automateLiveTv() {
	        try {
	            WebElement liveTv = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@title='Live TV']//*[name()='svg']")));
	            liveTv.click();
	            System.out.println("Live TV started.\n");
	            //waitForPageload();
	        } catch (Exception e) {
	            System.err.println("Failed to start Live TV:\n " + e.getMessage());
	            takeScreenshot("error-live-tv.png");
	        }
	    }
	    

	    @SuppressWarnings("unused")
		private void printInfo() {
	        String windowHandle = driver.getWindowHandle();
	        System.out.println("The handle of the website is: \n " + windowHandle);

	        String actualTitle = driver.getTitle();
	        System.out.println("The title of the webpage is: \n " + actualTitle);

	        String[] keywords = {
	            "Hindi News",":हिंदी न्यूज़","Latest Hindi News","Today Hindi News","Breaking News in Hindi",
	            "हिंदी समाचार | TV9 Bharatvarsh","TV9 Bharatvarsh LIVE,Hindi News"    
	        };
	        boolean titleMatches = false;
	        for (String keyword : keywords) {
	            if (actualTitle.contains(keyword)) {
	                titleMatches = true;
	                break;
	            }
	        }

	        if (titleMatches) {
	            System.out.println("TITLE Verification Sucessful");
	        } else {
	            System.out.println("TITLE Verification FAILED");
	            
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
	    
	    private WebElement validateAndClick(String locator,String locatorType, String elementDescription) {
	    	WebElement element=null;
	    	String url=null;
	    	//based on locator type it will locate the element
	        try {
	            if (locatorType.equalsIgnoreCase("css")) {
	                element= wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(locator)));
	            }
	            else if (locatorType.equalsIgnoreCase("xpath")) {
	                element= wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator)));
	            } 
	            else if(locatorType.equalsIgnoreCase("name")) {
	            	element= wait.until(ExpectedConditions.elementToBeClickable(By.name(locator)));
	            }
	            else {
	                throw new IllegalArgumentException("Invalid locator type:--" +locatorType);
	            }
	            
	         // Retrieve the URL of the element (if it has an href attribute)
	            url = element.getAttribute("href");
	            if (url!= null) {
	                System.out.println("URL of the element:-- " +url);
	            } else {
	                System.out.println("The element does not have an href attribute.");
	            }
	            element.click();//click on the element        
	            System.out.println("Clicked on--" +elementDescription);
	            
	            waitForPageload();//wait for the page to load 
	            handleAlert(driver,true);//handle alert if present
	        } 
	        catch (Exception e) {
	            System.err.println("Failed to click on " +elementDescription + ": \n " + e.getMessage());
	            takeScreenshot("error-" +elementDescription + ".png");
	     
	        }
	     return element;
	  
	    }
	
}
