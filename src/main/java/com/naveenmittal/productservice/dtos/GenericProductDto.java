package com.naveenmittal.productservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenericProductDto {
    private Long id;
    private String title;
    private String image;
    private String description;
    private Double price;
    private String category;
}
