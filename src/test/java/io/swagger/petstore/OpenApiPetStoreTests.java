package io.swagger.petstore;

import com.github.javafaker.Faker;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.swagger.petstore.controller.PetApi;
import io.swagger.petstore.models.Category;
import io.swagger.petstore.models.Pet;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class OpenApiPetStoreTests {

    private final RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder()
            .setBaseUri("https://petstore.swagger.io/v2")
            .setBasePath("pet")
            .addHeader("api_key", "4a0d8b0e-03fe-4ec3-971d-d009fc6a3b2d")
            .setContentType(ContentType.JSON);

    private final Faker faker = new Faker();


    @Test
    @DisplayName("Create and get test")
    void createAndGetTest() {
        Pet targetPet = new Pet()
                .id(faker.number().randomNumber())
                .name(faker.name().name())
                .category(new Category()
                        .id(faker.number().randomNumber())
                        .name(faker.name().username())
                ).status(Pet.StatusEnum.AVAILABLE)
                .photoUrls(new ArrayList<>())
                .tags(new ArrayList<>());


        Assertions.assertThatCode(() ->
                new PetApi.AddPetOper(requestSpecBuilder).body(targetPet).execute(response -> response)
        ).doesNotThrowAnyException();

    }

}
