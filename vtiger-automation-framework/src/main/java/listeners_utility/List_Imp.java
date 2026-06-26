package listeners_utility;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import base_utility.BaseClass;
import generic_utility.JavaUtility;
import generic_utility.WebDriverUtility;

/**
 * List_Imp — TestNG Listener for ExtentReports integration.
 *
 * Implements:
 *   - ISuiteListener : creates / flushes ExtentReports at suite level
 *   - ITestListener  : logs PASS / FAIL / SKIP per test method
 
 * @author Preeti chaurasiya
 */
public class List_Imp implements ITestListener, ISuiteListener {

    public ExtentReports report;

    private ThreadLocal<ExtentTest> tlTest = new ThreadLocal<>();

    // ==============================
    // SUITE LEVEL — Report init
    // ==============================
    @Override
    public void onStart(ISuite suite) {
        System.out.println("[LISTENER] Suite Started: " + suite.getName());

        String time = JavaUtility.genCurrentTime();
        ExtentSparkReporter spark = new ExtentSparkReporter("./advance_reports/" + time + ".html");
        spark.config().setDocumentTitle("Vtiger CRM Automation Report");
        spark.config().setReportName("Vtiger CRM Test Execution");
        spark.config().setTheme(Theme.STANDARD);

        report = new ExtentReports();
        report.attachReporter(spark);

        report.setSystemInfo("Project",     "Vtiger CRM Automation");
        report.setSystemInfo("Author",      "Preeti");
        report.setSystemInfo("Environment", "localhost:8888");
        report.setSystemInfo("Tool",        "Selenium + TestNG + Java");
    }

    @Override
    public void onFinish(ISuite suite) {
        if (report != null) {
            report.flush();
        }
        System.out.println("[LISTENER] Suite Finished: " + suite.getName());
    }

    // ==============================
    // TEST METHOD LEVEL — Logging
    // ==============================
    @Override
    public void onTestStart(ITestResult result) {
        String methodName = result.getMethod().getMethodName();
        // FIX 1: store ExtentTest in ThreadLocal
        ExtentTest test = report.createTest(methodName);
        tlTest.set(test);
        System.out.println("[LISTENER] Test Started: " + methodName);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        String methodName = result.getMethod().getMethodName();
        tlTest.get().log(Status.PASS, methodName + " — PASSED");
        System.out.println("[LISTENER] Test PASSED: " + methodName);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String methodName = result.getMethod().getMethodName();
        tlTest.get().log(Status.FAIL, methodName + " — FAILED");
        tlTest.get().log(Status.FAIL, "Reason: " + result.getThrowable().getMessage());

        try {
            Object testInstance = result.getInstance();
            if (testInstance instanceof BaseClass) {
                WebDriver driver = ((BaseClass) testInstance).driver;
                if (driver != null) {
                    WebDriverUtility wdUtil = new WebDriverUtility(driver);
                    wdUtil.takeScreenshotWithTimestamp("FAIL_" + methodName);
                    tlTest.get().log(Status.FAIL, "Screenshot saved to ./screenshots/");
                }
            }
        } catch (IOException e) {
            tlTest.get().log(Status.WARNING, "Screenshot capture failed: " + e.getMessage());
        }

        System.out.println("[LISTENER] Test FAILED: " + methodName);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        String methodName = result.getMethod().getMethodName();
        tlTest.get().log(Status.SKIP, methodName + " — SKIPPED");
        System.out.println("[LISTENER] Test SKIPPED: " + methodName);
    }
}
