package pages;

import utilities.AppiumUtils;

public class BasePage {

    protected AppiumUtils appiumUtils;

    public BasePage() {
        this.appiumUtils = new AppiumUtils();
    }
} 