package io.swagger.petstore.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TagsItem {
    private String name;
    private Integer id;
}
