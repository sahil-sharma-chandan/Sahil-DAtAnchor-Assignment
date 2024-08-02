


Feature: Login and Search File

  Scenario: Successful login and search for a document
    Given the user navigates to the login page
    When the user logs in with email "Replace with Outlook Email"
    And the user fetches the OTP and submits it
    Then the user should be logged in successfully
    And the user searches for "Document.docx"