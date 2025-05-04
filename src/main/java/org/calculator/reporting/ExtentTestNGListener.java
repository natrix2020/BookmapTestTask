package org.calculator.reporting;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentTestNGListener implements ITestListener {

    private static ExtentReports extentReports;
    private static final ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    /**
     * Called before any tests start (at suite level).
     */
    @Override
    public void onStart(ITestContext context) {
        // Initialize ExtentSparkReporter for the HTML report
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("test-output/SparkReport.html");
        sparkReporter.config().setDocumentTitle("Test Execution Report");
        sparkReporter.config().setReportName("Calculator Test Results");
        sparkReporter.config().setTheme(Theme.STANDARD); // You can also use Theme.DARK

        // Create ExtentReports and attach the reporter
        extentReports = new ExtentReports();
        extentReports.attachReporter(sparkReporter);

        // Add system-specific information to the report
        extentReports.setSystemInfo("Java Version", System.getProperty("java.version"));
        extentReports.setSystemInfo("Operating System", System.getProperty("os.name"));
        extentReports.setSystemInfo("Tester", "Andrii Shevchyk");
    }

    /**
     * Create a test entry when a test starts.
     */
    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest test = extentReports.createTest(result.getMethod().getMethodName(),
                result.getMethod().getDescription());
        extentTest.set(test);
    }

    /**
     * Mark the test as passed and log this in the report.
     */
    @Override
    public void onTestSuccess(ITestResult result) {
        extentTest.get().log(Status.PASS, "Test Passed");
    }

    /**
     * Mark the test as failed and log the error information.
     */
    @Override
    public void onTestFailure(ITestResult result) {
        extentTest.get().log(Status.FAIL, "Test Failed: " + result.getThrowable().getMessage());
    }

    /**
     * Mark the test as skipped and log the reason for skipping.
     */
    @Override
    public void onTestSkipped(ITestResult result) {
        extentTest.get().log(Status.SKIP, "Test Skipped: " + result.getThrowable());
    }

    public static void logCalculationStep(String message) {
        extentTest.get().info(message);
    }

    /**
     * Flush the ExtentReports instance to write the report to disk.
     */
    @Override
    public void onFinish(ITestContext context) {
        if (extentReports != null) {
            extentReports.flush();
        }
    }
}