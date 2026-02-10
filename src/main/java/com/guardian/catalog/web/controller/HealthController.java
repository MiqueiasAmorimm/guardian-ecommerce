package com.guardian.catalog.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
@RequestMapping("/api/v1")
public class HealthController {

    private static final Logger logger =
            LoggerFactory.getLogger(HealthController.class);

    @GetMapping("/health")
    public ResponseEntity<HealthResponse> health() {

        logger.debug("Health check requested");

        var response = new HealthResponse(
                "UP",
                Instant.now().toString(),
                "guardian-catalog"
        );

        return ResponseEntity.ok(response);
    }
}
