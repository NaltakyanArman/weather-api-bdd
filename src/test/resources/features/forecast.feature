Feature: Open-Meteo weather forecast
  As an API consumer
  I want to query the Open-Meteo forecast endpoint
  So that I can get current and daily weather data for a location

  Background:
    Given the forecast API base URL is "https://api.open-meteo.com"

  @smoke
  Scenario: Current temperature for Yerevan
    When I request the forecast for latitude 40.18 and longitude 44.51 with current "temperature_2m"
    Then the response status code should be 200
    And the response should contain the field "current.temperature_2m"

  @smoke
  Scenario: Daily forecast for 3 days
    When I request the forecast for latitude 40.18 and longitude 44.51 with daily "temperature_2m_max" for 3 days
    Then the response status code should be 200
    And the field "daily.time" should contain 3 entries

  @negative
  Scenario: Invalid latitude is rejected
    When I request the forecast for latitude 100 and longitude 44.51 with current "temperature_2m"
    Then the response status code should be 400
    And the response should contain the field "error"
