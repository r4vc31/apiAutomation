package com.globant.pages;

import com.globant.models.Film;
import com.globant.models.Person;
import com.globant.utils.basePage.BasePage;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.ArrayList;
import java.util.List;

public class FilmPage extends BasePage {

    public Film getFilmByPersonIdAndIndex(int personId, int index) {
        PersonPage personPage = new PersonPage();
        Person person = personPage.getPersonById(personId);
        String filmUrl = person.getFilms().get(index);

        Response response = RestAssured.given().get(filmUrl);
        return response.as(Film.class);
    }

    public List<Film> getAllFilms() {
        Response response = getResponse("films/");
        return response.jsonPath().getList("results", Film.class);
    }

    public Film getFilmDetails(String filmId) {
        Response response = getResponse("films/" + filmId + "/");
        return response.as(Film.class);
    }

    public boolean hasCharacterInFilm(String filmId, String characterName) {
        Film film = getFilmDetails(filmId);
        return film.getCharacters().contains(characterName);
    }

    public int getCharacterCountForFilm(String filmId) {
        Film film = getFilmDetails(filmId);
        return film.getCharacters().size();
    }

    // Additional methods as needed:
    // - getPlanetsForFilm
    // - getSpeciesForFilm
    // - getProducersForFilm
    // - ...

    // Data validation methods:
    public boolean validateFilmTitle(String filmId, String expectedTitle) {
        Film film = getFilmDetails(filmId);
        return film.getTitle().equals(expectedTitle);
    }

    // Handling pagination (if applicable):
    public List<Film> getAllFilmsPaginated() {
        List<Film> allFilms = new ArrayList<>();
        String nextPageUrl = "films/";

        while (nextPageUrl != null) {
            Response response = getResponse(nextPageUrl);
            List<Film> filmsOnPage = response.jsonPath().getList("results", Film.class);
            allFilms.addAll(filmsOnPage);
            nextPageUrl = response.jsonPath().getString("next");
        }

        return allFilms;
    }

    // Error handling methods:
    public void handle404NotFound(String url) {
        Response response = getResponse(url);
        if (response.getStatusCode() != 404) {
            throw new RuntimeException("Expected 404 Not Found, but received status code: " + response.getStatusCode());
        }
    }

}
