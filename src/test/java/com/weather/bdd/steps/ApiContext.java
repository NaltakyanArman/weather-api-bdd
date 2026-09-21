package com.weather.bdd.steps;

import io.restassured.response.Response;

/**
 * Scenario-scoped holder for state shared between step definition classes.
 * Cucumber creates a new instance per scenario through its default
 * dependency-injection mechanism, so responses never leak across scenarios.
 */
public class ApiContext {

    private String baseUrl;
    private Response response;

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }
}
