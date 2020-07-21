Feature: IMDB login

  Scenario: Correct Credentials
    Given the user visits IMDB login page
    When the user enters valid credentials
    Then the user should be Logged in

  Scenario: Missing credentials
    Given the user visits IMDB login page
    When the user enters empty credentials
    Then the messages should be
      |Enter your email|
      |Enter your password|

  Scenario: Incorrect Credentials
    Given the user visits IMDB login page
    When the user enters an invalid password
    Then the message should be "Your password is incorrect"

  Scenario: Logout
    Given  the user is logged in
    When the user selects Sign Out
    Then the user should be not Logged in
