package org.example.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginPage {
    private final WebDriverWait wait;

    private WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void enterEmail(String email) {
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='email']")));
        emailField.sendKeys(email);
    }

    public void fetchOTP(String email) {
        // logic to fetch OTP from email

        // Get the main window handle
        String mainWindowHandle = driver.getWindowHandle();
        // Get all window handles
        Set<String> windowHandles = driver.getWindowHandles();
        ArrayList<String> windowList = new ArrayList<>(windowHandles);

        // Open a new window (new tab)
        ((JavascriptExecutor) driver).executeScript("window.open()");
        Set<String> allWindows = driver.getWindowHandles();
        String newTabHandle = null;

        // Switch to the newly opened tab
        for (String windowHandle : allWindows) {
            if (!windowHandle.equals(mainWindowHandle)) {
                newTabHandle = windowHandle;
                driver.switchTo().window(newTabHandle);
                break;
            }
        }

        // Navigate to Outlook login page
        driver.get("https://outlook.live.com/mail/0/?nlp=1&cobrandid=ab0455a0-8d03-46b9-b18b-df2f57b9e44c&deeplink=owa%2F");

        // Create WebDriverWait instance

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


        // Wait for the new tab to load and switch to it
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));
        allWindows = driver.getWindowHandles();
        for (String windowHandle : allWindows) {
            if (!windowHandle.equals(mainWindowHandle) && !windowHandle.equals(newTabHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }


        // Wait for the email field to be visible and interact with it
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#i0116")));
        emailField.sendKeys(email);
        driver.findElement(By.id("idSIButton9")).click();


        // Wait for the email field to be visible and interact with it
        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"i0118\"]")));
        passwordField.sendKeys("Ghazi133@");
        driver.findElement(By.id("idSIButton9")).click();  // Click the Sign in button

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        driver.findElement(By.xpath("//*[@id=\"acceptButton\"]")).click();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        driver.findElement(By.xpath("//*[@id=\"folderPaneDroppableContainer\"]/div/div[1]/div[2]/div/div[1]/div/span[1]")).click();


        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


        // Wait for emails to load
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        // Find the email with the verification code
        String emailSubject = "Your Anchor account One Time Passcode is"; // Adjust as needed
        List<WebElement> emails = driver.findElements(By.xpath("//span[contains(text(), '" + emailSubject + "')]"));
        if (!emails.isEmpty()) {
            emails.get(0).click();
            // Wait for the email content to load
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            // Extract the email body
            WebElement emailBody = driver.findElement(By.xpath("//div[@role='main']"));
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Email Body: " + emailBody.getText());
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            String emailText = emailBody.getText();
            // Optionally, use regex or string operations to find the verification code in the email body
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

                // Optionally, you can use the OTP in the original tab
                // For example, switching to the original tab and entering the OTP
                driver.close();

                driver.switchTo().window(windowList.get(0));
                WebElement otpInput = driver.findElement(By.xpath("//*[@id=\"1-vcode\"]")); // Adjust as needed
                otpInput.sendKeys(otp);
                otpInput.sendKeys(Keys.RETURN);
            } else {
                System.out.println("OTP not found!");
            }


        }


    }

    public void submitLogin() {
        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='email-btn']")));
        submitButton.click();
    }

    public void sendOtp() {
        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='auth0-label-submit']")));
        submitButton.click();
    }

    public boolean verifyLogin() {
        try {
            Thread.sleep(15000); // Consider replacing this with a more efficient wait
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


        // Wait for a known element on the logged-in page
        try {
            WebElement userProfileIcon = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-info-email")));
            System.out.println("User Logedin Successfully...");
            return userProfileIcon.isDisplayed();
        } catch (TimeoutException e) {
            // If the element is not found within the timeout period
            return false;
        }
    }

    public void clickActionTab() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        WebElement actionTab = driver.findElement(By.xpath("//*[@id=\"radix-:r19:\"]"));
        actionTab.click();

    }

    public void openInNewTab() {

        WebElement actionTab = driver.findElement(By.xpath("//*[@id=\"fp-sharedlink-table-body-1-1_actions-open\"]/div/span"));
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

        WebElement previewTab = driver.findElement(By.xpath("//*[@id=\"fp-sharedlink-table-body-1-1_actions-preview\"]/div/span"));
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
        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id=\"menu-/home\"]")));
        submitButton.click();

    }

    public void searchFile(String searchfield) {
        WebElement searchField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"fp-home-recentfiles-search-bar\"]")));
        searchField.sendKeys(searchfield);

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // Verify if the file is present in the search results
        try {
            WebElement fileElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"fp-home-recentfiles-recenttable-body-0-0_name\"]/div/button/span[2]")));
            System.out.println("File is present.");
        } catch (org.openqa.selenium.NoSuchElementException e) {
            System.out.println("File is not present.");
        }
    }

}