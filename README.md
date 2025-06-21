# Appium_Java_Flutter_HybridFramework
 This is a Hybrid flutter framework built using Cursor AI which is open to use and needs to be checked and verified.

It utilizes a hybrid approach to automate Flutter based applications using the latest flutter-driver in Appium.

# Appium Java Hybrid Framework

This is a robust and scalable test automation framework for mobile applications, built with Appium, Java, and TestNG. It follows the Page Object Model (POM) design pattern and is specifically designed to be easy to use and maintain.

## 🚀 Core Features

- **Page Object Model (POM)**: For clean and maintainable test code.
- **TestNG**: As the testing framework for powerful assertions and test management.
- **Explicit and Implicit Waits**: Smart synchronization with parameterized waits to handle dynamic elements.
- **Centralized Configuration**: All framework settings are managed in a single properties file.
- **Detailed Logging**: Comprehensive logging with Log4j2 for easy debugging.
- **Extent Reports**: Beautiful, detailed HTML reports to visualize test results.
- **Flutter-Driver Support**: Configured to run tests on Flutter applications.

## 🏗️ Framework Structure

```
AppiumJavaHybridFramework/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── config/
│   │   │   │   └── AppiumConfig.java
│   │   │   ├── drivers/
│   │   │   │   └── AppiumDriverManager.java
│   │   │   ├── pages/
│   │   │   │   ├── BasePage.java
│   │   │   │   ├── LoginPage.java
│   │   │   │   └── HomePage.java
│   │   │   └── utilities/
│   │   │       ├── AppiumUtils.java
│   │   │       └── DriverUtils.java
│   │   └── resources/
│   │       ├── appium_config.properties
│   │       └── log4j2.xml
│   │
│   └── test/
│       └── java/
│           └── tests/
│               ├── BaseTest.java
│               └── LoginTest.java
│
├── .gitignore
├── pom.xml
├── README.md
└── testng.xml
```

## 📋 Prerequisites

- Java (JDK 11 or higher)
- Maven
- Appium Server
- An IDE (like IntelliJ IDEA or Eclipse)
- Android SDK (for Android testing) or Xcode (for iOS testing)

## 🛠️ How to Set Up

1.  **Clone the Repository**: 
    ```bash
    git clone <your-repo-url>
    ```
2.  **Install Dependencies**: Open the project in your IDE and let Maven download all the dependencies defined in `pom.xml`.
3.  **Configure the Framework**:
    -   Open `src/main/resources/appium_config.properties`.
    -   Update the `platform.name`, `platform.version`, and `device.name` to match your test device or emulator.
    -   For Flutter apps, keep `automation.name=Flutter`. For native Android, use `UiAutomator2`.
    -   Update `app.path` to the correct location of your test application (`.apk` or `.ipa` file).
4.  **Place Your App**: Put your application file (e.g., `app.apk`) in the `src/test/resources/apps/` directory (you may need to create it).

## 🚀 How to Run Tests

You can run tests using either your IDE or the command line with Maven.

### Using TestNG (in your IDE)
-   Right-click on the `testng.xml` file and select "Run '.../testng.xml'".

### Using Maven (in your terminal)
-   Navigate to the project's root directory.
-   Run the following command:
    ```bash
    mvn clean test
    ```

## 📊 Viewing Reports

After the tests have run, you can find the results in the `target/` directory:
-   **Extent Report**: Open `target/ExtentReport.html` in your browser.
-   **Logs**: Check `target/logs/app.log` for detailed execution logs.

## ✍️ How to Add a New Test

1.  **Create a New Page Object**:
    -   In the `pages` package, create a new Java class (e.g., `ProfilePage.java`).
    -   Make it extend `BasePage`.
    -   Define your element locators using `By`.
    -   Create methods for the actions a user can perform on that page (e.g., `clickEditProfileButton()`). Use `appiumUtils` for all interactions.

2.  **Create a New Test Class**:
    -   In the `tests` package, create a new Java class (e.g., `ProfileTest.java`).
    -   Make it extend `BaseTest`.
    -   Create an instance of your new page object.
    -   Write your test methods using the `@Test` annotation. Use methods from your page object to perform actions and TestNG's `Assert` class to verify the results.

3.  **Add to TestNG Suite**:
    -   Open `testng.xml`.
    -   Add your new test class to the suite:
        ```xml
        <classes>
            <class name="tests.LoginTest"/>
            <class name="tests.ProfileTest"/> 
        </classes>
        ```

That's it! You're ready to expand the framework with your own tests. 
