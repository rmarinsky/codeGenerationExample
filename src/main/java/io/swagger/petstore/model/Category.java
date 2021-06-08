package io.swagger.petstore.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Category {
    private String name;
    private Integer id;
}
