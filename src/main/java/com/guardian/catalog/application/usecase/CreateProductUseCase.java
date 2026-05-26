package com.guardian.catalog.application.usecase;

import com.guardian.catalog.domain.model.Product;
import com.guardian.catalog.infrastructure.repository.ProductRepository;
import com.guardian.catalog.web.dto.CreateProductRequest;
import org.springframework.stereotype.Service;

@Service

public class CreateProductUseCase  {
    private final ProductRepository productRepository;
    public CreateProductUseCase(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    public Product execute (CreateProductRequest request) {
        Product product = new Product (request.getName(),
                request.getDescription(), request.getPrice());
        return productRepository.save(product);
    }
}