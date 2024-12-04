package tv9_digital_project;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.MalformedURLException;
import java.sql.Driver;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.apache.logging.log4j.LogManager;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.apache.logging.log4j.Logger;


/*
 * THis class contains all the generic methods related to 
 * web driver actions
 * @author 
 */

public class WebDriverUtility {
	
	private static final Logger logger = LogManager.getLogger(WebDriverUtility.class);

	/**
	 * This method will maximize the window
	 * @param driver
	 */
		public void maximizeWindow(WebDriver driver) {
		try {
	        driver.manage().window().maximize();
	       // System.out.println("Windows Maximized");
	    } catch (Exception e) {
	        System.err.println("Window maximize failed: " + e.getMessage());
	    }
	}
		
		
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
		
		/**
		 * This method will minimize the window
		 * @param driver
		 */
		public void minimizeWindow(WebDriver driver) {
		try{
			driver.manage().window().minimize();
		}catch(Exception e) {
			System.err.println("Windows minimize failed: "+e.getMessage());
		}
}
		/**
		 * This method will wait for 10 seconds to load the page.
		 */
		public void implicitlyWait(WebDriver driver) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		}
		
		
		/**
		 * This method will wait for the element to become visible
		 * @param driver
		 * @param element
		 */
		
		public void waitforElementToBeVisible(WebDriver driver,WebElement element) {
			WebDriverWait wait =new WebDriverWait(driver,Duration.ofSeconds(20));
			wait.until(ExpectedConditions.visibilityOf(element));
		}
		
		/*
		 * This method will delete all cookies in a session 
		 * @param element
		 */
		public void mangageCookies(WebDriver driver) {
			 try {
		            driver.manage().deleteAllCookies();
		            System.out.println("All cookies deleted successfully.");
		        } catch (Exception e) {
		            System.err.println("Failed to delete cookies: " + e.getMessage());
		        }
		    }
		 
		/*
		 * This method will handle drop down with the help of index
		 * @param element
		 * @param index
		 **/
		public void handleDropdown(WebElement element,int index) {
			Select s= new Select(element);
			s.selectByIndex(index);
		}
		
		
		/*
		 * This method will handle drop down with the help of value
		 * @param element
		 * @param value
		 */
		public void handleDropdown(WebElement element, String value) {
			Select s= new Select(element);
			s.selectByValue(value);
		}
		
		/**
		 * This method will handle drop down with the help of visible
		 * @param text
		 * @param element
		 */
		public void handleDropdown(String text,WebElement element ) {
			Select s= new Select(element);
			s.selectByVisibleText(text);
		}
		
		/**
		 * This method will perform mouse hover action on a webelement
		 * @param driver
		 * @param element
		 */
		public void mouseHoverAction(WebDriver driver,WebElement element) {
			try {
				Actions act= new Actions(driver);
				act.moveToElement(element).perform();
				System.out.println("Moved to the element and clicked on the element \n");
			}
			catch(Exception e) {
				System.err.println("Error while moving to the element & clicking \n"+e.getMessage());
			}
			
		}
		
		/**
		 * This method will perform right click action anywhere on a web page
		 * @param driver
		 */
		public void rightClickAction(WebDriver driver) {
		try{
			Actions act= new Actions(driver);
			act.contextClick().perform();
			System.out.println("Right click on the element \n");
		}
		catch(Exception e) {
			System.err.println("Right click on the element failed \n"+e.getMessage());
		}
			
	}
		
		/**
		 * This method will perform double click anywhere on the web page
		 * @param driver
		 */
		public void doubleClickAction(WebDriver driver) {
		try{
			Actions act = new Actions(driver);
			act.doubleClick().perform();
		}
		catch(Exception e) {
			System.err.println("Right click on the element failed \n"+e.getMessage());
		}
	}
		/*
		 * This method will get the url of each page.
		 * @param driver
		 * 
		 */
		
		public String logCurrentUrl(WebDriver driver) {
			if(driver==null) {
				 throw new IllegalArgumentException("WebDriver instance cannot be null.");
			}
			try {
				WebDriverWait wait =new WebDriverWait(driver,Duration.ofSeconds(10));
				//WebDriverWait wait = new WebDriverWait(driver,10);
			    wait.until(ExpectedConditions.jsReturnsValue("return document.readyState =='complete'"));
		        String currentUrl = driver.getCurrentUrl();
		        System.out.println("Current URL:-- " + currentUrl);
		        return currentUrl;
		    } catch (WebDriverException e) {
		        System.err.println("Failed to retrieve the current URL: " + e.getMessage());
		        throw e; //
		    }
		}
		
		/**
		 * This method will perform double click on a web element
		 * @param driver
		 * @param element
		 */
		public void doubleClickAction(WebDriver driver, WebElement element) {
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
	                System.err.println("Driver is null,cannot close the browser");
	            }
	        } catch (Exception e) {
	            System.err.println("Error while closing the browser:" + e.getMessage());
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
	            System.out.println("Current Date and Time:-- " + formattedDateTime);
	            return formattedDateTime;
	        } catch (Exception e) {
	            System.err.println("Error getting current date and time: " + e.getMessage());
	            return null;
	        }
	    } 
	    
	    /**
	     * This method will wait for the page to fully load by checking the document's readyState.
	     * @param driver WebDriver
	     */
	

	    /**
	     * This method will wait for the page to fully load by checking the document's readyState.
	     * @param driver Webriver instance
	     */
	    public void waitForPageToLoad(WebDriver driver) {
	        try {
	        	 JavascriptExecutor js = (JavascriptExecutor) driver;
	             while (!js.executeScript("return document.readyState").equals("complete"))
	            	 
	              System.out.println("Page is fully loaded.");//wait until the page is fully loaded
	        } catch (Exception e) {
	            System.err.println("Error while waiting for page to load: " + e.getMessage());
	            takeScreenshot(driver, "page-load-error.png"); // Optional: take screenshot on failure
	        }
	    }
	    
	    // Take screenshot
	    public void takeScreenshot(WebDriver driver,String fileName) {
	    	String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
	    	
	        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	        try {
	            FileUtils.copyFile(screenshot, new File("./screenshotS/" + fileName));
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	    
	    /**
	     * Scrolls to the element specified by a CSS or XPath selector.
	     *
	     * @param locator      CSS or XPath selector for the element to scroll to.
	     * @param locatorType  Type of selector: "css" or "xpath".
	     * @param driver       WebDriver instance.
	     */
	    public void scrollToElement(String locator, String locatorType, WebDriver driver) {
	        try {
	            WebElement element;
	            
	            if (locatorType.equalsIgnoreCase("css")) {
	                element = driver.findElement(By.cssSelector(locator));
	            } else if (locatorType.equalsIgnoreCase("xpath")) {
	                element = driver.findElement(By.xpath(locator));
	            } else {
	                throw new IllegalArgumentException("Invalid locator type: " + locatorType);
	            }

	            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
	            System.out.println("Scrolled to element: " + locator + " using " + locatorType + " locator.");
	        } catch (Exception e) {
	            System.err.println("Failed to scroll to element: " + locator + ". " + e.getMessage());
	        }
	    }

	    
	    
	    /**
	     * This method will write test execution details to a notepad file.
	     
	     * @param testName the name of the test
	     * @param duration the time taken to execute the test
	     * @param status   the result status of the test (PASS/FAIL)
	     */
	    public void writeToNotepad(String testName, long duration, String status) {
	        String filePath = "./test-results.txt"; // Define the file path

	        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) { // Open file in append mode
	            writer.write("Test Name: " + testName);
	            writer.write("\nTime Taken: " + TimeUnit.MILLISECONDS.toSeconds(duration) + " seconds");
	            writer.write("\nStatus: " + status);
	            writer.write("\n-----------------------------------------\n");
	            System.out.println("Test details written to notepad.");
	        } catch (IOException e) {
	            System.err.println("Failed to write to notepad: " + e.getMessage());
	        }
	    }
		
	    //using string.join get core web vitals
	    
	    public Map<String, Object> getCoreWebVitals(WebDriver driver) {
	    	Map<String, Object> coreWebVitals = new HashMap<>();
	        String script = String.join("",
	            "const vitals = { lcp:0,fid:0,cls:0 };",
	            
	            "const performanceObserver = new PerformanceObserver((list) => {",
	            "    for (const entry of list.getEntries()) {",
	            "        if (entry.name === 'largest-contentful-paint') {",
	            "            vitals.lcp = entry.startTime;",
	            "        }",
	            "        if (entry.name === 'first-input') {",
	            "            vitals.fid = entry.processingStart - entry.startTime;",
	            "        }",
	            "    }",
	            "});",
	        
	            "const layoutShiftObserver = new PerformanceObserver((list) => {",
	            "    list.getEntries().forEach((entry) => {",
	            "        if (!entry.hadRecentInput) {",
	            "            vitals.cls += entry.value;",
	            "        }",
	            "    });",
	            "});",
	            
	            "performanceObserver.observe({ type: 'largest-contentful-paint', buffered: true });",
	            "performanceObserver.observe({ type: 'first-input', buffered: true });",
	            "layoutShiftObserver.observe({ type: 'layout-shift', buffered: true });",
	            "return new Promise(resolve => {",
	            "    setTimeout(() => resolve(vitals), 8000);", // Wait 5 seconds to gather data
	            "});"
	        );
	        JavascriptExecutor js = (JavascriptExecutor) driver;
	        try {
	            coreWebVitals = (Map<String, Object>) js.executeAsyncScript(script);
	            System.out.println("Core Web Vitals captured: " + coreWebVitals);

	            // Validation against thresholds
	            validateCoreWebVitals(coreWebVitals);
	        } catch (Exception e) {
	            System.err.println("Failed to retrieve Core Web Vitals: " + e.getMessage());
	        }
	        return coreWebVitals;
	    }
	    
	    /*
	     * This method will validate core web vitals of the webpage.
	     */
        
	    private void validateCoreWebVitals(Map<String, Object> coreWebVitals) {
	        // Recommended thresholds for Web Vitals
	        double lcpThreshold = 2500; // 2.5 seconds
	        double fidThreshold = 100;  // 0.1 seconds
	        double clsThreshold = 0.1;  // 0.1

	        double lcp = (double) coreWebVitals.getOrDefault("lcp", -1);
	        double fid = (double) coreWebVitals.getOrDefault("fid", -1);
	        double cls = (double) coreWebVitals.getOrDefault("cls", -1);

	        if (lcp > lcpThreshold) {
	            System.err.println("Warning: LCP exceeds threshold (" + lcp + " ms > " + lcpThreshold + " ms)");
	        } else {
	            System.out.println("LCP is within the threshold: " + lcp + " ms");
	        }

	        if (fid > fidThreshold) {
	            System.err.println("Warning: FID exceeds threshold (" + fid + " ms > " + fidThreshold + " ms)");
	        } else {
	            System.out.println("FID is within the threshold: " + fid + " ms");
	        }

	        if (cls > clsThreshold) {
	            System.err.println("Warning: CLS exceeds threshold (" + cls + " > " + clsThreshold + ")");
	        } else {
	            System.out.println("CLS is within the threshold: " + cls);
	        }
	    }   
	    
	        
	    /**
	     * This method checks if the webpage is using HTTPS.
	     * @param driver The WebDriver instance
	     * @return true if the page uses HTTPS, false otherwise
	     */
	    public boolean isPageUsingHttps(WebDriver driver) {
	        try {
	            String currentUrl = driver.getCurrentUrl();
	            if (currentUrl.startsWith("https")) {
	                logger.info("The webpage is using HTTPS.");
	                return true;
	            } else {
	               // logger.warn("The webpage is not using HTTPS.");
	                return false;
	            }
	        } catch (Exception e) {
	            logger.error("Failed to determine if page is using HTTPS: ", e);
	            return false;
	        }
	    }

	    /*
	     * This method will accept or dismiss the alert popup if displayed.
	     * @param driver the WebDriver instance
         * @param acceptAlert if true, the alert will be accepted; if false, it will be dismissed
         * @return the text of the alert if present, otherwise returns null
	     */
	    
	    public String handleAlert(WebDriver driver,boolean acceptAlert) {
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
				System.err.println("No alert is present.");
				return null;
			}
	    }
	   
	    
	    public void ItestResult(long startTime) {
	    	
	    	ITestResult result = null;
	    	 long endTime = System.currentTimeMillis(); // Record the end time of the test 
			long duration = endTime - startTime; // Calculate the time taken
			String testName = result.getMethod().getMethodName(); // Get the name of the test
	         System.out.println("Test '" + testName + "' took " + TimeUnit.MILLISECONDS.toSeconds(duration) + "seconds to execute.");
	    }
	    
        /*  public void logTestDuration(ITestResult result) {
	    	
	        String testName = result.getMethod().getMethodName();
	        String status = result.isSuccess() ? "PASSED" : "FAILED";
	        long startTime = 0;
			long duration =System.currentTimeMillis()-startTime;
	        writeToNotepad(testName, duration, status);
	        System.out.println("Test '" + testName + "' status: " + status + ", duration: " + duration + " ms");
	    }*/
	    
	  
	    /*
	     * This method will extract & print the comscore code from page sourcecode 
	     * @param url The URL of the webpage to parse.
         * @return The content of the comScore script(s) as a String.
         * @throws IOException If there is an error fetching the URL.
	     */
	    public void extractComScoreCode(WebDriver driver) throws URISyntaxException {
	    
	    	 String pageUrl=driver.getCurrentUrl();
	    	 if (pageUrl == null || pageUrl.isEmpty()) {
	    	 System.err.println("Page URL is null or empty"); 
	         }
	    	// System.out.println("Page URL is:-- " +pageUrl);
	    	 
	    	// Get the HTTP response code and response time
            long startTime = System.currentTimeMillis();
            int responseCode = getResponseCode(pageUrl);
            long responseTime = System.currentTimeMillis()-startTime;
            System.out.println("Response Code for Comscore script:--" + responseCode);
          //System.out.println("Response Time for Comscore script:-- " + responseTime +"ms");
            
            
            //comscore
	        String pageSource = driver.getPageSource();
	        boolean isComscorePresent = pageSource.contains("comscore") || pageSource.contains("cs.");
	        Assert.assertTrue(isComscorePresent, "Comscore script not found \n");
	        
	        // Check for the comscore code ...
	        if (isComscorePresent) {
	             System.out.println("-----COMSCORE SCRIPT ATTACHED IN THE SOURCE CODE-----\n");

	       //Look for the comscore script..... 
	             int startIndex = pageSource.indexOf("comscore");
	             int endIndex = pageSource.indexOf("</script>", startIndex)  + "</script>".length();
	             if (startIndex != -1 && endIndex != -1) 
	             {
	             //extract & print the comscore script
	             String comscoreScript = pageSource.substring(startIndex, endIndex);
	             // Print the extracted Comscore script
	            // System.out.println(comscoreScript);  
	         }
	             else {
	                 System.err.println("Failed to extract Comscore script: Invalid indices.\n");
	          }
	        }
      }
	    /**
	     * Gets the HTTP response code for a given URL.
	     * @param pageUrl The URL to check
	     * @return The HTTP response code
	     */
	    public static int getResponseCode(String pageUrl) throws URISyntaxException  {
	        try {
	            URI uri = new URI(pageUrl);
	            URL url = uri.toURL();
	            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	            connection.setRequestMethod("GET");
	            connection.connect();
	            return connection.getResponseCode();
	        }
	        catch (IOException e) {
	            e.printStackTrace();
	            return -1; // Return -1 in case of an error
	        }
	    }
	    
	    /*
	     * This method will extract the izooto code from the webpage source code
	     * @param url The URL of the webpage to parse.
         * @return The content of the iZooto script(s) as a String.
         * @throws IOException If there is an error fetching the URL.
	     */
	    public void extractIzootoCode(WebDriver driver) throws URISyntaxException{
	    	
	    	String pageUrl=driver.getCurrentUrl();
	    	 if (pageUrl == null || pageUrl.isEmpty()) {
	    	 System.err.println("Page URL is null or empty"); 
	         }
	    	// System.out.println("Page URL is:-- " +pageUrl);
	    	 
	    	// Get the HTTP response code and response time
           long startTime = System.currentTimeMillis();
           int responseCode = getResponseCode(pageUrl);
           long responseTime = System.currentTimeMillis()-startTime;
           System.out.println("Response Code for Izooto script:--" + responseCode);

            // Get the page source
            String pageSource = driver.getPageSource();

            // Look for iZooto code 
            if (pageSource.contains("izooto")) {
                System.out.println("----IZOOTO SCRIPT FOUND IN THE SOURCE CODE------\n");
                int startIndex = pageSource.indexOf("izooto");
                int scriptStart = pageSource.lastIndexOf("<script", startIndex);
                int scriptEnd = pageSource.indexOf("</script>", scriptStart) + "</script>".length();
        
                //Exract the izooto code & print it in console
                String izootoCode = pageSource.substring(scriptStart,scriptEnd);
                //System.out.println(izootoCode);
            } 
            else {
                System.err.println("No iZooto script found in the source code \n");
            }
        }
	    
	    /*
	     * This method will extract the chartbeat code from the webpage source code
	     * @param url The URL of the webpage to parse.
         * @return The content of the iZooto script(s) as a String.
         * @throws IOException If there is an error fetching the URL.
	     */
	    public void extractChartbeatCode(WebDriver driver) throws URISyntaxException {
	    	
	    	String pageUrl=driver.getCurrentUrl();
	    	 if (pageUrl == null || pageUrl.isEmpty()) {
	    	 System.err.println("Page URL is null or empty"); 
	         }
	    	// System.out.println("Page URL is:-- " +pageUrl);
	    	 
	    	// Get the HTTP response code and response time
           long startTime = System.currentTimeMillis();
           int responseCode = getResponseCode(pageUrl);
           long responseTime = System.currentTimeMillis()-startTime;
           System.out.println("Response Code for Chartbeat script:--" + responseCode);
	    	//get the pagesource code
	    	String pageSource= driver.getPageSource();
	    	
	    	//Check for the Chartbeat script 
	    	if(pageSource.contains("chartbeat")) {
	    		System.out.println("-----CHARTBEAT SCRIPT FOUND IN THE SOURCE CODE----- \n");
	    		
	    		int startIndex = pageSource.indexOf("chartbeat");
	    		int scriptStart = pageSource.lastIndexOf("<script",startIndex);
	    		int scriptEnd = pageSource.indexOf("</script>",scriptStart)+ "</script>".length();
	    		
	    		//Extract the chartbeat code and print it in console
	    		String chartBeatCode = pageSource.substring(scriptStart,scriptEnd);
	    		//System.out.println(chartBeatCode);
	    		}
	    	else {
	    		System.err.println("No ChartBeat script found in the source code");
	    	
	    	}
	    }
	    
	    /*
	     * This method will extract and print the gt script 
	     * from the source code.
	     * @param url The URL of the webpage to parse.
         * @return The content of the iZooto script(s) as a String.
         * @throws IOException If there is an error fetching the URL.
	     * 
	     */
	    public void extractGoogleManager(WebDriver driver) throws URISyntaxException {
	    	String pageUrl=driver.getCurrentUrl();
	    	 if (pageUrl == null || pageUrl.isEmpty()) {
	    	 System.err.println("Page URL is null or empty"); 
	         }
	    	// System.out.println("Page URL is:-- " +pageUrl); 
	    	// Get the HTTP response code and response time
           long startTime = System.currentTimeMillis();
           int responseCode = getResponseCode(pageUrl);
           long responseTime = System.currentTimeMillis()-startTime;
           System.out.println("Response Code for GT script:--" + responseCode);
           
	    	//Get the page source code
	    	String pageSource=driver.getPageSource();
	    	if(pageSource.contains("googletagmanager")) {
	    		
	    	System.out.println("-----GT SCRIPT FOUND IN THE SOURCE CODE----- \n");
	    	
	    	//Check for the gt script in the source code
	    	int startIndex = pageSource.indexOf("googletagmanager");
	    	int scriptStart = pageSource.lastIndexOf("<script",startIndex);
	    	int scriptEnd = pageSource.indexOf("</script>",scriptStart)+"</script>".length();
	    	
	    	//Extract the gt script from the source code and print it....
	    	String GoogleTagManager=pageSource.substring(scriptStart,scriptEnd);
	    	//System.out.println(GoogleTagManager);
	    	}
	    	else {
	    		System.err.println("No GT script found in the source code");  	
	    }
    }    
}

