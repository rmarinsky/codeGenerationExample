package io.swagger.petstore;

import com.github.javafaker.Faker;
import io.swagger.petstore.controller.PetController;
import io.swagger.petstore.models.Category;
import io.swagger.petstore.models.Pet;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class PetStoreObjectStyleTests {

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

        petController.createPet(targetPet);

        var petById = petController.getPetById(targetPet.getId().toString());

        Assertions.assertThat(petById.as(Pet.class)).isEqualTo(targetPet);
    }

}
