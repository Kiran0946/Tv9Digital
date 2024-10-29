package tv9_digital_project;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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
	    public void testHomePage() {
	        System.out.println("-----Validating homepage-----");
	        Assert.assertTrue(driver.getTitle().contains("TV9 Bharatvarsh"), "Homepage title validation failed");
	    }
	 
	 @Test(dependsOnMethods = "testHomePage", priority = 2)
	    public void testWebStory() {
	        validateAndClick("a[title='वेब स्टोरी']", "Web Story");
	    }


	   
	    @Test(dependsOnMethods = "testHomePage" ,priority=5)
	    public void testNotifications() {
	        validateAndClick("#notiCount","Notifications");//notification
	    }

	    @Test(dependsOnMethods = "testHomePage" ,priority=2)
	    public void testBusiness() {
	        validateAndClick("a[title='बिजनेस']","Business");//bussiness
	    }

	    @Test(dependsOnMethods = "testHomePage",priority=4)
	    public void testState() {
	        validateAndClick("div[class='tv9_catnavbar'] a[title='राज्य']", "State");//state
	    }

	    @Test(dependsOnMethods = "testHomePage",priority=3)
	    public void testCountry() {
	    	validateAndClick("div[class='tv9_catnavbar'] a[title='देश']","Country");//country tab
	    }
	    
	    @Test(dependsOnMethods = "testHomePage", priority= 6)
	    public void testAutomateLiveTv() throws InterruptedException {
	    	//Thread.sleep(5000);
	        automateLiveTv();
	    }

	    @Test(dependsOnMethods = "testHomePage" , priority= 7)
	    public void testElections2024() {
	    	validateAndClick("a[title='चुनाव 2024']","Elections 2024");//elections tab
	    }

	    @Test(dependsOnMethods = "testHomePage" , priority=8)
	    public void testHindiLanguage() {
	    	validateAndClick("img[title='TV9 Bharatvarsh | Hindi News']", "TV9LOGO");//logo button
	    }

	    @Test(dependsOnMethods = "testHomePage" , priority=9)
	    public void testTechnology() {
	    	validateAndClick("a[title='टेक']","Technology");//technology tab
	        
	    }

	    @Test(dependsOnMethods = "testHomePage", priority=10)
	    public void testWorld() {
	    	validateAndClick("div[class='tv9_catnavbar'] a[title='दुनिया']","World");//world tab
	    }

	    @Test(dependsOnMethods = "testHomePage" , priority=12)
	    public void testActiveVideo() {
	    	validateAndClick("a[class='active']","video");
	    }

	    @Test(dependsOnMethods = "testHomePage" , priority=11)
	    public void testReligion() {
	    	validateAndClick("div[class='tv9_catnavbar'] a[title='धर्म']","Religion");//religion tab
	    }

	    @Test(dependsOnMethods = "testHomePage" , priority=13)
	    public void testEducation() {
	    	validateAndClick("div[class='tv9_catnavbar'] a[title='नॉलेज']","Knowledge");
	        
	    }

	    @Test(dependsOnMethods = "testHomePage" , priority=14)
	    public void testHamburgerMenu() {
	    	validateAndClick("i:nth-child(3)","Hamburger menu");//hamburger menu
	    }

	    @Test(dependsOnMethods = "testHamburgerMenu" , priority=15)
	    public void testCloseHamburgerMenu() {
	    	validateAndClick(".close_icon","Close icon on Hamburgre menu");
	    }

	    @Test(dependsOnMethods = "testHomePage" , priority=16)
	    public void testHealth() {
	    	validateAndClick("div[class='tv9_catnavbar'] a[title='हेल्थ']","Health");//health tab
	    }

	    @Test(dependsOnMethods = "testHomePage" , priority=17)
	    public void testKnowledge() {
	    	validateAndClick("div[class='tv9_catnavbar'] a[title='नॉलेज']","Knowledge");//knowledge tab
	    }

	    @Test(dependsOnMethods = "testHomePage" , priority=18)
	    public void testSports() {
	    	validateAndClick("a[title='Sports9']","Sports");//sports tab 
	    }
	    
	    @Test(dependsOnMethods ="testSports" , priority=19)
	    public void testSportsLogo() {
	    	validateAndClick("img[title='Sports 9']","Sports 9 logo");//sports button logo
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
