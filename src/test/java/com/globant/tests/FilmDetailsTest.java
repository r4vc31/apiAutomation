package com.globant.tests;

import com.globant.models.Film;
import com.globant.models.Person;
import com.globant.pages.FilmPage;
import com.globant.pages.PersonPage;
import com.globant.utils.baseTest.BaseTest;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class FilmDetailsTest extends BaseTest {
    private PersonPage personPage;
    private FilmPage filmPage;

    @BeforeClass
    public void setup() {
        personPage = new PersonPage();
        filmPage = new FilmPage();
    }
    @Test
    @Parameters({"person-id", "film-number"})
    public void testSecondFilmDetails(String personId, int filmNumber) {
        Person person = extractResponseData(personPage.getPersonDetails(personId), Person.class);

        String secondFilmUrl = person.getFilms().get(filmNumber - 1);

        Film film = extractResponseData(sendGetRequest(secondFilmUrl), Film.class);

        // Assert release date format
        filmPage.validateFilmDate(film.getReleaseDate());

        // Assert array sizes
        validateMoreThanOneElement(film.getCharacters());
        validateMoreThanOneElement(film.getPlanets());
        validateMoreThanOneElement(film.getStarships());
        validateMoreThanOneElement(film.getVehicles());
        validateMoreThanOneElement(film.getSpecies());
    }

}
