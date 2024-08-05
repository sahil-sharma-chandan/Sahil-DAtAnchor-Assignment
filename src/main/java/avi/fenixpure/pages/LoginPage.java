package avi.fenixpure.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void enterEmail(String email) {
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='email']")));
        emailField.sendKeys(email);
    }

    public void submitLogin() {
        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("email-btn")));
        submitButton.click();
    }

    public void sendOtp() {
        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='auth0-label-submit']")));
        submitButton.click();
    }

    public void nevigateToMail(String email, String password) {
        // Get the main window handle
        String mainWindowHandle = driver.getWindowHandle();

        // Open a new window (new tab)
        ((JavascriptExecutor) driver).executeScript("window.open()");

        // Wait for the new tab to be available and switch to it
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));
        String newTabHandle = driver.getWindowHandles().stream().filter(handle -> !handle.equals(mainWindowHandle)).findFirst().orElseThrow(() -> new RuntimeException("New tab handle not found"));

        driver.switchTo().window(newTabHandle);

        // Navigate to Outlook login page
        driver.get("https://outlook.live.com/mail/0/?nlp=1&cobrandid=ab0455a0-8d03-46b9-b18b-df2f57b9e44c&deeplink=owa%2F");

        // Wait for the email field to be visible and interact with it
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#i0116")));
        emailField.sendKeys(email); // Replace with actual email
        driver.findElement(By.id("idSIButton9")).click(); // Click the Next button

        // Wait for the password field to be visible and interact with it
        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("i0118")));
        passwordField.sendKeys(password); // Replace with actual password
        driver.findElement(By.id("idSIButton9")).click(); // Click the Sign in button
        // Wait for the next element to be visible after sign in
        WebElement acceptButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("acceptButton")));
        acceptButton.click(); // Click the accept button
    }

    public void fetchOTP() {
        String emailSubject = "Your Anchor account One Time Passcode is"; // Adjust as needed
        List<WebElement> emails = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//span[contains(text(), '" + emailSubject + "')]")));
        if (!emails.isEmpty()) {
            emails.get(0).click();
            // Wait for the email content to load
            WebElement emailBody = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='main']")));
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            // Extract the email body text
            String emailText = emailBody.getText();
            System.out.println(emailText);
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            // Use regex to find the OTP
            Pattern otpPattern = Pattern.compile("\\b\\d{6}\\b"); // Assuming OTP is a 6-digit number
            Matcher matcher = otpPattern.matcher(emailText);

            if (matcher.find()) {
                String otp = matcher.group();
                System.out.println("OTP: " + otp);
                driver.close();
                // Switch back to the original tab and enter the OTP
                driver.switchTo().window(driver.getWindowHandles().stream().findFirst().orElseThrow(() -> new RuntimeException("Original window handle not found")));
                WebElement otpInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"1-vcode\"]"))); // Adjust as needed
                otpInput.sendKeys(otp);
                otpInput.sendKeys(Keys.RETURN);
            } else {
                System.out.println("OTP not found!");
            }
        }
    }

    public boolean verifyLogin() {
        try {
            WebElement userProfileIcon = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-info-email")));
            System.out.println("User LogedIn Successfully...");
            return userProfileIcon.isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    public void clickActionTab() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        WebElement actionTab = driver.findElement(By.id("radix-:r19:"));
        actionTab.click();
    }

    public void openInNewTab() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            // Click on the action tab to open a new tab
            WebElement actionTab = wait.until(ExpectedConditions.elementToBeClickable(By.id("fp-sharedlink-table-body-1-1_actions-open")));
            actionTab.click();

            // Wait until a new tab/window is opened
            wait.until(ExpectedConditions.numberOfWindowsToBe(2));

            // Switch to the new tab
            Set<String> windowHandles = driver.getWindowHandles();
            ArrayList<String> windowList = new ArrayList<>(windowHandles);
            driver.switchTo().window(windowList.get(1));

            // Define the expected URL
            String expectedUrl = "https://fenixshare.anchormydata.com/fenixpyre/v/Document.docx";

            // Wait until the URL of the new tab matches the expected URL
            wait.until(ExpectedConditions.urlToBe(expectedUrl));

            // Confirm the URL
            String actualUrl = driver.getCurrentUrl();
            if (expectedUrl.equals(actualUrl)) {
                System.out.println("New tab opened successfully with the correct URL.");
            } else {
                System.out.println("New tab did not open with the correct URL. Actual URL: " + actualUrl);
            }
            driver.close();

            // Switch back to the original tab
            driver.switchTo().window(windowList.get(0));

        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }

    public void openInNewTabToPreview() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            this.clickActionTab();
            // Click on the action tab to open the new tab
            WebElement actionPreTab = wait.until(ExpectedConditions.elementToBeClickable(By.id("fp-sharedlink-table-body-1-1_actions-preview")));
            actionPreTab.click();

            // Wait until a new window/tab is opened
            wait.until(ExpectedConditions.numberOfWindowsToBe(2));

            // Get all window handles and switch to the new tab
            Set<String> windowHandles = driver.getWindowHandles();
            ArrayList<String> windowList = new ArrayList<>(windowHandles);
            driver.switchTo().window(windowList.get(1));

            // Wait until the URL matches the expected URL
            String expectedUrl = "https://fenixshare.anchormydata.com/fenixpyre/v/Document.docx";
            wait.until(ExpectedConditions.urlToBe(expectedUrl));

            // Confirm the URL
            String actualUrl = driver.getCurrentUrl();
            if (expectedUrl.equals(actualUrl)) {
                System.out.println("New tab preview opened successfully with the correct URL.");
            } else {
                System.out.println("New tab preview did not open with the correct URL. Actual URL: " + actualUrl);
            }
            driver.close();

            // Switch back to the original tab
            driver.switchTo().window(windowList.get(0));

        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }

    public void searchMethod() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("menu-/home")));
        searchButton.click();
    }

    public void searchFile(String searchField) {
        WebElement searchInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("fp-home-recentfiles-search-bar")));
        searchInput.sendKeys(searchField);
        // Verify if the file is present in the search results
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("fp-home-recentfiles-recenttable-body-0-0_name")));
            System.out.println("File is present.");
        } catch (org.openqa.selenium.NoSuchElementException e) {
            System.out.println("File is not present.");
        }
    }

    public void captureScreenshot(String methodName) {
        try {
            TakesScreenshot screenshotTaker = (TakesScreenshot) driver;
            File screenshot = screenshotTaker.getScreenshotAs(OutputType.FILE);
            String screenshotPath = "screenshots/" + methodName + "_" + System.currentTimeMillis() + ".png";
            Files.createDirectories(Paths.get("screenshots"));
            Files.copy(screenshot.toPath(), Paths.get(screenshotPath));
            System.out.println("Screenshot taken and saved to " + screenshotPath);
        } catch (IOException e) {
            System.err.println("Failed to capture screenshot: " + e.getMessage());
        }
    }
}