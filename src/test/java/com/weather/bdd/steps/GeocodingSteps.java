package com.weather.bdd.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Step definitions for the Open-Meteo geocoding endpoint.
 */
public class GeocodingSteps {

    private final ApiContext context;

    public GeocodingSteps(ApiContext context) {
        this.context = context;
    }

    @Given("the geocoding API base URL is {string}")
    public void theGeocodingApiBaseUrlIs(String baseUrl) {
        context.setBaseUrl(baseUrl);
    }

    @When("I search for the city {string}")
    public void iSearchForTheCity(String city) {
        context.setResponse(
                given()
                        .baseUri(context.getBaseUrl())
                        .queryParam("name", city)
                        .queryParam("count", 1)
                        .when()
                        .get("/v1/search"));
    }

    @Then("the first result should have country code {string}")
    public void theFirstResultShouldHaveCountryCode(String countryCode) {
        String actual = context.getResponse().jsonPath().getString("results[0].country_code");
        assertThat(actual).isEqualTo(countryCode);
    }
}
