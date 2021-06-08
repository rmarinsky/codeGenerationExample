package io.swagger.petstore.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PetDto {
    private List<String> photoUrls;
    private String name;
    private Integer id;
    private Category category;
    private List<TagsItem> tags;
    private String status;
}
