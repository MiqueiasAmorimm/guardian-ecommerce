package com.guardian.catalog.application.usecase;

import com.guardian.catalog.domain.model.Product;
import com.guardian.catalog.infrastructure.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service

public class GetProductByIdUseCase {
    private final ProductRepository productRepository;

    public GetProductByIdUseCase(ProductRepository productRepository) {
        this.productRepository = productRepository;

    }

    public Optional<Product> execute (UUID id) {
     return productRepository.findById(id);
    }
}

