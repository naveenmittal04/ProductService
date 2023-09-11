package com.naveenmittal.productservice.services;

import com.naveenmittal.productservice.dtos.GenericProductDto;

import java.util.List;

public interface ProductService {
    GenericProductDto getProductById(Long id);

    GenericProductDto createProduct(GenericProductDto productDto);

    List<GenericProductDto> getAllProducts();
}
