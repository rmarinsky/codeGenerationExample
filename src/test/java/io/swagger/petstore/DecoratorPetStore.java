package io.swagger.petstore;

import com.github.javafaker.Faker;
import io.restassured.response.Response;
import io.swagger.petstore.common.ResponseAssertion;
import io.swagger.petstore.common.ResponseExpectMessages;
import io.swagger.petstore.controller.PetController;
import io.swagger.petstore.models.Category;
import io.swagger.petstore.models.Pet;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class DecoratorPetStore {

    private final PetController petController = new PetController();
    private final Faker faker = new Faker();

    @Test
    @DisplayName("Create and get pet by id")
    void createAndGetPet() {
        var targetPet = new Pet()
                .id(faker.number().randomNumber())
                .name(faker.name().name())
                .category(new Category()
                        .id(faker.number().randomNumber())
                        .name(faker.name().username())
                ).status(Pet.StatusEnum.AVAILABLE)
                .photoUrls(new ArrayList<>())
                .tags(new ArrayList<>());

        Response pet = petController.createPet(targetPet);
        new ResponseAssertion(pet)
                .statusCodeIsEqualTo(ResponseExpectMessages.StatusCode.OK);

        var petById = petController.getPetById(targetPet.getId().toString());

        new ResponseAssertion(petById)
                .statusCodeIsEqualTo(ResponseExpectMessages.StatusCode.OK)
                .responseIsEqualTo(targetPet);
    }

}
