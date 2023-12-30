package com.globant.tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.globant.models.Film;
import com.globant.models.Person;
import com.globant.models.Planet;
import com.globant.pages.PersonPage;
import com.globant.utils.baseTest.BaseTest;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import static org.testng.AssertJUnit.assertEquals;


import java.io.IOException;
import java.util.List;

public class PlanetTest extends BaseTest {
    private PersonPage personPage;

    @BeforeClass
    public void setup() {
        personPage = new PersonPage();
    }
    @Test
    @Parameters({"person-id", "planet-number"})
    public void testPlanetResponseConsistency(String personId, int planetNumber) {
        Person person = extractResponseData(personPage.getPersonDetails(personId), Person.class);
        List<String> films = person.getFilms();

        String lastFilmUrl = films.get(films.size() - 1);  // Get last film URL
        Film film = extractResponseData(sendGetRequest(lastFilmUrl), Film.class);

        String firstPlanetUrl = film.getPlanets().get(planetNumber-1);

        Response planetResponse = sendGetRequest(firstPlanetUrl);

        // Grab the URL from the planet response
        String planetUrl = planetResponse.jsonPath().getString("url");

        // Request the same URL again
        Response secondPlanetResponse = personPage.getResponse(planetUrl);

        // Convert responses to String for comparison (consider handling potential serialization variations)
        String firstPlanetString = planetResponse.asString();
        String secondPlanetString = secondPlanetResponse.asString();

        // Validate the response is exactly the same
        assertValue(firstPlanetString, secondPlanetString, "The responses do not match"); // Assert entire object equality
    }

}
