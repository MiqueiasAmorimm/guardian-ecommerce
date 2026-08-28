package com.guardian.catalog.application.usecase;

import com.guardian.catalog.domain.model.Product;
import com.guardian.catalog.infrastructure.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetAllProductsUseCaseTest {
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private GetAllProductsUseCase getAllProductsUseCase;

    @Test
    void shouldReturnAllProducts() {
        List<Product> products = List.of(
                new Product("Tenis Nike", "Tenis esportivo masculino", new BigDecimal("299.90")),
                new Product("Camiseta Adidas", "Camiseta esportiva", new BigDecimal("149.90"))
        );
        when(productRepository.findAll()).thenReturn(products);

        List<Product> result = getAllProductsUseCase.execute();

        assertNotNull(result);
        assertEquals(2, result.size());
    }
}