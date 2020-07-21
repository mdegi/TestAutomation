Feature: IMDB search

  Scenario: Change search results order
    Given the user visits IMDB home page
    When the user visits Advanced Search
    And the user visits Advanced Search Movies, TV and Video Games
    And the user clicks IMDB Top 100
    And the user selects display 100 per page
    And the user selects sorted by A-Z Ascending
    And the user presses the search button
    Then a list should be displayed in A-Z Ascending

  Scenario: Advanced Search
    Given the user visits IMDB home page
    When the user visits Advanced Search
    And the user visits Advanced Search Movies, TV and Video Games
    And the user clicks IMDB Top 100
    And the user presses the search button
    Then a list should be displayed

  Scenario: Search that finds Results
    Given the user visits IMDB home page
    When the user searches for Batman
    Then the search message should be Results for "Batman"
    And results are displayed

  Scenario: Search that does not find Results
    Given the user visits IMDB home page
    When the user searches for unseacheable
    Then the search message should be No results found for "unseacheable"
    And results are not displayed

