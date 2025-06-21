package utilities;

import drivers.AppiumDriverManager;
import io.appium.java_client.AppiumDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DriverUtils {

    private static final Logger log = LogManager.getLogger(DriverUtils.class);

    private AppiumDriver getDriver() {
        return AppiumDriverManager.getDriver();
    }

    public WebElement waitForElementToBeVisible(By locator, int timeoutInSeconds) {
        try {
            log.info("Waiting for element to be visible: {}. Timeout: {}s", locator, timeoutInSeconds);
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(timeoutInSeconds));
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            log.info("Element is visible: {}", locator);
            return element;
        } catch (TimeoutException e) {
            log.error("Element was not visible within the timeout period: {}", locator, e);
            throw e;
        }
    }

    public WebElement waitForElementToBeClickable(By locator, int timeoutInSeconds) {
        try {
            log.info("Waiting for element to be clickable: {}. Timeout: {}s", locator, timeoutInSeconds);
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(timeoutInSeconds));
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
            log.info("Element is clickable: {}", locator);
            return element;
        } catch (TimeoutException e) {
            log.error("Element was not clickable within the timeout period: {}", locator, e);
            throw e;
        }
    }

    public boolean waitForElementToDisappear(By locator, int timeoutInSeconds) {
        try {
            log.info("Waiting for element to disappear: {}. Timeout: {}s", locator, timeoutInSeconds);
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(timeoutInSeconds));
            boolean disappeared = wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
            log.info("Element disappeared successfully: {}", locator);
            return disappeared;
        } catch (TimeoutException e) {
            log.error("Element did not disappear within the timeout period: {}", locator, e);
            throw e;
        }
    }
} 