package tv9_digital_project;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ElectionPage_test extends BaseClass {

	private static final String URL = "https://www.tv9hindi.com/elections";//url
	
	
	 @Test
	    public void testElectionHomePage() throws InterruptedException {
	        driver.get(URL);
	        System.out.println(" Navigated to URL:  " + URL );
	        //Thread.sleep(5000);
	        waitForPageload();
	        //logCurrentUrl(driver);// it will get the url of each page
	        getCurrentDateTime();
	        printInfo();
	    }
	 
	      
	 @Test(dependsOnMethods = "testElectionHomePage" ,priority= 1)
	    public void testElections2024() {
	        performAction("div[class='sp9Navbar_Main'] a[title='चुनाव 2024']");//elections 2024
	    }
	 
	 @Test(dependsOnMethods = "testElectionHomePage" ,priority =2)
	 public void testAssemblyElections() {
		 performAction("div[class='sp9Navbar_Main'] a[title='विधानसभा']");//assembly election
	 }
	 
	 @Test(dependsOnMethods ="testElectionHomePage",priority=3)
		public void testMaharashtra(){
		 performAction("div[class='sp9Navbar_Main'] a[title='महाराष्ट्र']");//Maharashtra
	 }
	 
	 @Test(dependsOnMethods ="testElectionHomePage",priority=4)
		public void testJharkhand(){
		 performAction("div[class='sp9Navbar_Main'] a[title='झारखण्ड']");//jharkhand
	 }
	 
	 @Test(dependsOnMethods ="testElectionHomepage", priority=5)
			 public void testElectionConstituencies() {
		 performAction("div[class='sp9Navbar_Main'] a[title='विधानसभा क्षेत्र']");//election constituencies
	 }
	 @Test(dependsOnMethods= "testElectionHomePage" ,priority=6)
	 public void testElelctionCandidates() {
		 performAction("div[class='sp9Navbar_Main'] a[title='प्रत्याशी']");//election candidates
	 }
	 
	 @Test(dependsOnMethods ="testElectionHomePage" , priority=7)
	 public void testElectionSchedule() {
		 performAction("div[class='sp9Navbar_Main'] a[title='शेड्यूल']");//schedule
	 }
	 
	 
	 @Test(dependsOnMethods ="testElectionHomePage" , priority=8)
	 public void testLoksabhaElections() {
		 performAction("div[class='sp9Navbar_Main'] a[title='लोकसभा']");//Loksabha Election
	 }
	 
	 @Test(dependsOnMethods ="testElectionHomePage",priority=9)
	 public void testElectionParty() {
		 performAction("div[class='sp9Navbar_Main'] a[title='पार्टी']");//ElectionParty
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
	
	
	
	
	
	
}
