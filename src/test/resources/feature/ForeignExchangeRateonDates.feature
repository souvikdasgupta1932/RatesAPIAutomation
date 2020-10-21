@Regression
Feature: Test for Rates API for Foreign Exchange rates based on dates

 
  Scenario Outline: Display all the foreign exchange reference rates based on dates
    Given the user performs the get call on the foreign exchange rates for date
      | <date> |
    Then displays all the foreign exchange rates
    And verify "base" is "EUR"
    And verify "date" is "<date>"

    Examples: 
      | date       |
      | 2020-10-04 |

  Scenario Outline: Request specific exchange rates by setting the symbols parameter
    Given the user performs the get call on the "<date>" foreign exchange rates based on "symbols"
      | USD |
      | GBP |
    Then the user displays the filtered exchange rates

    Examples: 
      | date       |
      | 2020-01-09 |

  Scenario Outline: Verify current system date is displayed in case future date values are requested for
    Given the user performs the get call on the foreign exchange rates for date
      | <date> |
    And verify "date" is "<date>"
    Examples:
    |date|
    |2020-11-21|
