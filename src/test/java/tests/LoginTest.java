package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

public class LoginTest extends BaseTest {

    private LoginPage loginPage;
    private HomePage homePage;

    @BeforeMethod
    public void initPages() {
        loginPage = new LoginPage();
        homePage = new HomePage();
    }

    @Test(description = "Verify that a user can log in with valid credentials.")
    public void testSuccessfulLogin() {
        log.info("Executing test: testSuccessfulLogin");
        test.get().info("Attempting to log in with valid credentials.");
        
        loginPage.login("validUser", "validPassword");
        
        Assert.assertTrue(homePage.isWelcomeMessageDisplayed(), "Welcome message was not displayed after login.");
        test.get().pass("Successfully verified welcome message after login.");
    }

    @Test(description = "Verify that an error message is shown for invalid credentials.")
    public void testFailedLogin() {
        log.info("Executing test: testFailedLogin");
        test.get().info("Attempting to log in with invalid credentials.");

        loginPage.login("invalidUser", "invalidPassword");

        Assert.assertTrue(loginPage.isErrorMessageDisplayed(), "Error message was not displayed for invalid login.");
        test.get().pass("Successfully verified error message for failed login.");
    }
} 