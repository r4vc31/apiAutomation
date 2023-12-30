package com.globant.tests;

import com.globant.models.Person;
import com.globant.pages.PersonPage;
import com.globant.utils.baseTest.BaseTest;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

public class PersonDetailsTest extends BaseTest {
    private PersonPage personPage;

    @BeforeClass
    public void setup() {
        personPage = new PersonPage();
    }

    @Test
    public void testCharacterDetails() {
        Response response = personPage.getPersonDetails("2");

        // Assert successful response
        assertEquals(200, response.getStatusCode());

        // Get Person object and access properties
        Person people2 = response.as(Person.class);

        // Assert skin color
        assertEquals("gold", people2.getSkinColor());

        // Assert film count
        assertEquals(6, people2.getFilms().size());
    }
}
