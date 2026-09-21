package com.weather.bdd.steps;

import io.cucumber.java.en.Then;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Generic response assertions shared by every feature: status code and
 * presence, absence or size of a JSON path in the response body.
 */
public class CommonSteps {

    private final ApiContext context;

    public CommonSteps(ApiContext context) {
        this.context = context;
    }

    @Then("the response status code should be {int}")
    public void theResponseStatusCodeShouldBe(int expected) {
        assertThat(context.getResponse().statusCode()).isEqualTo(expected);
    }

    @Then("the response should contain the field {string}")
    public void theResponseShouldContainTheField(String path) {
        Object value = context.getResponse().jsonPath().get(path);
        assertThat(value).as("field %s should be present", path).isNotNull();
    }

    @Then("the response should not contain the field {string}")
    public void theResponseShouldNotContainTheField(String path) {
        Object value = context.getResponse().jsonPath().get(path);
        assertThat(value).as("field %s should be absent", path).isNull();
    }

    @Then("the field {string} should contain {int} entries")
    public void theFieldShouldContainEntries(String path, int size) {
        List<?> values = context.getResponse().jsonPath().getList(path);
        assertThat(values).as("field %s", path).hasSize(size);
    }
}
