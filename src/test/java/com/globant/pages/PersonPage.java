package com.globant.pages;

import com.globant.models.Film;
import com.globant.models.Person;
import com.globant.utils.basePage.BasePage;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class PersonPage extends BasePage {

    public Person getPersonById(int id) {
        Response response = RestAssured.given().get("https://swapi.dev/api/people/" + id);
        return response.as(Person.class);
    }

    public Response getPersonDetails(String personId) {
        return getResponse("people/" + personId + "/");
    }

    public List<String> getFilmsForPerson(String personId) {
        Response response = getResponse("people/" + personId + "/");
        Person person = response.as(Person.class);
        return person.getFilms();
    }

    public boolean hasSkinColor(String personId, String skinColor) {
        Person person = getPersonDetails(personId).as(Person.class);
        return person.getSkinColor().equals(skinColor);
    }

    public int getFilmCountForPerson(String personId) {
        return getFilmsForPerson(personId).size();
    }

    // Additional methods as needed:
    // - getSpeciesForPerson
    // - getHomeworldForPerson
    // - ...

    // Data validation methods:
    public boolean validateFilmDate(String filmUrl) {
        Response response = getResponse(filmUrl);
        Film film = response.as(Film.class);
        String releaseDate = film.getReleaseDate();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Adjust format as needed
        try {
            dateFormat.parse(releaseDate);
            return true; // Date is valid
        } catch (ParseException e) {
            return false; // Date is invalid
        }
    }

}

