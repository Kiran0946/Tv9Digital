package tv9_digital_project;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {

	 private int retryCount = 0;
	    private static final int maxRetryCount = 2; // Set the number of retry attempts

	    @Override
	    public boolean retry(ITestResult result) {
	        if (retryCount < maxRetryCount) {
	            retryCount++;
	            System.out.println("Retrying test " + result.getName() + " with status "
	                    + getResultStatusName(result.getStatus()) + " for the " + retryCount + " time(s).");
	            return true;
	        }
	        return false;
	    }

	    public String getResultStatusName(int status) {
	        String resultName = null;
	        if (status == 1)
	            resultName = "SUCCESS";
	        if (status == 2)
	            resultName = "FAILURE";
	        if (status == 3)
	            resultName = "SKIP";
	        return resultName;
	    }
}
