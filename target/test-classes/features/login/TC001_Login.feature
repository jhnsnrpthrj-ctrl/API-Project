@Login
Feature: OMR Branch Hotel Application
  This feature verifies the login functionality of the OMR Branch hotel application,
  including normal login, login with Enter key, and invalid login validations.

  Background:
    Given User is on the OMR Branch hotel page
  
  Scenario Outline: Verify login with valid credentials without Enter Key
    When User enters "<User name>" and "<Password>"
    Then User should verify success message after login "Welcome <First name>"

    Examples:
      | User name   | Password   | First name |
      | sarankarthick167@gmail.com | Karthick@16798 | Karthick |

  Scenario Outline: Verify login with valid credentials using Enter Key via Robot Class
    When User enters "<User name>" and "<Password>" with enter key
    Then User should verify success message after login "Welcome <First name>"

    Examples:
      | User name   | Password   | First name |
      | sarankarthick167@gmail.com | Karthick@16798 | Karthick|
      
  
  Scenario Outline: Verify login with invalid credentials
    When User enters "<User name>" and "<Password>"
    Then User should verify error message after login "Invalid Login details or Your Password might have expired."

    Examples:
      | User name    | Password   |
      | jhnsnrpthrj@gmail.com | hello@123 |

      