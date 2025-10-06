package com.clarusways.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.text.SimpleDateFormat;
import java.util.Date;

public class T10_TestListener implements ITestListener {

    private static ExtentReports reportManager;
    private static ThreadLocal<ExtentTest> currentTest = new ThreadLocal<>();

    static {
        String timestamp = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date());
        ExtentSparkReporter htmlReporter = new ExtentSparkReporter("test-results/T13_ExtentReports/test-report_" + timestamp + ".html");
        reportManager = new ExtentReports();
        reportManager.attachReporter(htmlReporter);

        // System Configuration
        reportManager.setSystemInfo("Operating System", System.getProperty("os.name"));
        reportManager.setSystemInfo("Java Version", System.getProperty("java.version"));
        reportManager.setSystemInfo("Test Engineer", "Clarusways Team");
        reportManager.setSystemInfo("Environment", "QA");
    }

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest testCase = reportManager.createTest(result.getMethod().getMethodName());
        currentTest.set(testCase);
        currentTest.get().log(Status.INFO, "Starting test execution: " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        currentTest.get().log(Status.PASS, "Test completed successfully: " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        currentTest.get().log(Status.FAIL, "Test execution failed: " + result.getName());
        currentTest.get().log(Status.FAIL, "Error details: " + result.getThrowable());

        // Capture screenshot on failure
        try {
            String screenshotLocation = captureScreenshot(result.getMethod().getMethodName());
            currentTest.get().addScreenCaptureFromPath(screenshotLocation);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        currentTest.get().log(Status.SKIP, "Test was skipped: " + result.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        reportManager.flush();
    }

    private String captureScreenshot(String testName) {
        // Screenshot implementation will be handled in T08_ReporterUtil
        return "test-evidence/screenshots/" + testName + "_" + System.currentTimeMillis() + ".png";
    }
}