package com.globant.utils.baseTest;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseTest {

    @BeforeClass
    public void setupBase() {
        RestAssured.baseURI = "https://swapi.dev/api/";
    }

    protected Response sendGetRequest(String endpoint) {
        return RestAssured.given().get(endpoint);
    }

    protected void assertStatusCode(Response response, int expectedStatusCode) {
        Assert.assertEquals(expectedStatusCode, response.getStatusCode());
    }

    protected <T> T extractResponseData(Response response, Class<T> responseType) {
        return response.as(responseType);
    }

    @AfterClass
    public static void teardown() {
        // No specific cleanup actions needed for these tests
    }

}



