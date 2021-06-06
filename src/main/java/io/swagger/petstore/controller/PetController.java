package io.swagger.petstore.controller;

import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;
import io.restassured.response.Response;
import io.restassured.http.ContentType;
import static io.restassured.config.HeaderConfig.headerConfig;
import io.restassured.config.RestAssuredConfig;



public class PetController {

    private RequestSpecification petApi() {
        return given()
                .baseUrl("")
                .config(RestAssuredConfig.config()
                        .headerConfig(headerConfig().overwriteHeadersWithName("Authorization", "Content-Type")))
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .header("api_key", "key");
    }

    public Response getPetById(String id) {
        return petApi()
                .get(id);
    }

    public Response createPet(PetDto pet) {
        return petApi()
                .body(pet)
                .post();

    }

    public Response deletePetById(String id) {
        return petApi()
                .delete(id);
    }

}
