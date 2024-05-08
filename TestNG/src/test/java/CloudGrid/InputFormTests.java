package CloudGrid;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

public class InputFormTests extends BaseTest{
	
	@Test
    public void verifyTitle()
	{
        String methodName = new Exception().getStackTrace()[0].getMethodName();
        
        System.out.println("Test execution started : " + methodName);
        
        extentTest = extentReports.createTest(methodName,"verify_Page_Title");
        extentTest.log(Status.INFO,"Starting test to verify title");
 
        String title ="Selenium Grid Online | Run Selenium Test On Cloud";
        Assert.assertEquals(title,driver.getTitle());
        extentTest.log(Status.INFO,"Verified the expected page title as : " + title);
        
        status = "passed";
        
        System.out.println("Test execution completed : " + methodName);
    }

	@Test
    public void singleInputTest() 
	{
		String methodName = new Exception().getStackTrace()[0].getMethodName();
		
		System.out.println("Test execution started : " + methodName);
		
		extentTest = extentReports.createTest(methodName,"single_input_test");
		extentTest.log(Status.INFO,"Starting test to verify single input");
		
		//Clicks on the simple form demo option in the selenium playground
		extentTest.log(Status.INFO,"Clicking on Simple Form Demo");
        driver.findElement(By.xpath("//*[text()='Simple Form Demo']")).click();
            
        //Enter the message in the enter message input box
        String expectedMessage = "Hello World";
        extentTest.log(Status.INFO,"Entering the message as : " + expectedMessage);
        driver.findElement(By.id("user-message")).sendKeys(expectedMessage);
            
        //Click on Get checked value button
        extentTest.log(Status.INFO,"Clicking on Get checked value");
        driver.findElement(By.id("showInput")).click();
        
        //Retrieve the entered user message
        String actualMessage = driver.findElement(By.id("message")).getText();
        Assert.assertEquals(actualMessage, expectedMessage, "Expected and actual texts do not match.");
        extentTest.log(Status.INFO,"Verified the expected message as : " + expectedMessage);
        
        status = "passed";
        
        System.out.println("Test execution completed : " + methodName);
    }
	
	@Test
    public void multipleInputTest() 
	{
		String methodName = new Exception().getStackTrace()[0].getMethodName();
		
		System.out.println("Test execution started : " + methodName);
		
		extentTest = extentReports.createTest(methodName,"multiple_input_test");
		extentTest.log(Status.INFO,"Starting test to verify multiple input");
		
		//Click on the simple form demo option in the selenium playground
		extentTest.log(Status.INFO,"Clicking on Simple Form Demo");
        driver.findElement(By.xpath("//*[text()='Simple Form Demo']")).click();
        
        //Enter the first and second value
        extentTest.log(Status.INFO,"Entering the values as 5 and 10");
        driver.findElement(By.id("sum1")).sendKeys("5");
        driver.findElement(By.id("sum2")).sendKeys("10");
        
        //Click on the Get Sum button
        extentTest.log(Status.INFO,"Clicking on Get Sum");
        driver.findElement(By.xpath("//button[text()='Get Sum']")).click();
 
        //Fetch the result value and assert same
        String actualSum = driver.findElement(By.id("addmessage")).getText();
        
        //Valid assert 
//        Assert.assertEquals(actualSum,"15", "Expected and actual values do not match.");
        
        //Invalid assert to fail intentionally and demonstrate capture screenshot implementation
        Assert.assertEquals(actualSum,"20", "Expected and actual values do not match.");
        
        extentTest.log(Status.INFO,"Verified the expected sum as : 15");
        
        status = "passed";
        
        System.out.println("Test execution completed : " + methodName);
	}

}
