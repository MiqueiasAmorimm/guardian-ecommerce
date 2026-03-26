package com.guardian.catalog.domain;

import com.guardian.catalog.domain.model.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    @Test
    @DisplayName("should create product successfully when valid data is provided")
    void shouldCreateProductSuccessfully() {
        Product product = new Product(
                "Laptop",
                "High performance laptop",
                BigDecimal.valueOf(1000)
        );

        assertNotNull(product);
    }

    @Test
    @DisplayName("should throw exception when name is null")
    void shouldThrowExceptionWhenNameIsNull() {
        assertThrows(IllegalArgumentException.class, () ->
                new Product(null, "Valid description", BigDecimal.TEN)
        );
    }

    @Test
    @DisplayName("should throw exception when name is blank")
    void shouldThrowExceptionWhenNameIsBlank() {
        assertThrows(IllegalArgumentException.class, () ->
                new Product("   ", "Valid description", BigDecimal.TEN)
        );
    }

    @Test
    @DisplayName("should throw exception when price is negative")
    void shouldThrowExceptionWhenPriceIsNegative() {
        assertThrows(IllegalArgumentException.class, () ->
                new Product("TV", "Smart TV 50 inches", BigDecimal.valueOf(-1))
        );
    }

    @Test
    @DisplayName("should change price successfully")
    void shouldChangePriceSuccessfully() {
        Product product = new Product(
                "TV",
                "Smart TV 50 inches",
                BigDecimal.TEN
        );

        product.changePrice(BigDecimal.valueOf(20));

        assertDoesNotThrow(() ->
                product.changePrice(BigDecimal.valueOf(30))
        );
    }

    @Test
    @DisplayName("should throw exception when changing price to negative")
    void shouldThrowExceptionWhenChangingPriceToNegative() {
        Product product = new Product(
                "TV",
                "Smart TV 50 inches",
                BigDecimal.TEN
        );

        assertThrows(IllegalArgumentException.class, () ->
                product.changePrice(BigDecimal.valueOf(-5))
        );
    }
}