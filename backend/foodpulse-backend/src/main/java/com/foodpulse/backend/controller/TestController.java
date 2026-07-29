package com.foodpulse.backend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller exposing simple sanity and health check endpoints.
 */
@RestController
@Tag(name = "Sanity Controller", description = "Endpoints for backend health check")
public class TestController {

    /**
     * Home endpoint for basic sanity verification.
     *
     * @return message indicating backend is running successfully
     */
    @GetMapping("/")
    @Operation(summary = "Health Check / Home", description = "Verifies that the FoodPulse backend service is up and running.")
    public ResponseEntity<String> home() {
        return ResponseEntity.ok("FoodPulse Backend Running Successfully 🚀");
    }
}