package junit;


import org.junit.*;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class TestExtentReportJUnit {
	
	ExtentSparkReporter extentSparkReporter;
    ExtentReports extentReports;
    ExtentTest extentTest;
    
    @Before
    public void startReporter()
    {
    	extentSparkReporter  = new ExtentSparkReporter(System.getProperty("user.dir") + "/test-output/extentReport.html");
    	extentReports = new ExtentReports();
    	extentReports.attachReporter(extentSparkReporter);

    	//configuration items to change the look and feel
        //add content, manage tests etc
    	extentSparkReporter.config().setDocumentTitle("Simple JUnit Automation Report");
    	extentSparkReporter.config().setReportName("Test Report");
    	extentSparkReporter.config().setTheme(Theme.STANDARD);
    	extentSparkReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
    }
    
    @Test
	public void testPassed() {
		extentTest = extentReports.createTest("Test Case 1", "This test case has passed");

	}
 
    @After
    public void tearDown() {
        //to write or update test information to reporter
    	extentReports.flush();
    }

}
