package com.globant.tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.globant.models.Film;
import com.globant.models.Person;
import com.globant.models.Planet;
import com.globant.pages.FilmPage;
import com.globant.pages.PersonPage;

import com.globant.utils.baseTest.BaseTest;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class PlanetDetailsTest extends BaseTest {
    private PersonPage personPage;
    private FilmPage filmPage;

    @BeforeClass
    public void setup() {
        personPage = new PersonPage();
        filmPage = new FilmPage();
    }
    @Test
    @Parameters({"person-id", "planet-number"})
    public void testFirstPlanetDetailsFromLastFilm(String personId, int planetNumber) {
        Person person = extractResponseData(personPage.getPersonDetails(personId), Person.class);
        List<String> films = person.getFilms();

        String lastFilmUrl = films.get(films.size() - 1);  // Get last film URL
        Film film = extractResponseData(sendGetRequest(lastFilmUrl), Film.class);

        String firstPlanetUrl = film.getPlanets().get(planetNumber-1);
        Planet planet = extractResponseData(sendGetRequest(firstPlanetUrl), Planet.class);

        // Read fixture data
        ObjectMapper mapper = new ObjectMapper();
        Planet expectedPlanet = null;
        try {
            expectedPlanet = mapper.readValue(getClass().getResource("/fixtures/planet.json"), Planet.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Assert gravity and terrains
        assertValue(expectedPlanet.getGravity(), planet.getGravity(), "Expected gravity to be " + expectedPlanet.getGravity());

        // Compare terrains after splitting
        assertValue(expectedPlanet.getTerrain(), planet.getTerrain(), "Expected terrain to be " + expectedPlanet.getTerrain());
    }

}
