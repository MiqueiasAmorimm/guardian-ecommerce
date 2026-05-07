package com.guardian.catalog.web.controller;

import com.guardian.catalog.application.usecase.CreateProductUseCase;
import com.guardian.catalog.domain.model.Product;
import com.guardian.catalog.web.dto.CreateProductRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final CreateProductUseCase createProductUseCase;

    public ProductController(CreateProductUseCase createProductUseCase) {
        this.createProductUseCase = createProductUseCase;
    }

    @PostMapping
    public Product create(@RequestBody CreateProductRequest request) {
        return createProductUseCase.execute(request);
    }
}