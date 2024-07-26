package org.example.tests;


import junit.framework.Assert;
import org.example.pages.LoginPage;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginTest {
    @Test
    public void testLogin() {
        System.setProperty("webdriver.chrome.driver", "C:\\Junaid Gazi\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://fenixshare.anchormydata.com/fenixpyre/s/669ff2910e5caf9f73cd28ea/QA%2520Assignment");
        driver.manage().window().maximize();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterEmail("sahil040272@gmail.com");
        loginPage.submitLogin();
        loginPage.sendOtp();
        loginPage.fetchOTP();


        boolean isLoggedIn = loginPage.verifyLogin();
        Assert.assertTrue("Login was not successful.", isLoggedIn);



        loginPage.clickActionTab();
        loginPage.openInNewTab();
        loginPage.searchMethod();
        loginPage.searchFile("Document.docx");
    }
}