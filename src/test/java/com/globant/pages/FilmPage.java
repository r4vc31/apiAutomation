package com.globant.pages;

import com.globant.models.Film;
import com.globant.utils.basePage.BasePage;
import io.restassured.response.Response;

import java.util.List;

import static org.testng.AssertJUnit.assertTrue;

public class FilmPage extends BasePage {

    public List<Film> getAllFilms() {
        Response response = getResponse("films/");
        return response.jsonPath().getList("results", Film.class);
    }

    public Response getFilmDetails(String filmId) {
        return getResponse("films/" + filmId + "/");
    }


    public void validateFilmDate(String releaseDate) {
        assertTrue(releaseDate.matches("\\d{4}-\\d{2}-\\d{2}"));
    }

}
