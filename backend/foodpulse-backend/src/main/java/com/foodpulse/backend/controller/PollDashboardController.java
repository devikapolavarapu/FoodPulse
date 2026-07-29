package com.foodpulse.backend.controller;

import com.foodpulse.backend.dto.LivePollDashboardDTO;
import com.foodpulse.backend.service.PollService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for retrieving real-time live poll results.
 */
@RestController
@RequestMapping("/polls")
@CrossOrigin
@Tag(name = "Poll Dashboard Controller", description = "Endpoints for retrieving live poll results and statistics")
public class PollDashboardController {

    private final PollService pollService;

    public PollDashboardController(PollService pollService) {
        this.pollService = pollService;
    }

    /**
     * Retrieves the live poll statistics, including counts and percentages.
     *
     * @param pollId the poll ID
     * @return live poll dashboard statistics
     */
    @GetMapping("/dashboard/{pollId}")
    @Operation(summary = "Get live poll dashboard stats", description = "Retrieves real-time vote count, percentage distribution, winner, and last updated time for a poll.")
    public ResponseEntity<LivePollDashboardDTO> getDashboard(@PathVariable int pollId) {
        LivePollDashboardDTO dashboard = pollService.getLivePollDashboard(pollId);
        return ResponseEntity.ok(dashboard);
    }
}