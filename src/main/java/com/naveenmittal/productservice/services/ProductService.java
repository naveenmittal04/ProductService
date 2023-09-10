package com.naveenmittal.productservice.services;

import com.naveenmittal.productservice.dtos.GenericProductDto;

public interface ProductService {
    GenericProductDto getProductById(Long id);
}
