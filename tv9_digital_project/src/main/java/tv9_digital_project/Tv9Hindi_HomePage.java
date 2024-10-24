package tv9_digital_project;


import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.openqa.selenium.support.ui.ExpectedConditions;

import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Tv9Hindi_HomePage {

        private static final String URL = "https://www.tv9hindi.com/";//Launch website
        //private static final String URL ="https://beta.tv9hindi.com/";
	    private static final int TIMEOUT = 15; 

	    public static void main(String[] args) {
	        WebDriver driver = setupDriver();
	        boolean allActionComplete= true;
	        
	        try {
	            driver.get(URL);
	            driver.manage().window().maximize();//maximize window
	            performActions(driver);
	            waitForPageLoad(driver);
	            performActions(driver);
	            printInfo(driver);
	            automateLivetv(driver);
	        }
	        catch (Exception e) {
	            System.err.println("An error occurred: " + e.getMessage());
	            allActionComplete =false; 
	        } 
	        finally {
	            driver.quit(); // Use quit to close all instances
	            if(allActionComplete=true) {
	            	System.out.println("Website operation complete");
	            }
	            	else
	            	{
	            		System.out.println("An error occured");
	            		
	            	}
	            }
	        }
	    
	    //automate live tv 
	    private static void automateLivetv(WebDriver driver) {
	    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));
            try {
            	WebElement livetv=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@title='Live TV']//*[name()='svg']")));
            	livetv.click();
            	System.out.println("live tv started");
            	
            	waitForPageLoad(driver);
            }
            catch (Exception e) {
                System.err.println("Failed to start live TV: " + e.getMessage());
            }
			
		}


		private static WebDriver setupDriver() {
	        WebDriverManager.firefoxdriver().setup();
	        return new FirefoxDriver();
	    }
	    
	    private static void waitForPageLoad(WebDriver driver) {
	      WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));  
	      wait.until(webDriver->((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));	
		}

	    private static boolean performActions(WebDriver driver) {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));
	        boolean allActionComplete=true;
	     

	        // Array of actions to be  performed  
	        String[] cssSelectors = {
	            "a[title='वेब स्टोरी']",//webstory
	            
	            "#notiCount",//notifications
	            
	            "div[class='container'] a[title='बिजनेस']",//bussiness
	            
	            "div[class='tv9_catnavbar'] a[title='राज्य']",//state
	            
	            "div[class='tv9_catnavbar'] a[title='देश']",//country
	            
	            "a[title='चुनाव 2024']",//elections 2024
	            
	            "a[title='हिन्दी ']",//hindi language button
	            
	            "//a[@title='टेक']",// tech
	            
	            "div[class='tv9_catnavbar'] a[title='दुनिया']",//world
	            
	            "//div[@class='tv9_catnavbar']//a[@title='वीडियो'][contains(text(),'वीडियो')]",//video
	            
	            "//div[@class='tv9_catnavbar']//a[@title='धर्म'][contains(text(),'धर्म')]",//religion
	            
	            "//div[@class='tv9_catnavbar']//a[@title='एजुकेशन'][contains(text(),'एजुकेशन')]",//education
	            
	           //"//a[@title='Live TV']//*[name()='svg']",//live tv
	            
	            ".MenuBtn",//hamburger menu
	            
	            ".close_icon",//close button on hamburger page
	            
	            "div[class='tv9_catnavbar'] a[title='हेल्थ']",//health
	            	            
	            "div[class='tv9_catnavbar'] a[title='नॉलेज']",//knowledge
	            
	            "a[title='Sports9']",//sports9page
	            
	        };

	        for (String cssSelector : cssSelectors) {
	        	if(!clickElement(wait,By.xpath(cssSelector)));{
	        	allActionComplete= false;
	             }
	        	waitForPageLoad(driver);
	        	}
	        return allActionComplete;
	    
	    }   

        
	    private static boolean clickElement(WebDriverWait wait, By by) {
	        try {
	            wait.until(ExpectedConditions.elementToBeClickable(by)).click();
	            return true; //  all action done
	            
	        } 
	        catch (Exception e) {
	            System.err.println("Failed to click on element: " + by + " - " + e.getMessage());
	            return false; // action couldnot be completed
	        }
	    }
	    


	    private static void printInfo(WebDriver driver)
	   
	    {
	    	
	        String windowHandle = driver.getWindowHandle();//get window handle 
	        System.out.println("The handle of the website is: " + windowHandle);
	        
	        String actualTitle = driver.getTitle();//get webpage title 
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

	            boolean titleMatches = true;
	            
	            // Check if any keyword is contained in the actual title
	            for (String keyword : keywords) {
	                if (actualTitle.contains(keyword)) {
	                    titleMatches = false;
	                    break; // Exit loop if any keyword matches
	                }
	            }
	      

	            if (titleMatches) {
	                System.out.println("TITLE VERIFICATIOIN SUCCESSFUL"); 
	            }
	            else {
	                System.out.println("TITLE VERIFICATION FAILED");
	            }
	           
	          }
	    }
	    
	   
	    
	    
