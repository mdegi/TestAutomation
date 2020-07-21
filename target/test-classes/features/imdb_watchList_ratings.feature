Feature: IMDB Ratings

  Scenario: Add rating to item
    Given the user is logged in
    When the user visits Watchlist
    And the user enters 10 star rating for Rambo: Last Blood
    Then the title has 10 star rating

  Scenario: Change rating of item
    Given the user is logged in
    When the user visits Watchlist
    And the user enters 6 star rating for Rambo: Last Blood
    Then the title has 6 star rating

  Scenario: Remove rating of item
    Given the user is logged in
    When the user visits Watchlist
    And the user cancels rating for Rambo: Last Blood
    Then the title has 0 star rating

  Scenario: Switch to public rating
    Given the user is logged in
    When the user visits Watchlist
    And the user visits public rating for Rambo: Last Blood
    Then User Ratings is Displayed for Rambo: Last Blood

  Scenario: Filter ratings
    Given the user is logged in
    When the user visits Watchlist
    And the user visits public rating for Rambo: Last Blood
    And the user selects <18 rating
    Then total users should tally individual users
