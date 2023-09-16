package com.naveenmittal.productservice.thirdpartyclients.productservice.fakestore;

import com.naveenmittal.productservice.dtos.GenericProductDto;
import com.naveenmittal.productservice.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class FakeStoreProductServiceClient {
    private final RestTemplateBuilder restTemplateBuilder;

    private String fakeStoreApiUrl;
    private String productUrl;
    private String specificProductUrl;
    private String fakeStoreProductUrl;


    public FakeStoreProductServiceClient(
            RestTemplateBuilder restTemplateBuilder,
            @Value("${fakestore.api.url}") String fakeStoreApiUrl,
            @Value("${fakestore.api.paths.product}") String productUrl
    ) {
        this.restTemplateBuilder = restTemplateBuilder;
        this.fakeStoreApiUrl = fakeStoreApiUrl;
        this.productUrl = productUrl;
        this.fakeStoreProductUrl = fakeStoreApiUrl + productUrl;
        this.specificProductUrl = fakeStoreApiUrl + productUrl + "/{id}";
    }

    public FakeStoreProductDto getProductById(Long id) throws NotFoundException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> responseEntity = restTemplate.getForEntity(specificProductUrl, FakeStoreProductDto.class, id);

        FakeStoreProductDto fakeStoreProductDto = responseEntity.getBody();

        if(fakeStoreProductDto == null) {
            throw new NotFoundException("Product not found with id "+id);
        }
        return fakeStoreProductDto;
    }

    public FakeStoreProductDto createProduct(GenericProductDto productDto) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> responseEntity = restTemplate.postForEntity(
                fakeStoreProductUrl,
                productDto,
                FakeStoreProductDto.class
        );
        return responseEntity.getBody();
    }

    public List<FakeStoreProductDto> getAllProducts() throws NotFoundException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto[]> responseEntity = restTemplate.getForEntity(fakeStoreProductUrl, FakeStoreProductDto[].class);
        FakeStoreProductDto[] fakeStoreProductDtos = responseEntity.getBody();
        if(fakeStoreProductDtos == null){
            throw new NotFoundException("No products found");
        }
        return Arrays.stream(fakeStoreProductDtos).toList();
    }

    public FakeStoreProductDto deleteProductById(Long id) throws NotFoundException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(FakeStoreProductDto.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor = restTemplate.responseEntityExtractor(FakeStoreProductDto.class);
        ResponseEntity<FakeStoreProductDto> response =
                restTemplate.execute(specificProductUrl, HttpMethod.DELETE, requestCallback, responseExtractor, id);

        if(response == null){
            throw new NotFoundException("No products found");
        }
        return response.getBody();
    }
}
