package com.naveenmittal.productservice.thirdpartyclients.productservice.fakestore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProductDto {
    private Long id;
    private String title;
    private String image;
    private String description;
    private Double price;
    private String category;
}
