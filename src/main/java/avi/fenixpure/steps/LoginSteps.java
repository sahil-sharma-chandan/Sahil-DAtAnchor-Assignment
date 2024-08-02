package avi.fenixpure.steps;

import avi.fenixpure.pages.LoginPage;
import io.cucumber.java.After;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.Assert;


import java.time.Duration;

public class LoginSteps {

    private WebDriver driver;
    private LoginPage loginPage;
    private WebDriverWait wait;
    private final String chromeDriverPath = "D:\\Junaid Gazi\\chromedriver-win64\\chromedriver.exe";
    private final String baseUrl = "https://fenixshare.anchormydata.com/fenixpyre/s/669ff2910e5caf9f73cd28ea/QA%2520Assignment";
    private final String email = "sahil.sharma.chandan@outlook.com";
    private final String password = "Ghazi133@";

    @Given("the user navigates to the login page")
    public void the_user_navigates_to_the_login_page() {
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        driver = new ChromeDriver();
        driver.get(baseUrl);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        loginPage = new LoginPage(driver);
    }

    @When("the user logs in with email {string}")
    public void the_user_logs_in_with_email_and_password(String email) {
        loginPage.enterEmail(email);
        loginPage.submitLogin();
        loginPage.sendOtp();
    }

    @When("the user fetches the OTP and submits it")
    public void the_user_fetches_the_otp_and_submits_it() {
        loginPage.nevigateToMail(email, password);
        loginPage.fetchOTP();
    }

    @Then("the user should be logged in successfully")
    public void the_user_should_be_logged_in_successfully() {
        // Assert or check the login success
        boolean isLoggedIn = loginPage.verifyLogin();
        Assert.assertTrue("Login was not successful.", isLoggedIn);

    }

    @And("the user searches for {string}")
    public void the_user_searches_for(String filename) {
        loginPage.clickActionTab();
        loginPage.openInNewTab();
        loginPage.openInNewTabToPreview();
        loginPage.searchMethod();
        loginPage.searchFile(filename);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}