package com.foodpulse.backend.controller;

import com.foodpulse.backend.dto.DashboardSummaryDTO;
import com.foodpulse.backend.service.DashboardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller exposing endpoints for mess dashboard statistics.
 */
@RestController
@RequestMapping("/dashboard")
@CrossOrigin
@Tag(name = "Dashboard Controller", description = "Endpoints for retrieving overall mess statistics and analytics")
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    /**
     * Retrieves overall mess statistics, counts, ratings, and top choice.
     *
     * @return overall mess summary analytics
     */
    @GetMapping("/summary")
    @Operation(summary = "Get overall dashboard summary", description = "Retrieves stats including total students, total polls, total votes, average rating, top choice, and today's votes.")
    public ResponseEntity<DashboardSummaryDTO> getSummary() {
        DashboardSummaryDTO summary = dashboardService.getDashboardSummary();
        return ResponseEntity.ok(summary);
    }
}
