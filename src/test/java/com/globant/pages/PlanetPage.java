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

    public String getPlanetGravity(String planetId) {
        Planet planet = getPlanetDetails(planetId);
        return planet.getGravity();
    }

    public String getPlanetTerrain(String planetId) {
        Planet planet = getPlanetDetails(planetId);
        return planet.getTerrain();
    }

    // Additional methods as needed:
    // - getResidentsForPlanet
    // - getFilmsForPlanet
    // - getClimateForPlanet
    // - ...

    // Data validation methods:
    public boolean validatePlanetName(String planetId, String expectedName) {
        Planet planet = getPlanetDetails(planetId);
        return planet.getName().equals(expectedName);
    }

}
