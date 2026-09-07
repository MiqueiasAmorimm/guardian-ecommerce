package com.guardian.catalog.application.usecase;

import com.guardian.catalog.domain.model.Product;
import com.guardian.catalog.infrastructure.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class GetAllProductsUseCase {
    private final ProductRepository productsRepository;
    public GetAllProductsUseCase(ProductRepository productRepository) {
        this.productsRepository = productRepository;
    }
    public Page<Product> execute(Pageable pageable) {
        return productsRepository.findAll(pageable);
    }
}
