# Fenix Share Automation Test

# Introduction

This repository contains a set of automation tests for the Fenix Share application. The tests are written in Java using Selenium WebDriver and JUnit.


# Prerequisites
.Java 8 or higher installed on your system
.Chrome browser installed on your system
.ChromeDriver executable downloaded and added to your system's PATH
.Maven installed on your system (optional)

# Test Scenarios
The following test scenarios are included in this repository:

Login Test: Verifies that a user can successfully log in to the application
Search Test: Verifies that a user can search for a file in the application
Open in New Tab Test: Verifies that a user can open a file in a new tab

# Running the Tests
To run the tests, follow these steps:

Clone this repository to your local machine using git clone
Navigate to the project directory using cd
If you are using Maven, run the command mvn clean test to build and run the tests
If you are not using Maven, compile the Java code using javac and run the tests using java
The tests will launch the Chrome browser and execute the test scenarios

# Troubleshooting

Make sure that the ChromeDriver executable is in your system's PATH
Make sure that the Chrome browser is installed and configured correctly
If you encounter any issues, check the test logs for errors and exceptions