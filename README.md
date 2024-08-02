# Project Setup and Running Instructions

## Prerequisites

Before running the project, ensure you have the following installed:

1. **Java Development Kit (JDK)**
    - Ensure you have JDK 17 or higher installed.
    - You can download it from [Oracle's JDK Downloads](https://www.oracle.com/java/technologies/javase-downloads.html) or [OpenJDK](https://openjdk.java.net/).

2. **Apache Maven**
    - If your project uses Maven for dependency management, make sure Maven is installed.
    - You can download it from [Apache Maven](https://maven.apache.org/download.cgi).

3. **Selenium WebDriver**
    - The project uses Selenium WebDriver to interact with the web browser. Ensure it is correctly set up in your project.

4. **Web Browser and Driver**
    - **Google Chrome** browser installed.
    - **ChromeDriver** binary that matches your version of Google Chrome. Ensure it's properly installed and set up.

5. **Cucumber**
    - Ensure Cucumber is installed if your project uses it for Behavior-Driven Development (BDD).

6. **Outlook Email**
    - Use an Outlook email account instead of Gmail to avoid potential security issues.

## Setup

1. **Clone the Repository**

   Open your terminal or command prompt and run:

   ```sh
   git clone https://github.com/sahil-sharma-chandan/Sahil-DAtAnchor-Assignment.git
   ```


2. **Open the Project in IntelliJ IDEA**

## Open IntelliJ IDEA.
    Locate the pom.xml file in the project directory (Sahil-DAtAnchor-Assignment).
    Right-click on pom.xml and select "Open As Project".

It will Autometically Build project

## Update Configuration

    Navigate to the file located at Sahil-DAtAnchor-Assignment/src/main/java/avi/fenixpure/component/Constant.java.
# Update the following fields:
    chromeDriverPath: Set this to the path of the ChromeDriver binary you downloaded.
    email: Update this with your Outlook email address.
    password: Update this with the password for your Outlook email account.
## Running the Application
    Open IntelliJ IDEA and navigate to Sahil-DAtAnchor-Assignment/src/main/resources/features/login.feature.
    Right-click on login.feature and select "Run".

Your application should now be running successfully. If you encounter any issues, make sure all prerequisites are correctly installed and configured.