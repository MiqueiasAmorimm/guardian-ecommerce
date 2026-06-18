package com.guardian.catalog.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class CreateProductRequest {
    @NotBlank
    private String name;

    @NotBlank
    private String description;
    @NotNull
    @PositiveOrZero

    private BigDecimal price;

    private Boolean active;
}
