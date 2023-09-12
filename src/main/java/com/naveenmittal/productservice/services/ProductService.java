package com.naveenmittal.productservice.services;

import com.naveenmittal.productservice.dtos.GenericProductDto;
import com.naveenmittal.productservice.exceptions.NotFoundException;

import java.util.List;

public interface ProductService {
    GenericProductDto getProductById(Long id) throws NotFoundException;

    GenericProductDto createProduct(GenericProductDto productDto);

    List<GenericProductDto> getAllProducts();

    GenericProductDto deleteProductById(Long id);
}
