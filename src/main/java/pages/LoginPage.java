package pages;

import org.openqa.selenium.By;

public class LoginPage extends BasePage {

    // --- Element Locators ---
    private final By usernameField = By.xpath("//*[@content-desc='username_field']");
    private final By passwordField = By.xpath("//*[@content-desc='password_field']");
    private final By loginButton = By.xpath("//*[@content-desc='login_button']");
    private final By errorMessage = By.xpath("//*[@content-desc='error_message']");

    public LoginPage() {
        super();
    }

    // --- Page Actions ---

    public void enterUsername(String username) {
        appiumUtils.sendKeys(usernameField, username);
    }

    public void enterPassword(String password) {
        appiumUtils.sendKeys(passwordField, password);
    }

    public void clickLoginButton() {
        appiumUtils.click(loginButton);
    }

    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
    }

    public boolean isErrorMessageDisplayed() {
        return appiumUtils.isDisplayed(errorMessage, 5); // 5-second wait
    }

    public String getErrorMessageText() {
        return appiumUtils.getText(errorMessage, 5); // 5-second wait
    }
} 