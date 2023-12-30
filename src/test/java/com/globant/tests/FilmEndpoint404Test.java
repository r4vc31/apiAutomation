package com.globant.tests;

import com.globant.pages.FilmPage;
import com.globant.pages.PersonPage;
import com.globant.utils.baseTest.BaseTest;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

public class FilmEndpoint404Test extends BaseTest {
    private FilmPage filmPage;

    @BeforeClass
    public void setup() {
        filmPage = new FilmPage();
    }
    @Test
    @Parameters({"film-id"})
    public void test404ResponseForNonExistentFilm(String filmId) {
        assertStatusCode(filmPage.getFilmDetails(filmId), 404);
    }

}
