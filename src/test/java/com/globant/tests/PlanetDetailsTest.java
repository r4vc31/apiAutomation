package com.globant.tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.globant.models.Person;
import com.globant.models.Planet;
import com.globant.pages.PersonPage;

import com.globant.utils.baseTest.BaseTest;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class PlanetDetailsTest extends BaseTest {
    private PersonPage personPage;

    @BeforeClass
    public void setup() {
        personPage = new PersonPage();
    }
    @Test
    public void testFirstPlanetDetailsFromLastFilm() {
        Response response = personPage.getPersonDetails("2");
        String lastFilmUrl = response.as(Person.class).getFilms().get(response.as(Person.class).getFilms().size() - 1);  // Get last film URL

        Response filmResponse = personPage.getResponse(lastFilmUrl);

        String firstPlanetUrl = filmResponse.jsonPath().getString("planets[0]");

        Response planetResponse = personPage.getResponse(firstPlanetUrl);
        Planet planet = planetResponse.as(Planet.class);

        // Read fixture data
        ObjectMapper mapper = new ObjectMapper();
        Planet expectedPlanet = null;
        try {
            expectedPlanet = mapper.readValue(getClass().getResource("/fixtures/planet.json"), Planet.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Assert gravity and terrains
        assertEquals(planet.getGravity(), expectedPlanet.getGravity());
        // Compare terrains after splitting
        List<String> actualTerrains = Arrays.asList(planet.getTerrain().split(", "));
        List<String> expectedTerrains = Arrays.asList(expectedPlanet.getTerrain().split(", "));
        assertTrue(actualTerrains.containsAll(expectedTerrains));  // Ensure all expected terrains are present
    }

}
