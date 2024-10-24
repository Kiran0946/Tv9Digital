package tv9_digital_project;

import java.sql.Driver;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;


/*
 * THis class contains all the generic methods related to 
 * web driver actions
 * @author 
 */
@SuppressWarnings("unused")

public class WebDriverUtility {

	/**
	 * This method will maximize the window
	 * @param driver
	 */
		public void maximizeWindow(WebDriver driver) {
		try {
	        driver.manage().window().maximize();
	    } catch (Exception e) {
	        System.err.println("Window maximize failed: " + e.getMessage());
	    }
	}
		/**
		 * This method will minimize the window
		 * @param driver
		 */
		
		/**
		 * This method will get the url to be performed
		 */
		public void getUrl(WebDriver driver,String url) {
			try {
			driver.get(url);
			} catch (Exception e) {
				System.err.println("Failed to get url: "+e.getMessage());	
				}
			
		}
		public void minimizeWindow(WebDriver driver)
		{
			driver.manage().window().minimize();
		}
		/**
		 * This method will wait for 10 seconds to load the page.
		 */
		public void implicitlyWait(WebDriver driver)
		{
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(22));
		}
		
		
		/**
		 * This method will wait for the element to become visible
		 * @param driver
		 * @param element
		 */
		
		public void waitforElementToBeVisible(WebDriver driver,WebElement element)
		{
			WebDriverWait wait =new WebDriverWait(driver,Duration.ofSeconds(20));
			wait.until(ExpectedConditions.visibilityOf(element));
		}
		
		/**
		 * This method will handle drop down with the help of index
		 * @param element
		 */
		public void handleDropdown(WebElement element,int index)
		{
			Select s= new Select(element);
			s.selectByIndex(index);
		}
		
		/**
		 * This method will handle drop down with the help of value
		 * @param element
		 * @param value
		 */
		public void handleDropdown(WebElement element, String value)
		{
			Select s= new Select(element);
			s.selectByValue(value);
		}
		
		/**
		 * This method will handle drop down with the help of visible
		 * @param text
		 * @param element
		 */
		public void handleDropdown(String text,WebElement element )
		{
			Select s= new Select(element);
			s.selectByVisibleText(text);
		}
		
		/**
		 * This method will perform mouse hover action on a webelement
		 * @param driver
		 * @param element
		 */
		public void mouseoHoverAction(WebDriver driver,WebElement element)
		{
			Actions act= new Actions(driver);
			act.moveToElement(element).perform();
		}
		
		/**
		 * This method will perform right click action anywhere on a web page
		 * @param driver
		 */
		public void rightClickAction(WebDriver driver)
		{
			Actions act= new Actions(driver);
			act.contextClick().perform();
			
		}
		
		/**
		 * This method will perform double click anywhere on the web page
		 * @param driver
		 */
		public void doubleClickAction(WebDriver driver)
		{
			Actions act = new Actions(driver);
			act.doubleClick().perform();
		}
		
		/**
		 * This method will perform double click on a web element
		 * @param driver
		 * @param element
		 */
		public void doubleClickAction(WebDriver driver, WebElement element)
		{
			Actions act = new Actions(driver);
			act.doubleClick(element).perform();
		}
		
		public void closeBrowser(WebDriver driver) {
			
			try {
	            if (driver != null) {
	                System.out.println("Closing the browser");
	                driver.quit();
	                System.out.println("Browser closed successfully");
	            } else {
	                System.err.println("Driver is null, cannot close the browser");
	            }
	        } catch (Exception e) {
	            System.err.println("Error while closing the browser: " + e.getMessage());
	        }
	    }
		
		 /**
	     * This method will scroll to the bottom of the page
	     * @param driver
	     */
	    public void scrollToBottom(WebDriver driver) {
	        try {
	            if (driver instanceof JavascriptExecutor) {
	                JavascriptExecutor js = (JavascriptExecutor) driver;
	                js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	                System.out.println("Scrolled to the bottom of the page");
	            } else {
	                System.err.println("Driver does not support JavaScript execution");
	            }
	        } catch (Exception e) {
	            System.err.println("Scroll to bottom failed: " + e.getMessage());
	        }
	    }
	    
	    /**
	     * This method will get the current date and time in the specified format
	     * @return String representation of the current date and time
	     */
	    public String getCurrentDateTime() {
	        try {
	            LocalDateTime now = LocalDateTime.now();
	            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	            String formattedDateTime = now.format(formatter);
	            System.out.println("Current Date and Time: " + formattedDateTime);
	            return formattedDateTime;
	        } catch (Exception e) {
	            System.err.println("Error getting current date and time: " + e.getMessage());
	            return null;
	        }
	    } 
		
}
