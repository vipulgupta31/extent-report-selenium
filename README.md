# How to generate ExtentReports in Selenium
This repository holds the beginner code for anyone who wants to understand and start using ExtentReports for reporting in their automation framework.

## What are Extent Reports?
Extent Report is an open-source library used for generating test reports in automation testing. It has been more widely used for report generation than the inbuilt reports in various test frameworks because of its enhanced features and customisation. It is a simple yet powerful reporting library that can be integrated with the automation framework for generating the automation test report.

## How to generate the Extent Reports in TestNG?
Three classes are used for generating and customising the Extent Reports in Selenium. They are:
1. ExtentHtmlReporter if using the version below 4.1.x else ExtentSparkReporter on latest version 5. In this blog, we are using ExtentSparkReporter.
2. ExtentReports
3. ExtentTest

```
ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/test-output/extentReport.html");

ExtentReports extentReports = new ExtentReports();
extentReports.attachReporter(extentSparkReporter);
extentSparkReporter.config().setDocumentTitle("Simple Automation Report");
extentSparkReporter.config().setReportName("Test Report");
extentSparkReporter.config().setTheme(Theme.STANDARD);
extentSparkReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");

ExtentTest extentTest = extentReports.createTest("Test Case", "Test description");
```
## How to capture screenshots in Extent Reports?
```
public void captureScreenshot() 
{
  try {
    String screenshotPath = System.getProperty("user.dir") + "/test-output/screenshots";
    File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
    String screenshotName = "screenshot_" + new Random().nextInt(999) + ".png";
    screenshotPath = screenshotPath + File.separator + screenshotName;
    Files.copy(screenshot, new File(screenshotPath));
    extentTest.addScreenCaptureFromPath(screenshotPath);
  }
  catch (IOException e) {
			e.printStackTrace();
  }
}
```
## About Project
It is created using Selenium with Java, TestNG, and Maven for Web-based automation

This is the list of tools, being used in this framework:
1. Apache Maven
2. Java
3. TestNG Framework
4. Selenium WebDrvier

## Steps for Execution on LambdaTest Cloud Platform
1. Import this project in Eclipse/IntelliJ as an “Existing Maven Project”
2. Update your LambdaTest account username and accessKey in BaseTest.java. You can find these in the Profile section. 
3. Execute any one of the test cases by right-clicking and selecting the Run as the TestNG option.
4. You can see the logs coming on Console as your execution progresses.
5. Log into your LambdaTest dashboard to see the detailed execution results for the executed test case.
