package com.naveenmittal.productservice.services;

import com.naveenmittal.productservice.dtos.GenericProductDto;
import org.springframework.stereotype.Service;

@Service("selfProductService")
public class SelfProductService implements ProductService{
    @Override
    public GenericProductDto getProductById(Long id) {
        return null;
    }
}
