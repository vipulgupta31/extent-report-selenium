package ParallelExecution;

import java.io.File;
import java.io.IOException;
import java.net.*;
import java.util.HashMap;
import java.util.Random;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.*;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.google.common.io.Files;

public class BaseTest {

	public static ExtentSparkReporter extentSparkReporter;
	public static ExtentReports extentReports;
	public static ExtentTest extentTest;
	
	//to implement thread safety
	protected static ThreadLocal<ExtentTest> extentTestThread = new ThreadLocal<ExtentTest>();

	public RemoteWebDriver driver = null;
	String username = System.getenv("LT_USERNAME") == null ? "vipul31gupta" : System.getenv("LT_USERNAME");
	String accessKey = System.getenv("LT_ACCESS_KEY") == null ? "UQAy0SAYeTlVvtIaSEBH0wMxXE06MmwHxXb3g13mzHqoZ5T3uZ" : System.getenv("LT_ACCESS_KEY");
	String status = "failed";

	@BeforeTest
	public void startReporter() 
	{
		extentSparkReporter = new ExtentSparkReporter(
				System.getProperty("user.dir") + "/test-output/extentReport.html");
		extentReports = new ExtentReports();
		extentReports.attachReporter(extentSparkReporter);

		// configuration items to change the look and feel
		// add content, manage tests etc
		extentSparkReporter.config().setDocumentTitle("Simple Automation Report");
		extentSparkReporter.config().setReportName("Test Report");
		extentSparkReporter.config().setTheme(Theme.STANDARD);
		extentSparkReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
	}

	@BeforeMethod
	public void setUp() 
	{
		try {
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.setPlatformName("Windows 10");
			chromeOptions.setBrowserVersion("124.0");

			HashMap<String, Object> ltOptions = new HashMap<String, Object>();
			ltOptions.put("build", "Extent Reports using Selenium Java");
			ltOptions.put("name", "Extent Reports using Selenium Java");
			ltOptions.put("w3c", true);
			chromeOptions.setCapability("LT:Options", ltOptions);

			driver = new RemoteWebDriver(
					new URL("https://" + username + ":" + accessKey + "@hub.lambdatest.com/wd/hub"), chromeOptions);

			driver.get("https://www.lambdatest.com/selenium-playground/");

		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	@AfterMethod
	public void updateResultAndcloseDriver(ITestResult result) 
	{
		if(result.getStatus() == ITestResult.FAILURE) {
			captureScreenshot();
        	extentTestThread.get().log(Status.FAIL,result.getThrowable());
        }
        else if(result.getStatus() == ITestResult.SUCCESS) {
        	extentTestThread.get().log(Status.PASS, result.getTestName());
        }
        else {
        	extentTestThread.get().log(Status.SKIP, result.getTestName());
        }
	    
		driver.executeScript("lambda-status=" + status);
		driver.quit();

	}

	@AfterTest
	public void endReport() 
	{
		extentReports.flush();

	}

	public void captureScreenshot() 
	{
		try 
		{
			System.out.println("Taking screenshot for failed assert");
			String screenshotPath = System.getProperty("user.dir") + "/test-output/screenshots";
			File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			
			System.out.println("Adding screenshot to extent report");
			String screenshotName = "screenshot_" + new Random().nextInt(999) + ".png";
			screenshotPath = screenshotPath + File.separator + screenshotName;
			Files.copy(screenshot, new File(screenshotPath));
			extentTestThread.get().addScreenCaptureFromPath(screenshotPath);
		} catch (IOException e) 
		{
			e.printStackTrace();
		}

	}
}
