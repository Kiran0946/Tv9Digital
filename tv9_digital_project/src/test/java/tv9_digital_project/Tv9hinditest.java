package tv9_digital_project;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Tv9hinditest extends BaseClass{

	private static final String URL = "https://www.tv9hindi.com/ \n ";//url
	
@BeforeMethod
	
     public void beforeMethod() {
		
       driver.get(URL);
      System.out.println("Navigated to URL:---\n  "  + URL);
}
	
        @Test
        
        public void extractIzootoCode(){

            // Get the page source
            String pageSource = driver.getPageSource();

            // Look for iZooto code 
            if (pageSource.contains("izooto")) {
                System.out.println("iZooto Code Found:\n");
                int startIndex = pageSource.indexOf("izooto");
                int scriptStart = pageSource.lastIndexOf("<script", startIndex);
                int scriptEnd = pageSource.indexOf("</script>", scriptStart) + "</script>".length();
        
                //Exract the izooto code & print it in console
                String izootoCode = pageSource.substring(scriptStart,scriptEnd);
                System.out.println(izootoCode);
            } else {
                System.out.println("No iZooto Code Found \n");
            }
        }
        
        @Test

        public void extractComScoreCode() throws InterruptedException{
		
         String Pagesource = driver.getPageSource();
        
        
		boolean isComscorePresent = Pagesource.contains("comscore") || Pagesource.contains("cs.");
        Assert.assertTrue(isComscorePresent, "Comscore script not found on the page \n");

        // Print a message if script is found
        if (isComscorePresent) {
            System.out.println("Comscore script is present on the page. \n");
            
            int startIndex = Pagesource.indexOf("comscore");
            int endIndex = Pagesource.indexOf("</script>", startIndex)  + "</script>".length();
       
            if (startIndex != -1 && endIndex != -1) {
            //extract & print the comscore script
            String comscoreScript = Pagesource.substring(startIndex, endIndex);
             
            // Print the extracted Comscore script
            System.out.println("->Comscore Script Found:------\n ");
            System.out.println(comscoreScript);     
        }
            else {
                System.out.println("Failed to extract Comscore script: Invalid indices.\n");
                 }
        }

   }

       /* @AfterClass
        
        public void tearDown() {
            if (driver != null) {
                driver.quit();
            }
        }*/
   
}