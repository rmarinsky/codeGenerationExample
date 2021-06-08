package io.swagger.petstore;

import com.github.javafaker.Faker;
import io.swagger.petstore.controller.PetController;
import io.swagger.petstore.model.Category;
import io.swagger.petstore.model.PetDto;
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
        var targetPet = PetDto.builder()
                .id(faker.number().randomDigitNotZero())
                .name(faker.name().name())
                .category(Category.builder()
                        .id(faker.number().randomDigitNotZero())
                        .name(faker.name().username())
                        .build()
                ).status("available")
                .photoUrls(new ArrayList<>())
                .tags(new ArrayList<>())
                .build();

        petController.createPet(targetPet);

        var petById = petController.getPetById(targetPet.getId().toString());

        Assertions.assertThat(petById.as(PetDto.class)).isEqualTo(targetPet);
    }

}
