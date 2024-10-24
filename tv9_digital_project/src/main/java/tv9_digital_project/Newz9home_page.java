package tv9_digital_project;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

@SuppressWarnings("unused")
public class Newz9home_page {
public static void main(String[] args) throws InterruptedException {
	
	//Open Chrome browser
	WebDriverManager.chromedriver().setup();
	WebDriver driver=new ChromeDriver();
     
	 /*Open Firefox browser
	 WebDriverManager.firefoxdriver().setup();
     WebDriver driver= new FirefoxDriver();*/
     WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(50));

     //maximize window
	 driver.manage().window().maximize();
	 
	 //wait for the page to load
     driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    
    //Launch Website 
    driver.get("https://www.news9live.com/");
    
    //click on change mode
    driver.findElement(By.xpath("//span[@id='mode_name']")).click();
    
    
   
    //Handle notification popup......
   /* try {
    
    	wait.until(ExpectedConditions.alertIsPresent());
    	
        // If the alert is present,it will be accepted
        driver.switchTo().alert().accept();
        }  
    catch (NoAlertPresentException e) {
        // if  no alert is present
    	System.out.println("No alert present to accept.");
    	
       
        }*/
    
    driver.switchTo().defaultContent();
    
    //click on 
    //click on India tab
    driver.findElement(By.xpath("//header[@class='main_header']//li[3]")).click();
    
    //click on latest tab
     driver.findElement(By.xpath("//a[@title='Get to know more about Latest News from India and around the world']")).click();
     
     
     //click on change mode
     driver.findElement(By.xpath("//span[@id='mode_name']")).click();
     
    
    
    //click on lifestyle tab
    driver.findElement(By.xpath("//header[@class='main_header']//li[5]")).click();
    String tit= driver.getTitle();
    System.out.println("The title of the lifestyle page--" +tit);
    
    //click on home button
    driver.findElement(By.xpath("//header[@class='main_header']//li[1]")).click();
   
   
    //get the handles of the web page
    String handle=driver.getWindowHandle();
    System.out.println("The handle of the website is--" +handle);
    
    
    
    //get the page url
    String url=driver.getCurrentUrl();
    System.out.println("The url of the page is--" +url);
    
    //get the webpage title
    String title=driver.getTitle();
    System.out.println("The title of the webpage is--" +title);
    
    //gey the source of webpage...
   // String pageSource=driver.getPageSource();
    //System.out.println("The page source of the website is --"+pageSource);
   
    //close browser
    driver.close();
    
    System.out.println("WEBSITE LAUNCH SUCCESSFULL");
    

     }
}
