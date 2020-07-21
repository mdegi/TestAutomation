Feature: IMDB Account

  Scenario: Change
    Given the user visits IMDB login page
    When the user enters valid credentials
    And the user visits Account settings
    And the user visits Personal details
    And the user changes gender to Male
    And the user presses the submit button
    Then the gender is Male

