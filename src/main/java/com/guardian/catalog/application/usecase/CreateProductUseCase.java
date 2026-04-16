package com.guardian.catalog.application.usecase;

import com.guardian.catalog.infrastructure.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service

public class CreateProductUseCase  {
    private final ProductRepository productRepository;
    public CreateProductUseCase(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
}
