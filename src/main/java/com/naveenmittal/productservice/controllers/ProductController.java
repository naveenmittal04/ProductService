package com.naveenmittal.productservice.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    @GetMapping
    public String getAllProducts() {
        return "All products";
    }

    @GetMapping("{id}")
    public String getProductById(@PathVariable("id") Long id) {
        return "Product by id "+id;
    }

    @DeleteMapping("{id}")
    public String deleteProductById(@PathVariable("id") Long id) {
        return "Product deleted with id "+id;
    }

    @PostMapping
    public String createProduct() {
        return "Product created";
    }

    @PutMapping("{id}")
    public String updateProductById(@PathVariable("id") Long id) {
        return "Product updated with id "+id;
    }
}
