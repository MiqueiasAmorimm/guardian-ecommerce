        package com.guardian.catalog.application.usecase;

        import com.guardian.catalog.domain.model.Product;
        import com.guardian.catalog.infrastructure.repository.ProductRepository;
        import org.junit.jupiter.api.Test;
        import org.junit.jupiter.api.extension.ExtendWith;
        import org.mockito.InjectMocks;
        import org.mockito.Mock;
        import org.mockito.junit.jupiter.MockitoExtension;

        import java.math.BigDecimal;
        import java.util.Optional;
        import java.util.UUID;

        import static org.junit.jupiter.api.Assertions.assertEquals;
        import static org.junit.jupiter.api.Assertions.assertTrue;
        import static org.mockito.Mockito.when;

        @ExtendWith(MockitoExtension.class)
        public class GetProductByIdUseCaseTest {
            @Mock
            private ProductRepository productRepository;

            @InjectMocks
            private GetProductByIdUseCase getProductByIdUseCase;

            @Test
            void shouldReturnProductWhenIdExists() {
                UUID id = UUID.randomUUID();
                Product product = new Product("Tenis Nike", "Tenis esportivo masculino", new BigDecimal("299.90"));
                when(productRepository.findById(id)).thenReturn(Optional.of(product));

                Optional<Product> result = getProductByIdUseCase.execute(id);

                assertTrue(result.isPresent());
                assertEquals("Tenis Nike", result.get().getName());
            }
        }