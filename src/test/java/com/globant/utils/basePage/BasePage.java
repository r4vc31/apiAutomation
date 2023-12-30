package com.globant.utils.basePage;

import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.response.Response;

import java.util.List;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

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

}


