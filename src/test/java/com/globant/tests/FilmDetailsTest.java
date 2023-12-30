package com.globant.tests;

import com.globant.models.Person;
import com.globant.pages.PersonPage;
import com.globant.utils.baseTest.BaseTest;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class FilmDetailsTest extends BaseTest {
    private PersonPage personPage;

    @BeforeClass
    public void setup() {
        personPage = new PersonPage();
    }
    @Test
    public void testSecondFilmDetails() {
        Response response = personPage.getPersonDetails("2");

        String secondFilmUrl = response.as(Person.class).getFilms().get(1);

        Response filmResponse = personPage.getResponse(secondFilmUrl);

        // Assert release date format
        String releaseDate = filmResponse.jsonPath().getString("release_date");
        assertTrue(releaseDate.matches("\\d{4}-\\d{2}-\\d{2}"));

        // Assert array sizes
        assertTrue(filmResponse.jsonPath().getList("characters").size() > 1);
        assertTrue(filmResponse.jsonPath().getList("planets").size() > 1);
        assertTrue(filmResponse.jsonPath().getList("starships").size() > 1);
        assertTrue(filmResponse.jsonPath().getList("vehicles").size() > 1);
        assertTrue(filmResponse.jsonPath().getList("species").size() > 1);
    }

}
