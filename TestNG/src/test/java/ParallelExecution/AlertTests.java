package ParallelExecution;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

public class AlertTests extends BaseTest
{
	@Test
    public void testSimpleAlertMessage() 
	{
		String methodName = new Exception().getStackTrace()[0].getMethodName();
		
		System.out.println("Test execution started : " + methodName);
		
        extentTest = extentReports.createTest(methodName,"simple_alert_message");
        extentTestThread.set(extentTest);
        extentTestThread.get().log(Status.INFO,"Starting test to verify simple alert message");
		
		//click on Bootstrap alerts
        extentTestThread.get().log(Status.INFO,"Clicking on Bootstrap Alerts");
        driver.findElement(By.linkText("Bootstrap Alerts")).click();
		
        //click on Normal success message
        extentTestThread.get().log(Status.INFO,"Clicking on Normal Success Message");
        driver.findElement(By.xpath("//*[text()='Normal Success Message']")).click();
        
        //fetch actual message and compare with expected message
        String expectedMessage = "×\nNormal success message. To close use the close button.";
        String actualMessage = driver.findElement(By.xpath("//*[contains(@class,'alert-success-manual')]")).getText();
        Assert.assertEquals(actualMessage, expectedMessage, "Expected and actual mmessage do not match.");
        extentTestThread.get().log(Status.INFO,"Verified the expected message as : " + expectedMessage);
        
        status = "passed";
        
        System.out.println("Test execution completed : " + methodName);
    }
}
