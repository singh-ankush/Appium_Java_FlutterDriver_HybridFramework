package config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class AppiumConfig {

    private static final Logger log = LogManager.getLogger(AppiumConfig.class);
    private static Properties props;

    static {
        props = new Properties();
        try {
            String propFileName = System.getProperty("user.dir") + "/src/main/resources/appium_config.properties";
            FileInputStream fis = new FileInputStream(propFileName);
            props.load(fis);
            log.info("Successfully loaded Appium config from: {}", propFileName);
        } catch (IOException e) {
            log.error("Failed to load Appium config properties.", e);
            throw new RuntimeException("Could not load appium_config.properties. Please check the file.", e);
        }
    }

    public static String getProperty(String key) {
        String value = props.getProperty(key);
        if (value == null) {
            log.warn("Property '{}' not found in config file.", key);
            return null;
        }
        return value;
    }

    public static String getProperty(String key, String defaultValue) {
        return props.getProperty(key, defaultValue);
    }

    public static int getIntProperty(String key, int defaultValue) {
        String value = props.getProperty(key);
        if (value != null) {
            try {
                return Integer.parseInt(value);
            } catch (NumberFormatException e) {
                log.warn("Could not parse property '{}' with value '{}' as an integer. Using default value '{}'.", key, value, defaultValue);
                return defaultValue;
            }
        }
        return defaultValue;
    }

    public static boolean getBooleanProperty(String key, boolean defaultValue) {
        String value = props.getProperty(key);
        if (value != null) {
            return Boolean.parseBoolean(value);
        }
        return defaultValue;
    }
} 