package com.globant.pages;

import com.globant.models.Planet;
import com.globant.utils.basePage.BasePage;
import io.restassured.response.Response;

import java.util.List;

public class PlanetPage extends BasePage {

    public List<Planet> getAllPlanets() {
        Response response = getResponse("planets/");
        return response.jsonPath().getList("results", Planet.class);
    }

    public Planet getPlanetDetails(String planetId) {
        Response response = getResponse("planets/" + planetId + "/");
        return response.as(Planet.class);
    }

}
