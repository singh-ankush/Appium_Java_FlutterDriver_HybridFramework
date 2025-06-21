package pages;

import org.openqa.selenium.By;

public class HomePage extends BasePage {

    // --- Element Locators ---
    private final By welcomeMessage = By.xpath("//*[@content-desc='welcome_message']");

    public HomePage() {
        super();
    }

    // --- Page Actions ---

    public boolean isWelcomeMessageDisplayed() {
        return appiumUtils.isDisplayed(welcomeMessage, 15); // Wait up to 15 seconds after login
    }

    public String getWelcomeMessageText() {
        return appiumUtils.getText(welcomeMessage, 15);
    }
} 