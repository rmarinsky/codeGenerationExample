package io.swagger.petstore;

import io.swagger.petstore.controller.PetController;

class PetStoreObjectStyleTests {

    private final PetController petController = new PetController();

    @org.junit.jupiter.api.Test
    @org.junit.jupiter.api.DisplayName("Create and get pet by id")
    void createAndGetPet() {
        var targetPet = new PerDto().id("0");

        petController.createPet(targetPet);

        var petById = petController.getPetById(targetPet.getId());

        Assertions.assertThat(petById.as(PetDto.class)).isEqualTo(targetPet);
    }

}
