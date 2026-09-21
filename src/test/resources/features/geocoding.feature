Feature: Open-Meteo geocoding
  As an API consumer
  I want to search locations by name
  So that I can resolve a city to its coordinates and country

  Background:
    Given the geocoding API base URL is "https://geocoding-api.open-meteo.com"

  @smoke
  Scenario Outline: City search returns the expected country code
    When I search for the city "<city>"
    Then the response status code should be 200
    And the first result should have country code "<country_code>"

    Examples:
      | city    | country_code |
      | Yerevan | AM           |
      | Berlin  | DE           |
      | Tokyo   | JP           |

  @negative
  Scenario: Non-existent city returns no results
    When I search for the city "Xyzqwertyville"
    Then the response status code should be 200
    And the response should not contain the field "results"
