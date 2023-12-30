package com.globant.tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.globant.models.Person;
import com.globant.models.Planet;
import com.globant.pages.PersonPage;
import com.globant.utils.baseTest.BaseTest;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.testng.AssertJUnit.assertEquals;


import java.io.IOException;

public class PlanetTest extends BaseTest {
    private PersonPage personPage;

    @BeforeClass
    public void setup() {
        personPage = new PersonPage();
    }
    @Test
    public void testPlanetResponseConsistency() {
        Response response = personPage.getPersonDetails("2");
        String lastFilmUrl = response.as(Person.class).getFilms().get(response.as(Person.class).getFilms().size() - 1);  // Get last film URL

        Response filmResponse = personPage.getResponse(lastFilmUrl);

        String firstPlanetUrl = filmResponse.jsonPath().getString("planets[0]");

        Response planetResponse = personPage.getResponse(firstPlanetUrl);
        Planet planet = planetResponse.as(Planet.class);

        // Grab the URL from the planet response
        String planetUrl = planetResponse.jsonPath().getString("url");

        // Request the same URL again
        Response secondPlanetResponse = personPage.getResponse(planetUrl);  // Use personPage for consistency
        Planet secondPlanet = secondPlanetResponse.as(Planet.class);

        // Convert responses to String for comparison (consider handling potential serialization variations)
        String firstPlanetString = planetResponse.asString();
        String secondPlanetString = secondPlanetResponse.asString();

        // Validate the response is exactly the same
        assertEquals(firstPlanetString, secondPlanetString);  // Assert entire object equality
    }

}
