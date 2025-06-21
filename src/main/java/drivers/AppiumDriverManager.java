package drivers;

import config.AppiumConfig;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.time.Duration;

public class AppiumDriverManager {

    private static final Logger log = LogManager.getLogger(AppiumDriverManager.class);
    private static final ThreadLocal<AppiumDriver> driverThread = new ThreadLocal<>();

    public static AppiumDriver getDriver() {
        return driverThread.get();
    }

    public static void initializeDriver() {
        if (getDriver() == null) {
            try {
                log.info("Initializing Appium driver...");
                DesiredCapabilities caps = new DesiredCapabilities();
                caps.setCapability("platformName", AppiumConfig.getProperty("platform.name"));
                caps.setCapability("platformVersion", AppiumConfig.getProperty("platform.version"));
                caps.setCapability("deviceName", AppiumConfig.getProperty("device.name"));
                caps.setCapability("automationName", AppiumConfig.getProperty("automation.name"));
                caps.setCapability("app", System.getProperty("user.dir") + AppiumConfig.getProperty("app.path"));

                URL url = new URL(AppiumConfig.getProperty("appium.server.url"));
                AppiumDriver driver;

                if (AppiumConfig.getProperty("platform.name").equalsIgnoreCase("Android")) {
                    driver = new AndroidDriver(url, caps);
                } else if (AppiumConfig.getProperty("platform.name").equalsIgnoreCase("iOS")) {
                    driver = new IOSDriver(url, caps);
                } else {
                    throw new IllegalArgumentException("Unsupported platform specified in config: " + AppiumConfig.getProperty("platform.name"));
                }
                
                int implicitWait = AppiumConfig.getIntProperty("implicit.wait.timeout", 10);
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait));
                
                driverThread.set(driver);
                log.info("Driver initialized successfully. Session ID: {}", driver.getSessionId());

            } catch (Exception e) {
                log.fatal("Driver initialization failed!", e);
                throw new RuntimeException("Failed to initialize Appium driver. See logs for details.", e);
            }
        }
    }

    public static void quitDriver() {
        AppiumDriver driver = getDriver();
        if (driver != null) {
            log.info("Quitting driver session: {}", driver.getSessionId());
            driver.quit();
            driverThread.remove();
            log.info("Driver session quit successfully.");
        }
    }
} 