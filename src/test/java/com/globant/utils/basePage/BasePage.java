package com.globant.utils.basePage;

import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.response.Response;

import java.util.List;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class BasePage {
    public BasePage() {
        RestAssured.config = RestAssuredConfig.config().encoderConfig(EncoderConfig.encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false));
    }

    public Response getResponse(String endpoint) {
        return given().baseUri(baseURI).get(endpoint);
    }

    public Response postRequest(String endpoint, Object body) {
        return given().baseUri(baseURI).body(body).post(endpoint);
    }

    public boolean hasElementsInResponse(String url, List<String> elements) {
        Response response = getResponse(url);
        for (String element : elements) {
            if (!response.getBody().toString().contains(element)) {
                return false;
            }
        }
        return true;
    }

    public void handle404NotFound(String endpoint) {
        try {
            // Request the /films/7/ endpoint
            Response response = getResponse(endpoint);  // Replace with your actual method for making requests

            // Assert the status code is 404 (Not Found)
            assertEquals(404, response.statusCode());
        } catch (Exception e) {
            // Handle any potential errors during the request
            System.err.println("Error during request: " + e.getMessage());
            // Fail the test or handle the error appropriately
        }

    }
}


