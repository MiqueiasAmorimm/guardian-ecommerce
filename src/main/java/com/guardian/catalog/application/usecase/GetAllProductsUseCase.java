package com.guardian.catalog.application.usecase;

import com.guardian.catalog.domain.model.Product;
import com.guardian.catalog.infrastructure.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllProductsUseCase {
    private final ProductRepository productsRepository;
    public GetAllProductsUseCase(ProductRepository productRepository) {
        this.productsRepository = productRepository;
    }
    public List<Product> Execute() {
        return productsRepository.findAll();
    }
}
