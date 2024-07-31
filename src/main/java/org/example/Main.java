package org.example;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Set;

public class Main {
    public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver", "D:\\Junaid Gazi\\chromedriver-win64\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();

// Set up WebDriver and WebDriverWait
//        System.setProperty("webdriver.chrome.driver", "/path/to/chromedriver");
//        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));

        try {
            // Navigate to Outlook login page
            driver.get("https://outlook.live.com/owa/");

            // Wait for the Sign in link to be visible and click it
            WebElement signInLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Sign in")));
            signInLink.click();

            // Wait for the new tab to load and switch to it
            wait.until(ExpectedConditions.numberOfWindowsToBe(2));  // Assuming there are only two windows/tabs
            Set<String> allWindows = driver.getWindowHandles();
            String mainWindowHandle = driver.getWindowHandle();
            for (String windowHandle : allWindows) {
                if (!windowHandle.equals(mainWindowHandle)) {
                    driver.switchTo().window(windowHandle);
                    break;
                }
            }

            // Wait for the email field to be visible and interact with it
            WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#i0116")));
            emailField.sendKeys("sahil.sharma.chandan@outlook.com");
            driver.findElement(By.id("idSIButton9")).click();

            // Wait for the password field to be visible and interact with it
            WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#i0118")));
            passwordField.sendKeys("Ghazi133@");
            driver.findElement(By.id("idSIButton9")).click();

            // Wait for the "Stay signed in?" page and click "Yes"
            WebElement staySignedInButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"acceptButton\"]")));
            staySignedInButton.click();

            WebElement okButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"id__11\"]")));
            okButton.click();



            // Wait for the inbox to be visible
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("._1p4f5b"))); // Adjust the selector as needed

            // Navigate to the most recent email
            WebElement recentEmail = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".._2x2wq"))); // Adjust the selector as needed
            recentEmail.click();

            // Wait for the email body to load
            WebElement emailBody = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".._2vffq"))); // Adjust the selector as needed

            // Extract the OTP from the email content
            String emailContent = emailBody.getText();
            String otp = extractOTP(emailContent);  // Define this method to extract OTP

            // Close the email tab and switch back to the main tab
            driver.close();
            driver.switchTo().window(mainWindowHandle);

            // Now that we have the OTP, use it in your application
            WebElement otpField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("otp-input-field-id"))); // Use the actual locator
            otpField.sendKeys(otp);

            // Click the submit button
            WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("submit-button-id"))); // Use the actual locator
            submitButton.click();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to extract 6-digit OTP from email content
    private static String extractOTP(String content) {
        Pattern pattern = Pattern.compile("\\b\\d{6}\\b");
        Matcher matcher = pattern.matcher(content);
        if (matcher.find()) {
            return matcher.group();
        } else {
            throw new RuntimeException("OTP not found in email.");
        }
    }
}
