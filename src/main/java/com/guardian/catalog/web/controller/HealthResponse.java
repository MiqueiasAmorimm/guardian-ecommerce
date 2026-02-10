package com.guardian.catalog.web.controller;

public record HealthResponse(
        String status,
        String timestamp,
        String service
) {}
