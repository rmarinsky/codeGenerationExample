package io.swagger.petstore;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.commons.lang3.RandomStringUtils;
import org.hamcrest.CoreMatchers;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PetStoreInRawStyleTests {

    private final String
            baseUrl = "http://petstore.swagger.io/v2",
            apiKeyValue = "4a0d8b0e-03fe-4ec3-971d-d009fc6a3b2d";

    @Test
    @DisplayName("Get pet")
    void addNewPetToStoreTest() {
        String testPetId = RandomStringUtils.randomNumeric(10),
                testPetName = "Pet_" + RandomStringUtils.randomAlphabetic(10);

        //Add new pet to the store
        RestAssured.given().baseUri(baseUrl)
                .log().all()
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "  \"id\": " + testPetId + ",\n" +
                        "  \"name\": \"" + testPetName + "\",\n" +
                        "  \"photoUrls\": [],\n" +
                        "  \"tags\": [],\n" +
                        "  \"status\": \"pending\"\n" +
                        "}")
                .header("api_key", apiKeyValue)
                .post("/pet")

                .then()
                .body("name", CoreMatchers.equalTo(testPetName))
                .and()
                .body("id", Matchers.hasToString(testPetId));
    }

}
