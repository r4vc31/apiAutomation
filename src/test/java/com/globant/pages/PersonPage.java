package com.globant.pages;

import com.globant.models.Film;
import com.globant.models.Person;
import com.globant.utils.basePage.BasePage;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import static org.testng.AssertJUnit.assertEquals;

public class PersonPage extends BasePage {

    public Response getPersonDetails(String personId) {
        return getResponse("people/" + personId + "/");
    }

    public List<String> getFilmsForPerson(String personId) {
        Response response = getPersonDetails(personId);
        Person person = response.as(Person.class);
        return person.getFilms();
    }

    public int getFilmCountForPerson(String personId) {
        return getFilmsForPerson(personId).size();
    }


}

