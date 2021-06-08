package io.swagger.petstore.controller;

import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.swagger.petstore.models.Pet;

import static io.restassured.RestAssured.given;
import static io.restassured.config.HeaderConfig.headerConfig;


public class PetController {

    private RequestSpecification petApi() {
        return given()
                .baseUri("http://petstore.swagger.io/v2")
                .config(RestAssuredConfig.config()
                        .headerConfig(
                                headerConfig().overwriteHeadersWithName("Authorization", "Content-Type")
                        )
                )
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .header("api_key", "1qa2ws3ed4rfvcxz")
                .log().all();
    }

    public Response getPetById(String id) {
        return petApi()
                .get(id);
    }

    public Response createPet(Pet targetPet) {
        return petApi()
                .body(targetPet)
                .post();
    }

    public Response deletePetById(String id) {
        return petApi()
                .delete(id);
    }

}
