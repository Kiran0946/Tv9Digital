package tv9_digital_project;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import java.util.Map;
import java.util.HashMap;
public class CoreWebVitalsUtility {

   private JavascriptExecutor jsExecutor;

	  // Constructor to initialize the JavaScript executor
	   public CoreWebVitalsUtility(JavascriptExecutor jsExecutor) {
	   this.jsExecutor = jsExecutor;
	   
	   }

	   /**
	   * Method to log Core Web Vitals using injected JavaScript.
	   * This uses the PerformanceObserver API to measure LCP,FID & CLS.
	   */
	   
	   public Map<String,Object> getCoreWebVitals(){
	       
       Map<String, Object> vitals = new HashMap<>();
           
	 //JavaScript to track Core Web Vitals using PerformanceObserver
	     
	   	try {
	        
	        String script = "const vitals = { LCP: 0, FID: 0, CLS: 0 }; " +
	                        "new PerformanceObserver((list) =>{ " +
	                        "  list.getEntries().forEach((entry) => { " +
                            "    if (entry.name === 'largest-contentful-paint') vitals.LCP = entry.renderTime || entry.loadTime; " +
	                        "    if (entry.entryType === 'first-input') vitals.FID = entry.processingStart - entry.startTime; " +
	                        "    if (entry.entryType === 'layout-shift' && !entry.hadRecentInput) vitals.CLS += entry.value; " +
	                        "  }); " +
	                        "}).observe({ type: 'largest-contentful-paint', buffered: true }); " +
	                        "new PerformanceObserver((list) => { " +
	                        "  list.getEntries().forEach((entry) => { " +
	                        "    if (entry.entryType === 'first-input') vitals.FID = entry.processingStart - entry.startTime; " +
	                        "  }); " +
	                        "}).observe({ type: 'first-input', buffered: true }); " +
                            "new PerformanceObserver((list) => { " +
	                        "  list.getEntries().forEach((entry) => { " +
	                        "    if (entry.entryType === 'layout-shift' && !entry.hadRecentInput) vitals.CLS += entry.value; " +
	                        "  }); " +
	                        "}).observe({ type: 'layout-shift', buffered: true }); " +
	                        "return vitals;";

	            // Execute the script and return the web vitals
	            
	            vitals =(Map<String,Object>)jsExecutor.executeScript(script);

	            System.out.println("Core Web Vitals :-");
	            System.out.println("LCP (Largest Contentful Paint):- " + vitals.get("LCP"));
	            System.out.println("FID (First Input Delay):- " + vitals.get("FID"));
	            System.out.println("CLS (Cumulative Layout Shift):- " + vitals.get("CLS"));

	        } catch (Exception e) {
	            System.err.println("Failed to get Core Web Vitals: " + e.getMessage());
	        }

	        return vitals;
	    }
	}

