package com.naveenmittal.productservice.controllers;

import com.naveenmittal.productservice.dtos.GenericProductDto;
import com.naveenmittal.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    ProductService productService;

    public ProductController(@Qualifier("fakeStoreProductService") ProductService productService) {
        this.productService = productService;
    }
    @GetMapping
    public String getAllProducts() {
        return "All products";
    }

    @GetMapping("{id}")
    public GenericProductDto getProductById(@PathVariable("id") Long id) {
        return productService.getProductById(id);
    }

    @DeleteMapping("{id}")
    public String deleteProductById(@PathVariable("id") Long id) {
        return "Product deleted with id "+id;
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
