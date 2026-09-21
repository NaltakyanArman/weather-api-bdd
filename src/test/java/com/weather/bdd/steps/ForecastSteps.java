package com.weather.bdd.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

import static io.restassured.RestAssured.given;

/**
 * Step definitions for the Open-Meteo forecast endpoint.
 */
public class ForecastSteps {

    private final ApiContext context;

    public ForecastSteps(ApiContext context) {
        this.context = context;
    }

    @Given("the forecast API base URL is {string}")
    public void theForecastApiBaseUrlIs(String baseUrl) {
        context.setBaseUrl(baseUrl);
    }

    @When("I request the forecast for latitude {double} and longitude {double} with current {string}")
    public void iRequestTheForecastWithCurrent(double latitude, double longitude, String current) {
        context.setResponse(
                given()
                        .baseUri(context.getBaseUrl())
                        .queryParam("latitude", latitude)
                        .queryParam("longitude", longitude)
                        .queryParam("current", current)
                        .when()
                        .get("/v1/forecast"));
    }

    @When("I request the forecast for latitude {double} and longitude {double} with daily {string} for {int} days")
    public void iRequestTheForecastWithDaily(double latitude, double longitude, String daily, int days) {
        context.setResponse(
                given()
                        .baseUri(context.getBaseUrl())
                        .queryParam("latitude", latitude)
                        .queryParam("longitude", longitude)
                        .queryParam("daily", daily)
                        .queryParam("forecast_days", days)
                        .when()
                        .get("/v1/forecast"));
    }
}
