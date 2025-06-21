package utilities;

import drivers.AppiumDriverManager;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AppiumUtils {

    private static final Logger log = LogManager.getLogger(AppiumUtils.class);
    private final DriverUtils driverUtils = new DriverUtils();
    private static final int DEFAULT_WAIT = 10; // Default wait time in seconds

    public void click(By locator) {
        click(locator, DEFAULT_WAIT);
    }

    public void click(By locator, int waitTimeInSeconds) {
        log.info("Attempting to click on element: {}", locator);
        WebElement element = driverUtils.waitForElementToBeClickable(locator, waitTimeInSeconds);
        element.click();
        log.info("Successfully clicked on element: {}", locator);
    }

    public void sendKeys(By locator, String text) {
        sendKeys(locator, text, DEFAULT_WAIT);
    }

    public void sendKeys(By locator, String text, int waitTimeInSeconds) {
        log.info("Attempting to send keys '{}' to element: {}", text, locator);
        WebElement element = driverUtils.waitForElementToBeVisible(locator, waitTimeInSeconds);
        element.clear();
        element.sendKeys(text);
        log.info("Successfully sent keys '{}' to element: {}", text, locator);
    }

    public String getText(By locator) {
        return getText(locator, DEFAULT_WAIT);
    }

    public String getText(By locator, int waitTimeInSeconds) {
        log.info("Attempting to get text from element: {}", locator);
        WebElement element = driverUtils.waitForElementToBeVisible(locator, waitTimeInSeconds);
        String text = element.getText();
        log.info("Retrieved text '{}' from element: {}", text, locator);
        return text;
    }
    
    public boolean isDisplayed(By locator) {
        return isDisplayed(locator, DEFAULT_WAIT);
    }

    public boolean isDisplayed(By locator, int waitTimeInSeconds) {
        log.info("Checking if element is displayed: {}", locator);
        try {
            driverUtils.waitForElementToBeVisible(locator, waitTimeInSeconds);
            log.info("Element is displayed: {}", locator);
            return true;
        } catch (Exception e) {
            log.warn("Element is not displayed: {}", locator);
            return false;
        }
    }

    public String takeScreenshot(String testName) {
        String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
        File sourceFile = AppiumDriverManager.getDriver().getScreenshotAs(OutputType.FILE);
        String screenshotPath = System.getProperty("user.dir") + "/target/screenshots/" + testName + "_" + timestamp + ".png";
        try {
            FileUtils.copyFile(sourceFile, new File(screenshotPath));
            log.info("Screenshot taken and saved to: {}", screenshotPath);
        } catch (IOException e) {
            log.error("Failed to save screenshot.", e);
        }
        return screenshotPath;
    }
} 