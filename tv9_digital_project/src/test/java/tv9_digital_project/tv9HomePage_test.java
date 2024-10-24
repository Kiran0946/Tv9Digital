package tv9_digital_project;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Test;
import org.testng.Assert;

public class tv9HomePage_test extends BaseClass {

	private static final String URL = "https://www.beta.tv9hindi.com/";
      
	
	    @Test
	    public void testHomePage() throws InterruptedException {
	        driver.get(URL);
	        System.out.println("Navigated to URL: " + URL);
	        Thread.sleep(5000);
	        waitForPageload();
	       // getCurrentDateTime();
	        printInfo();
	    }

	  
        
		@Test(dependsOnMethods = "testHomePage")
	    public void testAutomateLiveTv() {
	        automateLiveTv();
	    }

	    @Test(dependsOnMethods = "testHomePage")
	    public void testWebStory() {
	        performAction("a[title='वेब स्टोरी']");
	    }

	    @Test(dependsOnMethods = "testHomePage")
	    public void testNotifications() {
	        performAction("#notiCount");
	    }

	    @Test(dependsOnMethods = "testHomePage")
	    public void testBusiness() {
	        performAction("a[title='बिजनेस']");
	    }

	    @Test(dependsOnMethods = "testHomePage")
	    public void testState() {
	        performAction("div[class='tv9_catnavbar'] a[title='राज्य']");
	    }

	    @Test(dependsOnMethods = "testHomePage")
	    public void testCountry() {
	        performAction("div[class='tv9_catnavbar'] a[title='देश']");//country tab
	    }

	    @Test(dependsOnMethods = "testHomePage")
	    public void testElections2024() {
	        performAction("a[title='चुनाव 2024']");//elections tab
	    }

	    @Test(dependsOnMethods = "testHomePage")
	    public void testHindiLanguage() {
	        performAction("img[title='TV9 Bharatvarsh | Hindi News']");//logo button
	    }

	    @Test(dependsOnMethods = "testHomePage")
	    public void testTechnology() {
	        performAction("a[title='टेक']");//technology tab
	    }

	    @Test(dependsOnMethods = "testHomePage")
	    public void testWorld() {
	        performAction("div[class='tv9_catnavbar'] a[title='दुनिया']");//world tab
	    }

	    @Test(dependsOnMethods = "testHomePage")
	    public void testActiveVideo() {
	        performAction("a[class='active']");
	    }

	    @Test(dependsOnMethods = "testHomePage")
	    public void testReligion() {
	        performAction("div[class='tv9_catnavbar'] a[title='धर्म']");//religion tab
	    }

	    @Test(dependsOnMethods = "testHomePage")
	    public void testEducation() {
	        performAction("div[class='tv9_catnavbar'] a[title='नॉलेज']");
	        
	    }

	    @Test(dependsOnMethods = "testHomePage")
	    public void testHamburgerMenu() {
	        performAction(".tv9_navbarRHS");//hamburger menu
	    }

	    @Test(dependsOnMethods = "testHamburgerMenu")
	    public void testCloseHamburgerMenu() {
	        performAction("#closeMenu");
	    }

	    @Test(dependsOnMethods = "testHomePage")
	    public void testHealth() {
	        performAction("div[class='tv9_catnavbar'] a[title='हेल्थ']");//health tab
	    }

	    @Test(dependsOnMethods = "testHomePage")
	    public void testKnowledge() {
	        performAction("div[class='tv9_catnavbar'] a[title='नॉलेज']");//knowledge tab
	    }

	    @Test(dependsOnMethods = "testHomePage")
	    public void testSports() {
	        performAction("a[title='Sports9']");//sports tab 
	    }
	    
	    @Test(dependsOnMethods ="testHomePage")
	    public void testSportsLogo() {
	   	performAction("img[title='Sports 9']");//sports button logo
	    }

	    private void automateLiveTv() {
	        try {
	            WebElement liveTv = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@title='Live TV']//*[name()='svg']")));
	            liveTv.click();
	            System.out.println("Live TV started.");
	            waitForPageload();
	        } catch (Exception e) {
	            System.err.println("Failed to start Live TV: " + e.getMessage());
	            takeScreenshot("error-live-tv.png");
	        }
	    }

	    private void performAction(String cssSelector) {
	        System.out.println("Attempting to click element with selector: " + cssSelector);
	        boolean actionComplete = clickElement(By.cssSelector(cssSelector));
	        Assert.assertTrue(actionComplete, "Failed to click on element: " + cssSelector);
	        waitForPageload();
	    }

	    private boolean clickElement(By by) {
	        try {
	            wait.until(ExpectedConditions.elementToBeClickable(by)).click();
	            System.out.println("Element clicked: " + by);
	            return true;
	        } catch (Exception e) {
	            System.err.println("Failed to click on element: " + by + " - " + e.getMessage());
	            takeScreenshot("error-click-element.png");
	            return false;
	        }
	    }

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
	    
	    @AfterSuite(groups ={"SmokeSuite","RegressionSuite"}) //(alwaysRun= true)
		public void asConfig() {
			System.out.println("----- database closed successfully -----");
		}
       
	    
	  
	}
