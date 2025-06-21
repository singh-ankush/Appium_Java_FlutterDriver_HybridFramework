package tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import config.AppiumConfig;
import drivers.AppiumDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utilities.AppiumUtils;

import java.lang.reflect.Method;

public abstract class BaseTest {

    protected static final Logger log = LogManager.getLogger(BaseTest.class);
    protected static ExtentReports extent;
    protected static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    protected AppiumUtils appiumUtils;

    @BeforeSuite
    public void setUpSuite() {
        ExtentSparkReporter spark = new ExtentSparkReporter("target/ExtentReport.html");
        spark.config().setTheme(Theme.STANDARD);
        spark.config().setDocumentTitle("Appium Test Report");
        spark.config().setReportName("Test Automation Results");
        extent = new ExtentReports();
        extent.attachReporter(spark);
        log.info("Extent Reports have been set up.");
        appiumUtils = new AppiumUtils();
    }

    @BeforeMethod
    public void setUp(Method method) {
        log.info("<<<<< Starting test: {} >>>>>", method.getName());
        AppiumDriverManager.initializeDriver();
        
        ExtentTest extentTest = extent.createTest(method.getName());
        test.set(extentTest);
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        log.info("<<<<< Finishing test: {} >>>>>", result.getMethod().getMethodName());
        
        boolean logPass = AppiumConfig.getBooleanProperty("reporting.log.pass", true);
        boolean logFail = AppiumConfig.getBooleanProperty("reporting.log.fail", true);
        boolean logSkip = AppiumConfig.getBooleanProperty("reporting.log.skip", true);
        boolean screenshotPass = AppiumConfig.getBooleanProperty("reporting.screenshot.pass", false);
        boolean screenshotFail = AppiumConfig.getBooleanProperty("reporting.screenshot.fail", true);

        if (result.getStatus() == ITestResult.SUCCESS) {
            if (logPass) {
                test.get().pass("Test passed successfully.");
            }
            if (screenshotPass) {
                test.get().addScreenCaptureFromPath(appiumUtils.takeScreenshot(result.getName()), "Passed Screenshot");
            }
        } else if (result.getStatus() == ITestResult.FAILURE) {
            if (logFail) {
                test.get().fail(result.getThrowable());
            }
            if (screenshotFail) {
                test.get().addScreenCaptureFromPath(appiumUtils.takeScreenshot(result.getName()), "Failure Screenshot");
            }
        } else if (result.getStatus() == ITestResult.SKIP) {
            if (logSkip) {
                test.get().skip("Test was skipped.");
            }
        }
        
        AppiumDriverManager.quitDriver();
        extent.flush();
    }
} 