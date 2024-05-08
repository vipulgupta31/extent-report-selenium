package LocalGrid;

import org.testng.ITestResult;
import org.testng.annotations.*;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class BaseTest {
	
	ExtentSparkReporter extentSparkReporter;
    ExtentReports extentReports;
    ExtentTest extentTest;
    
    @BeforeTest
    public void startReporter()
    {
    	extentSparkReporter  = new ExtentSparkReporter(System.getProperty("user.dir") + "/test-output/extentReport.html");
    	extentReports = new ExtentReports();
    	extentReports.attachReporter(extentSparkReporter);

    	//configuration items to change the look and feel
        //add content, manage tests etc
    	extentSparkReporter.config().setDocumentTitle("Simple Automation Report");
    	extentSparkReporter.config().setReportName("Test Report");
    	extentSparkReporter.config().setTheme(Theme.STANDARD);
    	extentSparkReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
    }
    
    @AfterMethod
    public void getResult(ITestResult result) {
        if(result.getStatus() == ITestResult.FAILURE) {
        	extentTest.log(Status.FAIL,result.getThrowable());
        }
        else if(result.getStatus() == ITestResult.SUCCESS) {
        	extentTest.log(Status.PASS, result.getTestName());
        }
        else {
        	extentTest.log(Status.SKIP, result.getTestName());
        }
    }
 
    @AfterTest
    public void tearDown() {
        //to write or update test information to reporter
    	extentReports.flush();
    }

}
