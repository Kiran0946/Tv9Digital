package tv9_digital_project;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;

import org.openqa.selenium.devtools.v127.network.Network;
import org.openqa.selenium.devtools.v127.performance.Performance;
import org.openqa.selenium.devtools.v127.performance.model.Metric;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

	//@SuppressWarnings("unused")
	
	public class Webpagevital_test {
	    public static void main(String[] args) {
	    
	    	WebDriver driver =null;
	    	try {
	    	//Open chrome browser
	   	    WebDriverManager.chromedriver().setup();
	   	   
	       
	        
	        // Initialize ChromeDriver with ChromeOptions
	        ChromeOptions options = new ChromeOptions();
	        driver = new ChromeDriver(options);
	        
            WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(12));
	        
	       
	        // Get the DevTools instance and create a session
	        DevTools devTools = ((ChromeDriver) driver).getDevTools();
	        devTools.createSession();

	        // Enable network and performance monitoring
	        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
	        devTools.send(Performance.enable(Optional.empty()));

	        // Navigate to the webpage
	        driver.get("https://www.tv9hindi.com/");
	        
	        Thread.sleep(5000);

	        // Collect performance metrics
	        List<Metric> metrics = devTools.send(Performance.getMetrics());

	        // Loop through the metrics and print the Core Web Vitals
	        System.out.println("Core Web Vitals are--");
	        for (Metric metric : metrics) {
	            String name = metric.getName();
	            if (name.equals("LargestContentfulPaint") || name.equals("CumulativeLayoutShift")) {
	                System.out.println(name + ": " + metric.getValue());
	            }
	        }

	        // Close the browser
	        driver.quit();
	    }
	    	catch (Exception e) {
	    		e.printStackTrace();  // Catch and print exceptions for debugging
	    	}
	    }
	    
	}