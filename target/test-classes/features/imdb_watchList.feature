Feature: IMDB WatchList

  Scenario: Add item to watch list
    Given the user is logged in
    When the user visits Watchlist
    And the user selects edit
    And the user enters Rambo: Last Blood (2019) in search box
    And the user selects the first entry displayed
    And the user selects the DONE button
    Then Rambo: Last Blood shows in list view

  Scenario: Confirm no duplicates
    Given the user is logged in
    When the user visits Watchlist
    And the user selects edit
    And the user enters Rambo: Last Blood (2019) in search box
    And the user selects the first entry displayed
    And the user enters Rambo: Last Blood (2019) in search box
    And the user selects the first entry displayed
    And the user selects the DONE button
    Then total entries is 1

  Scenario: Remove item from watchlist - Failing Item not removed
    Given the user is logged in
    When the user visits Watchlist
    And the user selects edit
    And the user selects the first entry checkbox
    And the user selects DELETE
    And the user confirms DELETE
    And the user selects the DONE button
    Then total entries is 0

  Scenario: Switch View
    Given the user is logged in
    When the user visits Watchlist
    And the user switches view
    Then Then Rambo: Last Blood does not show in list view

  Scenario: Refine list
    Given the user visits IMDB login page
    When the user enters valid credentials
    And the user visits Watchlist
    And the user selects REFINE
    Then refined list is showing