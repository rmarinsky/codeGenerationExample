package io.swagger.petstore.controller;

import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.swagger.petstore.model.PetDto;

import static io.restassured.RestAssured.given;
import static io.restassured.config.HeaderConfig.headerConfig;


public class PetController {

    private RequestSpecification petApi() {
        return given()
                .baseUri("http://petstore.swagger.io/v2")
                .basePath("pet")
                .contentType(ContentType.JSON)
                .header("api_key", "4a0d8b0e-03fe-4ec3-971d-d009fc6a3b2d")
                .log().all();
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
