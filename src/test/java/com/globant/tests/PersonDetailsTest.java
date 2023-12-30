package com.globant.tests;

import com.globant.models.Person;
import com.globant.pages.PersonPage;
import com.globant.utils.baseTest.BaseTest;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

public class PersonDetailsTest extends BaseTest {
    private PersonPage personPage;

    @BeforeClass
    public void setup() {
        personPage = new PersonPage();
    }

    @Test
    @Parameters({"person-id", "skin-color", "film-count"})
    public void testPersonDetails(String personId, String skinColor, int filmCount) {
        Response personResponse = personPage.getPersonDetails(personId);

        // Assert response
        assertStatusCode(personResponse, 200);

        // Get Person object and access properties
        Person people2 = extractResponseData(personResponse, Person.class);

        // Assert skin color
        assertValue(skinColor, people2.getSkinColor(), "Expected skin color to be " + skinColor);

        // Assert film count
        assertValue(String.valueOf(filmCount), String.valueOf(people2.getFilms().size()), "Expected film count to be " + filmCount);
    }
}
