Feature: Login and Search File

  Scenario: Successful login and search for a document
    Given user navigates to the login page
    When user logs in with email
    And user fetches the OTP and submits it
    Then user should be logged in successfully
    And user searches for "Document.docx"
    Then user should see the file "Document.docx" in the search results