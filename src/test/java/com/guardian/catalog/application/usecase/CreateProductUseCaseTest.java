
package com.guardian.catalog.application.usecase;


import com.guardian.catalog.domain.model.Product;
import com.guardian.catalog.infrastructure.repository.ProductRepository;
import com.guardian.catalog.web.dto.CreateProductRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CreateProductUseCaseTest {
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private CreateProductUseCase createProductUseCase;

    @Test
    void shouldCreateProductSuccessfully() {
        CreateProductRequest request = new CreateProductRequest (
                "Tenis Nike",
                "Tenis esportivo masculino",
                new BigDecimal("299.90"),
                null
        );

        Product saveProduct = new Product(
                request.getName(),
                request.getDescription(),
                request.getPrice()

        );
        when(productRepository.save(any(Product.class))).thenReturn(saveProduct);
        Product result = createProductUseCase.execute(request);
        assertNotNull(result);
        assertEquals("Tenis Nike", result.getName());
        assertEquals("Tenis esportivo masculino", result.getDescription());
    }

}
