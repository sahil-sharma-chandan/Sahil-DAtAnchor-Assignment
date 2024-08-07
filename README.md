# Project Setup and Running Instructions

## Overview
This project is an automated testing framework for a web application using Java, Cucumber, and Extent Reports. It follows the Page Object Model (POM) design pattern for organizing test code, and includes comprehensive logging and reporting to help in understanding and analyzing test results.

## Prerequisites

Before running the project, ensure you have the following installed:

1. **Java Development Kit (JDK)**
   - Ensure you have JDK 17 or higher installed.
   - You can download it from [Oracle's JDK Downloads](https://www.oracle.com/java/technologies/javase-downloads.html) or [OpenJDK](https://openjdk.java.net/).

2. **Apache Maven**
   - This project uses Maven for dependency management, make sure Maven is installed.
   - You can download it from [Apache Maven](https://maven.apache.org/download.cgi).

3. **Selenium WebDriver**
   - The project uses Selenium WebDriver to interact with the web browser. Ensure it is correctly set up in your project.

4. **Web Browser and Driver**
   - **Google Chrome** browser installed Version 127.0.6533.89.
   - **ChromeDriver** binary that matches Version 127.0.6533.89 of Google Chrome. Ensure it's properly installed and set up.
   - _Note:_ Make sure Google Chrome and ChromeDriver both are same version.

5. **Cucumber**
   - Ensure Cucumber is installed if your project uses it for Behavior-Driven Development (BDD).
   - Need to install Cucumber Plugin. Go to the File menu (or IntelliJ IDEA menu on macOS) at the top-left corner of the window.
     Select Settings (or Preferences on macOS). In the Settings/Preferences window, find and click on Plugins in the left-hand menu. In the Plugins section, you'll see a search bar at the top of the window.
     Type Cucumber into the search bar. Click on the Install button next to the Cucumber plugin.

6. **Outlook Email**
   - Use an Outlook email account instead of Gmail to avoid potential security issues.

7. **Install IntelliJ IDEA**
      - For Windows/macOS/Linux: [Go to the IntelliJ IDEA download page](https://www.jetbrains.com/idea/download/?section=windows).
   



## Setup

1. **Clone the Repository**

   Open your terminal or command prompt and run:

   ```sh
   git clone https://github.com/sahil-sharma-chandan/Sahil-DAtAnchor-Assignment.git
   ```

2. **Open IntelliJ IDEA**
    - Locate the pom.xml file in the project directory (Sahil-DAtAnchor-Assignment).
    - Right-click on pom.xml and select "Open As Project".
    - Now wait for Build Your Project. It will Autometically build project.
   
3. **Update Configuration**
    - Navigate to the file located at Sahil-DAtAnchor-Assignment/src/main/java/avi/fenixpure/component/Constant.java.
    - Update the following fields:
      - chromeDriverPath: Set this to the path of the ChromeDriver binary you downloaded.
      - email: Update this with your Outlook email address.
      - password: Update this with the password for your Outlook email account.
4. Running the Application
    - Open IntelliJ IDEA and navigate to Sahil-DAtAnchor-Assignment/src/main/resources/features/login.feature.
    - Right-click on login.feature and select "Run".

Application should now be running successfully. If you encounter any issues, make sure all prerequisites are correctly installed and configured.


## Project Structure

1. Component: src/main/java/avi/fenixpure/component/Constant
   - _Constant Class:_ This class centralizes configuration values for Application, improving maintainability and readability. It is important to avoid storing sensitive information directly in this class.
2. Pages: src/main/java/avi/fenixpure/pages
   - _LoginPage Class:_ Represents the login page of the application and automates interactions like logging in, handling OTPs, navigating tabs, and performing searches. It uses the Page Object Model to encapsulate these actions, promoting code reuse and ease of maintenance.
3. Reporting: src/main/java/avi/fenixpure/reporting
   - _ExtentReportManager Class:_ Manages Extent Reports, sets up the reporting environment, creates test entries, and flushes results. It provides detailed and visually rich reports to help analyze test outcomes.
4. Steps: src/main/java/avi/fenixpure/steps
   - _LoginSteps Class:_ Implements the steps defined in the Cucumber feature files for login scenarios. It integrates with reporting and logging frameworks, ensuring that resources are properly managed and test results are accurately reported.
5. Tests: src/main/java/avi/fenixpure/tests
   - _TestRunner Class:_ Integrates Cucumber with JUnit, configuring how Cucumber tests are executed. It specifies where to find feature files and step definitions and manages test result reporting.
6. Login Feature: src/main/resources/features/login.feature
   - Describes the BDD scenario for testing user login and document search functionalities. It aligns with the methods in the LoginSteps class, ensuring that application behavior is tested from an end-user perspective.
7. Log4j2 Configuration: src/main/resources/log4j2.xml
   - Configures logging with the following features:
      - Log Destinations: Console and file (test.log in the logs directory). 
      - Log Format: Includes timestamp, log level, logger name, and message. 
      - Log Level: Default is INFO, logging messages at INFO, WARN, and ERROR levels.

### Contact
For any questions or support, please contact [sahil.chandan94@gmail.com](sahil.chandan94@gmail.com).

