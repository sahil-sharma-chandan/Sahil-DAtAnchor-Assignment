package avi.fenixpure.pages;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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
        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='email-btn']")));
        submitButton.click();
    }

    public void sendOtp() {
        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='auth0-label-submit']")));
        submitButton.click();
    }

    public void nevigateToMail(String email, String password) {
        //logic to fetch OTP from email
        // Get the main window handle
        String mainWindowHandle = driver.getWindowHandle();

        // Open a new window (new tab)
        ((JavascriptExecutor) driver).executeScript("window.open()");

        // Wait for the new tab to be available and switch to it
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));
        String newTabHandle = driver.getWindowHandles().stream()
                .filter(handle -> !handle.equals(mainWindowHandle))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("New tab handle not found"));

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
    public void fetchOTP(){
        // Click the element to load emails

        WebElement folderPane = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"folderPaneDroppableContainer\"]/div/div[1]/div[2]/div/div[1]/div/span[1]")));
        folderPane.click();

        String emailSubject = "Your Anchor account One Time Passcode is"; // Adjust as needed

        List<WebElement> emails = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//span[contains(text(), '" + emailSubject + "')]")));

        if (!emails.isEmpty()) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            emails.get(0).click();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            // Wait for the email content to load
            WebElement emailBody = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='main']")));
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            // Extract the email body text
            String emailText = emailBody.getText();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            // Use regex to find the OTP
            Pattern otpPattern = Pattern.compile("\\b\\d{6}\\b"); // Assuming OTP is a 6-digit number

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            Matcher matcher = otpPattern.matcher(emailText);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            if (matcher.find()) {
                String otp = matcher.group();
                System.out.println("OTP: " + otp);

                // Switch back to the original tab and enter the OTP
                driver.close();

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
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        WebElement actionTab = driver.findElement(By.xpath("//*[@id=\"radix-:r18:\"]"));
        actionTab.click();
    }

    public void openInNewTab() {
        try {
//            this.clickActionTab();
            WebElement actionTab = driver.findElement(By.id("fp-sharedlink-table-body-1-1_actions-open"));
            actionTab.click();

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.numberOfWindowsToBe(2));

            // Get all window handles
            Set<String> windowHandles = driver.getWindowHandles();
            ArrayList<String> windowList = new ArrayList<>(windowHandles);

            // Switch to the new tab
            driver.switchTo().window(windowList.get(1));

            // Wait until the URL matches the expected URL
            String expectedUrl = "https://fenixshare.anchormydata.com/fenixpyre/v/Document.docx";
            wait.until(ExpectedConditions.urlToBe(expectedUrl));

            // Confirm the URL
            String actualUrl = driver.getCurrentUrl();
            if (expectedUrl.equals(actualUrl)) {
                System.out.println("New tab Edit opened successfully with the correct URL.");
            } else {
                System.out.println("New tab Edit did not open with the correct URL. Actual URL: " + actualUrl);
            }
            driver.switchTo().window(windowList.get(0));

        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    public void openInNewTabToPreview(){
        try {
            this.clickActionTab();
            WebElement actionTab = driver.findElement(By.id("fp-sharedlink-table-body-1-1_actions-preview"));
            actionTab.click();

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.numberOfWindowsToBe(2));

            // Get all window handles
            Set<String> windowHandles = driver.getWindowHandles();
            ArrayList<String> windowList = new ArrayList<>(windowHandles);

            // Switch to the new tab
            driver.switchTo().window(windowList.get(2));

            // Wait until the URL matches the expected URL
            String expectedUrl = "https://fenixshare.anchormydata.com/fenixpyre/v/Document.docx";
            wait.until(ExpectedConditions.urlToBe(expectedUrl));

            // Confirm the URL
            String actualUrl = driver.getCurrentUrl();
            if (expectedUrl.equals(actualUrl)) {
                System.out.println("New tab PreView opened successfully with the correct URL.");
            } else {
                System.out.println("New tab PreView did not open with the correct URL. Actual URL: " + actualUrl);
            }
            driver.switchTo().window(windowList.get(0));
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }


    public void searchMethod() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='menu-/home']")));
        searchButton.click();
    }

    public void searchFile(String searchField) {
        WebElement searchInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"fp-home-recentfiles-search-bar\"]")));
        searchInput.sendKeys(searchField);
        // Verify if the file is present in the search results
        try {
            WebElement fileElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"fp-home-recentfiles-recenttable-body-0-0_name\"]/div/button/span[2]")));
            System.out.println("File is present.");
        } catch (org.openqa.selenium.NoSuchElementException e) {
            System.out.println("File is not present.");
        }
    }
}





