package com.guardian.catalog.domain.model;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(nullable = false)
    private boolean active;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    protected Product() {
        // for JPA
    }

    public Product(String name, String description, BigDecimal price) {
        validateName(name);
        validateDescription(description);
        validatePrice(price);

        this.name = name;
        this.description = description;
        this.price = price;
        this.active = false;
        this.createdAt = Instant.now();
        this.updatedAt = Instant.now();
    }

    public void changePrice(BigDecimal newPrice) {
        validatePrice(newPrice);

        if (this.price.compareTo(newPrice) == 0) {
            return;
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

    private void validateDescription(String description) {
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("description cannot be empty");
        }
    }

    private void validatePrice(BigDecimal price) {
        if (price == null || price.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("price cannot be negative");
        }
    }
}