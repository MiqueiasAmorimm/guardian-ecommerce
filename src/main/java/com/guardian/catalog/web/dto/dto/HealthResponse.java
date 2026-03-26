package com.guardian.catalog.web.dto.dto;

public record HealthResponse(
        String status,
        String timestamp,
        String service
) {}
