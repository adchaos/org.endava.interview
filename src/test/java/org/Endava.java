package org;

import io.restassured.response.Response;
import jdk.jfr.Description;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.params.ParameterizedTest.DEFAULT_DISPLAY_NAME;
import static org.junit.jupiter.params.ParameterizedTest.DISPLAY_NAME_PLACEHOLDER;

public class Endava {

    String url = "https://reqres.in/api/";

    /**
     * Junit 5 provide the data for the test.
     * Verify that three endpoints are with status 200
     *
     * @param endpoints
     */
    @ParameterizedTest(name = DISPLAY_NAME_PLACEHOLDER + DEFAULT_DISPLAY_NAME)
    @Tag("positive")
    @DisplayName("Verify the correct statuses of the provided endpoints")
    @Description("Verify the correct statuses of the provided endpoints")
    @ValueSource(strings = {"users/2", "login", "users?page=2"})
    void verifySuccessEndpointStatuses(String endpoints) {
        Response response = given().relaxedHTTPSValidation().get(url + endpoints);
        System.out.println("Hit url: " + url + endpoints);
        Assertions.assertEquals(200, response.getStatusCode(), "The endpoint responses with: " + response.getStatusLine());
        Assertions.assertEquals("HTTP/1.1 200 OK", response.getStatusLine(), "The endpoint status line should be: HTTP/1.1 200 OK");
        response.prettyPrint();
    }

    /**
     * Junit 5 provide the data for the test.
     * Three empty parameters to test negative cases.
     *
     * @param negativeEndpoints
     */
    @ParameterizedTest(name = DISPLAY_NAME_PLACEHOLDER + DEFAULT_DISPLAY_NAME)
    @Tag("negative")
    @DisplayName("Verify the correct statuses of the provided endpoints")
    @Description("Verify the correct statuses of the provided endpoints")
    @ValueSource(strings = {"  ", "\t", "\n"})
    void verifyErrorEndpointStatuses(String negativeEndpoints) {
        Response response = given().relaxedHTTPSValidation().get(url + negativeEndpoints);
        System.out.println("Hit url: " + url + negativeEndpoints);
        Assertions.assertEquals(404, response.getStatusCode(), "The endpoint response should be with status code 400");
        Assertions.assertEquals("HTTP/1.1 404 Not Found", response.getStatusLine(), "The endpoint status line should be: HTTP/1.1 404 Not Found");
        response.prettyPrint();
    }
}
