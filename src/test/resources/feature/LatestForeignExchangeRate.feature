@Regression
Feature: Test for Rates API for Latest Foreign Exchange rates

  Scenario: Display response if incorrect URL is provided
    Given the user performs the get call on the "latest1" foreign exchange rates

  Scenario: Display all the latest foreign exchange reference rates
    Given the user performs the get call on the "latest" foreign exchange rates
    Then displays all the foreign exchange rates
    And verify base is "EUR"

  Scenario: Request specific exchange rates by setting the symbols parameter
    Given the user performs the get call on the "latest" foreign exchange rates based on "symbols"
      | USD |
      | GBP |
    Then the user displays the filtered exchange rates

  Scenario Outline: Verify rates are displayed as per the selected Base
    Given the user performs the get call on the "latest" foreign exchange rates based on "base"
      | <base> |
    And verify base is "<base>"
    Then verify rate for "<base>" is "1.0"

    Examples: 
      | base |
      | USD  |
      | JPY  |

  Scenario Outline: Verify rates are displayed as per the selected Base and symbols
    Given the user performs the get call on the latest foreign exchange rates based on "base" and "symbols"
      | <base> | <symbols> |
    And verify "base" is "<base>"
    And verify "symbols" is "<symbols>"

    Examples: 
      | base | symbols |
      | USD  | GBP     |
      | JPY  | GBP     |
