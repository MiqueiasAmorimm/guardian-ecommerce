package com.guardian.catalog.application.dto;

public record HealthResponse(
        String status,
        String timestamp,
        String service
) {}
