package tv9_digital_project;


import java.time.Duration;
import java.util.Optional;

import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.openqa.selenium.devtools.v85.network.Network;
import org.openqa.selenium.devtools.v85.network.model.Request;
import org.openqa.selenium.devtools.v85.network.model.RequestId;
import org.openqa.selenium.devtools.v85.network.model.Response;

import io.github.bonigarcia.wdm.WebDriverManager;

public class NetworkCall {

	public static void main(String[] args) {
		
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		WebDriver driver=new ChromeDriver(options);
	     
	     WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(50));

	     //maximize window
		 driver.manage().window().maximize();
		 
		 //Intialize DevTools 
		 DevTools devTools = ((ChromeDriver) driver).getDevTools();
	        devTools.createSession();
	        
	     // Enable network tracking
	        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
	        
	        
	        // Listener for network requests
	        devTools.addListener(Network.requestWillBeSent(), request -> logRequestDetails(request.getRequest()));

	        // Listener for network responses
	        devTools.addListener(Network.responseReceived(), responseReceived -> logResponseDetails(responseReceived.getResponse()));

	          
	        // Listener for timing details
	        devTools.addListener(Network.loadingFinished(), loadingFinished -> logTimingDetails(loadingFinished.getRequestId(), loadingFinished.getEncodedDataLength()));
	      	        
	        // webpage to navigate and capture network traffic
	        driver.get("https://www.tv9hindi.com/");
	        
	        // Cleanup and close 
	        driver.quit();
	}
	        
	        private static void logRequestDetails(Request request) {
	            System.out.println("===== Request Details =====");
	            System.out.println("URL: " + request.getUrl());
	            System.out.println("Method: " + request.getMethod());
	            System.out.println("Headers: " + request.getHeaders());
	            request.getPostData().ifPresent(postData -> System.out.println("Post Data: " + postData));
	            System.out.println("  ===========================");
	        }

	        private static void logResponseDetails(Response response) {
	            System.out.println("===== Response Details =====");
	            System.out.println("URL: " + response.getUrl());
	            System.out.println("Status Code: " + response.getStatus());
	            System.out.println("Headers: " + response.getHeaders());
	            System.out.println("MIME Type: " + response.getMimeType());
	            System.out.println("   ============================");
	        }

	        private static void logTimingDetails(RequestId requestId, Number number) {
	            System.out.println("===== Timing & Data Details =====");
	            System.out.println("Request ID: " + requestId);
	            System.out.println("Encoded Data Length: " + number);
	            System.out.println("   =================================");

	        
       }
}


