# Weather API BDD Tests

Cucumber BDD tests for the free [Open-Meteo](https://open-meteo.com/en/docs) weather API.

**Stack:** Java 17 - Gradle - Cucumber 7 - JUnit 5 - REST Assured - AssertJ

## Scenarios

| Feature | Scenario | Tags |
|---|---|---|
| `forecast.feature` | Current temperature for Yerevan: status 200, `current.temperature_2m` present | `@smoke` |
| `forecast.feature` | `forecast_days=3`: `daily.time` has 3 entries | `@smoke` |
| `forecast.feature` | `latitude=100`: status 400 | `@negative` |
| `geocoding.feature` | Scenario Outline: Yerevan / Berlin / Tokyo, check `country_code` | `@smoke` |
| `geocoding.feature` | Non-existent city: no `results` field | `@negative` |

Exact temperature values are never asserted, only structure and status codes.

## Requirements

- Java 17 (JDK)
- Internet access (tests call the live API, no key needed)

Gradle is downloaded automatically by the wrapper.

## Run

```bash
# all scenarios
./gradlew test

# only smoke
./gradlew test -Dcucumber.filter.tags="@smoke"

# only negative
./gradlew test -Dcucumber.filter.tags="@negative"
```

On Windows use `gradlew.bat` instead of `./gradlew`.

## Reports

- Cucumber HTML: `build/reports/cucumber/cucumber.html`
- JUnit HTML: `build/reports/tests/test/index.html`

## Project layout

```
src/test
  java/com/weather/bdd
    RunCucumberTest.java        JUnit 5 suite runner
    steps/
      ApiContext.java           scenario-scoped shared state
      CommonSteps.java          status / field assertions
      ForecastSteps.java        /v1/forecast requests
      GeocodingSteps.java       /v1/search requests
  resources
    features/
      forecast.feature
      geocoding.feature
    junit-platform.properties   glue + report plugins
```
