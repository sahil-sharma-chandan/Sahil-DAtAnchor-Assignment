# Project Title

## Description

This project is a Java-based Selenium WebDriver automation project designed to test the login functionality of a web application. It includes tests for logging in, OTP verification, and searching for a document.

## Prerequisites

Before running the project, ensure you have the following installed:

1. **Java Development Kit (JDK)**
    - Ensure you have JDK 8 or higher installed.
    - You can download it from [Oracle's JDK Downloads](https://www.oracle.com/java/technologies/javase-downloads.html) or [OpenJDK](https://openjdk.java.net/).

2. **Apache Maven** (Optional)
    - If your project uses Maven for dependency management, make sure Maven is installed.
    - You can download it from [Apache Maven](https://maven.apache.org/download.cgi).

3. **Selenium WebDriver**
    - The project uses Selenium WebDriver to interact with the web browser.

4. **Web Browser and Driver**
    - **Google Chrome** browser installed.
    - **ChromeDriver** binary that matches your version of Google Chrome.

## Setup

1. **Clone the Repository**

```sh
  git clone https://github.com/sahil-sharma-chandan/Sahil-DAtAnchor-Assignment.git
```
### Install Dependencies

If you are using Maven for dependency management, ensure that the dependencies are specified in the `pom.xml` file. Install them by running:

```sh
mvn install
```

### Install ChromeDriver

1. **Download ChromeDriver:**

    Obtain ChromeDriver from the ChromeDriver Downloads.
    Choose the version that matches your installed version of Google Chrome.
    Place ChromeDriver:

2. Extract the downloaded file and place chromedriver.exe in a directory of your choice (e.g., D:\Junaid Gazi\chromedriver-win64\).
    Update ChromeDriver Path in Code:

3. Open the LoginTest.java file.
    Locate the line that sets the path for ChromeDriver and update it to point to the location of chromedriver.exe:

## Now its ready to run