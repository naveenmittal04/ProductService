package com.naveenmittal.productservice.services;

import com.naveenmittal.productservice.dtos.FakeStoreProductDto;
import com.naveenmittal.productservice.dtos.GenericProductDto;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService{

    private final RestTemplateBuilder restTemplateBuilder;
    private String fakeStoreApiUrl = "https://fakestoreapi.com/products";

    public FakeStoreProductService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }
    @Override
    public GenericProductDto getProductById(Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> responseEntity = restTemplate.getForEntity(fakeStoreApiUrl+"/"+id, FakeStoreProductDto.class);

        FakeStoreProductDto fakeStoreProductDto = responseEntity.getBody();
        GenericProductDto genericProductDto = new GenericProductDto();
        genericProductDto.setTitle(fakeStoreProductDto.getTitle());
        genericProductDto.setImage(fakeStoreProductDto.getImage());
        genericProductDto.setDescription(fakeStoreProductDto.getDescription());
        genericProductDto.setPrice(fakeStoreProductDto.getPrice());
        genericProductDto.setCategory(fakeStoreProductDto.getCategory());
        return genericProductDto;
    }
}
