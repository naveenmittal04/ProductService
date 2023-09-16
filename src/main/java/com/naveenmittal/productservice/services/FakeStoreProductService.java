package com.naveenmittal.productservice.services;

import com.naveenmittal.productservice.dtos.GenericProductDto;
import com.naveenmittal.productservice.exceptions.NotFoundException;
import com.naveenmittal.productservice.thirdpartyclients.productservice.fakestore.FakeStoreProductDto;
import com.naveenmittal.productservice.thirdpartyclients.productservice.fakestore.FakeStoreProductServiceClient;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Primary
@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService {

    private final FakeStoreProductServiceClient fakeStoreProductServiceClient;

    public FakeStoreProductService(FakeStoreProductServiceClient fakeStoreProductServiceClient) {
        this.fakeStoreProductServiceClient = fakeStoreProductServiceClient;
    }

    private GenericProductDto convertFakeToGenericDto(FakeStoreProductDto fakeStoreProductDto){
        GenericProductDto genericProductDto = new GenericProductDto();
        genericProductDto.setTitle(fakeStoreProductDto.getTitle());
        genericProductDto.setImage(fakeStoreProductDto.getImage());
        genericProductDto.setDescription(fakeStoreProductDto.getDescription());
        genericProductDto.setPrice(fakeStoreProductDto.getPrice());
        genericProductDto.setCategory(fakeStoreProductDto.getCategory());
        return genericProductDto;
    }

    @Override
    public GenericProductDto getProductById(Long id) throws NotFoundException {
        FakeStoreProductDto fakeStoreProductDto = fakeStoreProductServiceClient.getProductById(id);

        if(fakeStoreProductDto == null) {
            throw new NotFoundException("Product not found with id "+id);
        }

        return convertFakeToGenericDto(fakeStoreProductDto);
    }

    @Override
    public GenericProductDto createProduct(GenericProductDto productDto) {
        return convertFakeToGenericDto(fakeStoreProductServiceClient.createProduct(productDto));
    }

    @Override
    public List<GenericProductDto> getAllProducts() throws NotFoundException {
        List<FakeStoreProductDto> fakeStoreProductDtos = fakeStoreProductServiceClient.getAllProducts();
        List<GenericProductDto> response = new ArrayList<>();
        for (FakeStoreProductDto fakeStoreProductDto : fakeStoreProductDtos) {
            response.add(convertFakeToGenericDto(fakeStoreProductDto));
        }
        return response;
    }

    @Override
    public GenericProductDto deleteProductById(Long id) throws NotFoundException {
        return convertFakeToGenericDto(fakeStoreProductServiceClient.deleteProductById(id));
    }

}
