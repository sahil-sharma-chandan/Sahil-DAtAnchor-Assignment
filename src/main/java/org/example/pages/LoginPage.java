package org.example.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Set;

public class LoginPage {
    private final WebDriverWait wait;

    private WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void enterEmail(String email) {
        WebElement emailField = wait
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='email']")));
        emailField.sendKeys(email);
    }

    public void fetchOTP() {
        // logic to fetch OTP from email

        String mainWindowHandle = driver.getWindowHandle();

        ((JavascriptExecutor) driver).executeScript("window.open()");
        Set<String> allWindows = driver.getWindowHandles();
        for (String windowHandle : allWindows) {
            if (!windowHandle.equals(mainWindowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
        driver.get("https://accounts.google.com/v3/signin/identifier?continue=https%3A%2F%2Faccounts.google.com%2Fb%2F0%2FAddMailService&followup=https%3A%2F%2Faccounts.google.com%2Fb%2F0%2FAddMailService&ifkv=AdF4I77rjMc6G9qfH_704Tv9gxTvvCQD95LQ2xSv6Og62LMlAcGS30NrBig9Edqgs5i7gFDJVqK71A&passive=1209600&flowName=GlifWebSignIn&flowEntry=ServiceLogin&dsh=S1459821927%3A1722324302343037&ddm=0");
        WebElement emailInput=driver.findElement(By.xpath("//input[@id=\"identifierId\"]"));
        emailInput.sendKeys("aknikhil.k@gmail.com");
        WebElement nextButton = driver.findElement(By.xpath("//*[@id=\"identifierNext\"]"));
        nextButton.click();

        WebElement passwordInput=driver.findElement(By.xpath("//*[@id=\"password\"]"));
        passwordInput.sendKeys("Ghazi133@");

        WebElement nextButton1 = driver.findElement(By.xpath("//*[@id=\"passwordNext\"]"));
        nextButton1.click();


        // Get all window handles
        Set<String> windowHandles = driver.getWindowHandles();
        ArrayList<String> windowList = new ArrayList<>(windowHandles);

        // Switch to the new tab
        driver.switchTo().window(windowList.get(1));

    }

    public void submitLogin() {
        WebElement submitButton = wait
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='email-btn']")));
        submitButton.click();
    }

    public void sendOtp() {
        WebElement submitButton = wait
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='auth0-label-submit']")));
        submitButton.click();
    }

    public boolean verifyLogin() {
        try {
            Thread.sleep(15000); // Consider replacing this with a more efficient wait
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // Click the submit button
        WebElement submitButton = wait
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='auth0-label-submit']")));
        submitButton.click();

        try {
            Thread.sleep(5000); // Consider replacing this with a more efficient wait
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // Wait for a known element on the logged-in page
        try {
            WebElement userProfileIcon = wait
                    .until(ExpectedConditions.visibilityOfElementLocated(By.id("user-info-email")));
            System.out.println("User Logedin Successfully...");
            return userProfileIcon.isDisplayed();
        } catch (TimeoutException e) {
            // If the element is not found within the timeout period
            return false;
        }
    }

    public void clickActionTab() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        WebElement actionTab = driver.findElement(By.xpath("//*[@id=\"radix-:r19:\"]"));
        actionTab.click();

    }

    public void openInNewTab() {

        WebElement actionTab = driver
                .findElement(By.xpath("//*[@id=\"fp-sharedlink-table-body-1-1_actions-open\"]/div/span"));
        actionTab.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));

        // Get all window handles
        Set<String> windowHandles = driver.getWindowHandles();
        ArrayList<String> windowList = new ArrayList<>(windowHandles);

        // Switch to the new tab
        driver.switchTo().window(windowList.get(1));

        // Verify the URL or title of the new tab
        String expectedUrl = "https://fenixshare.anchormydata.com/fenixpyre/v/Document.docx";

        String actualUrl = driver.getCurrentUrl();
        if (expectedUrl.equals(actualUrl)) {
            System.out.println("New tab opened successfully with the correct URL.");
        } else {
            System.out.println("New tab did not open with the correct URL. Actual URL: " + actualUrl);
        }

        // Switch back to the original tab
        driver.switchTo().window(windowList.get(0));

        this.clickActionTab();

        WebElement previewTab = driver
                .findElement(By.xpath("//*[@id=\"fp-sharedlink-table-body-1-1_actions-preview\"]/div/span"));
        previewTab.click();

        // Switch to the new tab
        driver.switchTo().window(windowList.get(1));

        // Verify the URL or title of the new tab
        String expectedPreUrl = "https://fenixshare.anchormydata.com/fenixpyre/v/Document.docx";

        String actualPreUrl = driver.getCurrentUrl();
        if (expectedPreUrl.equals(actualPreUrl)) {
            System.out.println("New tab opened successfully with the correct preview URL.");
        } else {
            System.out.println("New tab did not open with the correct URL. Actual Preview URL: " + actualUrl);
        }

        // Switch back to the original tab
        driver.switchTo().window(windowList.get(0));

    }

    public void searchMethod() {
        WebElement submitButton = wait
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id=\"menu-/home\"]")));
        submitButton.click();

    }

    public void searchFile(String searchfield) {
        WebElement searchField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"fp-home-recentfiles-search-bar\"]")));
        searchField.sendKeys(searchfield);

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // Verify if the file is present in the search results
        try {
            WebElement fileElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//*[@id=\"fp-home-recentfiles-recenttable-body-0-0_name\"]/div/button/span[2]")));
            System.out.println("File is present.");
        } catch (org.openqa.selenium.NoSuchElementException e) {
            System.out.println("File is not present.");
        }
    }

}