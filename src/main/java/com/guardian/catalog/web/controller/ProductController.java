package com.guardian.catalog.web.controller;

import com.guardian.catalog.application.usecase.CreateProductUseCase;
import com.guardian.catalog.application.usecase.GetProductByIdUseCase;
import com.guardian.catalog.domain.model.Product;
import com.guardian.catalog.web.dto.CreateProductRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping ("/products")
public class ProductController {
    private final CreateProductUseCase createProductUseCase;
    private final GetProductByIdUseCase getProductByIdUseCase;

    public ProductController(CreateProductUseCase createProductUseCase,
                             GetProductByIdUseCase getProductByIdUseCase) {
        this.createProductUseCase = createProductUseCase;
        this.getProductByIdUseCase = getProductByIdUseCase;
    }

    @PostMapping
    public ResponseEntity<Product> create(@Valid @RequestBody CreateProductRequest request) {
        Product product = createProductUseCase.execute(request);
        return ResponseEntity.status(201).body(product);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(@PathVariable UUID id) {
        Optional<Product> product = getProductByIdUseCase.execute(id);
        return product.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}

