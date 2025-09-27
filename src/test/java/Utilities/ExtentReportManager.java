package Utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ExtentReportManager implements ITestListener {

    private static ExtentReports extent;
    private static ExtentTest test;
    private static ExtentSparkReporter htmlReporter;
    String repName;

    public void onStart(ITestContext context) {
        // Initialize Extent Reports
        String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        repName="Test-Report-"+timestamp +".html";
        htmlReporter = new ExtentSparkReporter(System.getProperty("user.dir")+"/reports/" + repName);
        htmlReporter.config().setDocumentTitle("Automation Test Report");
        htmlReporter.config().setReportName("Test Report");
        htmlReporter.config().setTheme(Theme.STANDARD);

        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("OS", context.getCurrentXmlTest().getParameter("os"));
        extent.setSystemInfo("Browser", context.getCurrentXmlTest().getParameter("browser"));
        extent.setSystemInfo("Tester Name", "Your Name");

        List<String> includedGroups = context.getCurrentXmlTest().getIncludedGroups();
        if(!includedGroups.isEmpty())
        {
            extent.setSystemInfo("Groups",includedGroups.toString());
        }
    }

    // This method will run before the execution of each test case
    @Override
    public void onTestStart(ITestResult result) {
        test = extent.createTest(result.getMethod().getMethodName()); // Create a new test entry in the report
        test.log(Status.INFO, "Test is starting: " + result.getMethod().getMethodName());
    }

    // This method will run when a test case fails
    @Override
    public void onTestFailure(ITestResult result) {
        test.log(Status.FAIL, "Test Failed: " + result.getMethod().getMethodName());
        test.log(Status.FAIL, "Test Failure Reason: " + result.getThrowable());

        // Optionally, capture and attach a screenshot on failure
        try {
            String screenshotPath = new Utils().captureScreenshot(result.getMethod().getMethodName()); // You'll need to implement captureScreenshot
            test.addScreenCaptureFromPath(screenshotPath);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    // This method will run when a test case succeeds
    @Override
    public void onTestSuccess(ITestResult result) {
        test.log(Status.PASS, "Test Passed: " + result.getMethod().getMethodName());
    }

    // This method will run when a test case is skipped
    @Override
    public void onTestSkipped(ITestResult result) {
        test.log(Status.SKIP, "Test Skipped: " + result.getMethod().getMethodName());
    }


    @Override
    public void onFinish(ITestContext context) {
        extent.flush(); // This will flush the report and ensure that the changes are written to the file
    }
}
