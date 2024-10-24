package tv9_digital_project;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Tv9ElectionsPage {

	private static final String URL ="https://www.tv9hindi.com/elections";
	 private static final int TIMEOUT =10;
	
	 public static void main(String[] args) {
		 WebDriver driver = setupDriver();
		 boolean allActionsComplete = true;
		 
		    try {
		            driver.get(URL);
		            driver.manage().window().maximize();//maximize window
		            waitForPageLoad(driver);
		            handlePopup(driver);
		            performActions(driver);
		            printInfo(driver);
		            
		        }
		        catch (Exception e) {
		            System.err.println(" An error occurred: " + e.getMessage());
		            allActionsComplete= false;
		 
		        } 
		        finally {
		            driver.quit();
		            if(allActionsComplete =true)
		            {
		            	System.out.println("Operation success");
		            }
		            else {
		            	System.out.println("An error occured");
		            }
		         }
		     }
	 
		    private static WebDriver setupDriver() {
		    	//WebDriverManager.chromedriver().setup();
		    //	return new ChromeDriver();
		        WebDriverManager.firefoxdriver().setup();
		        return new FirefoxDriver();
		     
		    }
		    //Wait for the page to load
		    private static void waitForPageLoad(WebDriver driver) {
			      WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));  
			      wait.until(webDriver -> 
			        ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));	
				}
		    
		    //HAndle Popup
		    private static void handlePopup(WebDriver driver) {
		        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));
		        try {
		            //Handle the popup if present
		            WebElement popupCloseButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("")));
		            if (popupCloseButton.isDisplayed()) {
		                popupCloseButton.click();
		                System.out.println("Advertisement popup closed.");
		            }
		        } catch (Exception e) {
		            System.out.println("No advertisement popup found or unable to close: " + e.getMessage());
		        }
		    }
		    
		    private static boolean performActions(WebDriver driver) {
		        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));
		        boolean allActionComplete=true;
		        
		        String[] xPaths= {
		        		
		        		
		        		"//div[@class='sp9Navbar_Main']//a[@title='विधानसभा'][contains(text(),'विधानसभा')]",
		        		
		        		"//div[@class='sp9Navbar_Main']//a[@title='चुनाव 2024'][contains(text(),'चुनाव 2024')]",
		        		
		        		"//div[@class='sp9Navbar_Main']//a[@title='जम्मू कश्मीर'][contains(text(),'जम्मू कश्मीर')]",
		        		
		        		"//div[@class='sp9Navbar_Main']//a[@title='हरियाणा'][contains(text(),'हरियाणा')]",
		        		
		        		"//div[@class='sp9Navbar_Main']//a[@title='विधानसभा क्षेत्र'][contains(text(),'विधानसभा क्षेत्र')]",
		        		
		        		"//div[@class='sp9Navbar_Main']//a[@title='पार्टी'][contains(text(),'पार्टी')]",
		        		
		        		"//div[@class='sp9Navbar_Main']//a[@title='लोकसभा'][contains(text(),'लोकसभा')]",
		        		
		        		"//div[@class='sp9Navbar_Main']//a[@title='प्रत्याशी'][contains(text(),'प्रत्याशी')]"
		        		
		        	   };
 
		
		        for (String xPath : xPaths) {
		        	if(!clickElement(wait,By.xpath(xPath))) {
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

		                   "Assembly Elections 2024 (विधानसभा इलेक्शन 2024) Candidates", 
		                   "Jammu & Kashmir",
		                   "Haryana Vidhan Sabha Chunav 2024",
		                   "Candidates in Hindi | TV9 Bharatvarsh"
			             
				    };

	            boolean titleMatches=true;
	            
	            // Check if any keyword is contained in the actual title
	            for (String keyword : keywords) {
	            	if(actualTitle.contains(keyword)) {
	               // if (actualTitle.contains(keyword)) {
	                    titleMatches = false;
	                    break; // Exit loop if any keyword matches
	                }
	            }
	       
	            if (titleMatches) {
	                System.out.println("TITLE VERIFICATIOIN SUCCESSFUL"); 
	            } else {
	                System.out.println("TITLE VERIFICATION FAILED");
	            }		    
		    
	}
	 

}