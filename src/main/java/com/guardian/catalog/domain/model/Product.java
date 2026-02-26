package com.guardian.catalog.domain.model;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

public class Product {

    private UUID id;
    private String name;
    private BigDecimal price;
    private boolean active;
    private Instant createdAt;
    private Instant updatedAt;

    protected Product() {
        // for JPA
    }

    public Product(String name, BigDecimal price) {
        validateName(name);
        validatePrice(price);

        this.id = UUID.randomUUID();
        this.name = name;
        this.price = price;
        this.active = false;
        this.createdAt = Instant.now();
        this.updatedAt = Instant.now();
    }

    public void changePrice(BigDecimal newPrice) {
        validatePrice(newPrice);

        if (this.price.compareTo(newPrice) == 0) {
            return; // no real change
        }

        this.price = newPrice;
        this.updatedAt = Instant.now();
    }

    public void changeName(String newName) {
        validateName(newName);

        if (Objects.equals(this.name, newName)) {
            return;
        }

        this.name = newName;
        this.updatedAt = Instant.now();
    }

    public void activate() {
        if (!this.active) {
            this.active = true;
            this.updatedAt = Instant.now();
        }
    }

    public void deactivate() {
        if (this.active) {
            this.active = false;
            this.updatedAt = Instant.now();
        }
    }

    private void validateName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("name cannot be empty");
        }
    }

    private void validatePrice(BigDecimal price) {
        if (price == null || price.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("price cannot be negative");
        }
    }
}