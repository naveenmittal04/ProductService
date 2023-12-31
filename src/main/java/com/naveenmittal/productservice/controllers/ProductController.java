package com.naveenmittal.productservice.controllers;

import com.naveenmittal.productservice.dtos.GenericProductDto;
import com.naveenmittal.productservice.exceptions.NotFoundException;
import com.naveenmittal.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    ProductService productService;

    public ProductController(@Qualifier("fakeStoreProductService") ProductService productService) {
        this.productService = productService;
    }
    @GetMapping
    public List<GenericProductDto> getAllProducts() throws NotFoundException {
        return productService.getAllProducts();
    }

    @GetMapping("{id}")
    public GenericProductDto getProductById(@PathVariable("id") Long id) throws NotFoundException {
        return productService.getProductById(id);
    }

    @DeleteMapping("{id}")
    public GenericProductDto deleteProductById(@PathVariable("id") Long id) throws NotFoundException {

        return productService.deleteProductById(id);
    }

    @PostMapping
    public GenericProductDto createProduct(@RequestBody GenericProductDto productDto) {
        return productService.createProduct(productDto);
    }

    @PutMapping("{id}")
    public String updateProductById(@PathVariable("id") Long id) {
        return "Product updated with id "+id;
    }
}
