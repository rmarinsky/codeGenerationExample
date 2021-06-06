package io.swagger.petstore;

public class PetStoreInRawStyleTests {

    private final String
            baseUrl = "http://petstore.swagger.io/v2",
            apiKeyValue = "1qa2ws3ed4rfvcxz";

    @Test
    public void addNewPetToStoreTest() {
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
