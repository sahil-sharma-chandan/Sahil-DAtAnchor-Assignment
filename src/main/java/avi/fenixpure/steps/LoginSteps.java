package avi.fenixpure.steps;

import avi.fenixpure.component.Constant;
import avi.fenixpure.pages.LoginPage;
import avi.fenixpure.reporting.ExtentReportManager;
import io.cucumber.java.After;
import io.cucumber.java.en.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.Assert;

import java.time.Duration;

public class LoginSteps {

    private static final Logger logger = LogManager.getLogger(LoginSteps.class);
    private WebDriver driver;
    private LoginPage loginPage;
    private WebDriverWait wait;

    @Given("the user navigates to the login page")
    public void the_user_navigates_to_the_login_page() {
        try {
            logger.info("Navigating to login page...");
            System.setProperty("webdriver.chrome.driver", Constant.chromeDriverPath);
            driver = new ChromeDriver();
            driver.get(Constant.baseUrl);
            driver.manage().window().maximize();
            wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            loginPage = new LoginPage(driver);
            ExtentReportManager.createTest("Login Test");
        } catch (Exception e) {
            loginPage.captureScreenshot("the user navigates to the login page Failed");
            logger.error("navigates to the login page failed: " + e.getMessage());
            ExtentReportManager.getExtentReports().createTest("Search Test").fail(e.getMessage());
            throw e;
        }
    }

    @When("the user logs in with email {string}")
    public void the_user_logs_in_with_email_and_password(String email) {
        try {
            logger.info("Logging in with email: " + email);
            loginPage.enterEmail(email);
            loginPage.submitLogin();
            loginPage.sendOtp();
        } catch (Exception e) {
            loginPage.captureScreenshot("the user logs in with email Failed");
            logger.error("user logs in with email failed: " + e.getMessage());
            ExtentReportManager.getExtentReports().createTest("Search Test").fail(e.getMessage());
            throw e;
        }
    }

    @When("the user fetches the OTP and submits it")
    public void the_user_fetches_the_otp_and_submits_it() {
        try {
            logger.info("Fetching OTP...");
            loginPage.nevigateToMail(Constant.email, Constant.password);
            loginPage.fetchOTP();
        } catch (Exception e) {
            loginPage.captureScreenshot("Fetching OTP... Failed");
            logger.error("user fetches the OTP and submits it failed: " + e.getMessage());
            ExtentReportManager.getExtentReports().createTest("Search Test").fail(e.getMessage());
            throw e;
        }
    }

    @Then("the user should be logged in successfully")
    public void the_user_should_be_logged_in_successfully() {
        try {
            logger.info("Verifying login...");
            boolean isLoggedIn = loginPage.verifyLogin();
            Assert.assertTrue("Login was not successful.", isLoggedIn);
            ExtentReportManager.getExtentReports().createTest("Login Test").pass("Login successful");
        } catch (AssertionError e) {
            loginPage.captureScreenshot("loginVerificationFailure");
            logger.error("Login failed: " + e.getMessage());
            ExtentReportManager.getExtentReports().createTest("Login Test").fail(e.getMessage());
            throw e;
        }
    }

    @And("the user searches for {string}")
    public void the_user_searches_for(String filename) {
        try {
            logger.info("Searching for file: " + filename);
            loginPage.clickActionTab();
            loginPage.openInNewTab();
            loginPage.openInNewTabToPreview();
            loginPage.searchMethod();
            loginPage.searchFile(filename);
        } catch (Exception e) {
            loginPage.captureScreenshot("searchFailure");
            logger.error("Search failed: " + e.getMessage());
            ExtentReportManager.getExtentReports().createTest("Search Test").fail(e.getMessage());
            throw e;
        }
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        ExtentReportManager.flush();
    }
}