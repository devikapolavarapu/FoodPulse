package com.foodpulse.backend.controller;

import com.foodpulse.backend.dto.PollDTO;
import com.foodpulse.backend.service.PollService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller exposing endpoints for creating and viewing polls.
 */
@RestController
@RequestMapping("/polls")
@CrossOrigin
@Tag(name = "Poll Controller", description = "Endpoints for creating and viewing polls")
public class PollController {

    private final PollService pollService;

    public PollController(PollService pollService) {
        this.pollService = pollService;
    }

    /**
     * Creates a new mess feedback/voting poll.
     *
     * @param pollDTO the poll details DTO
     * @return the created poll details
     */
    @PostMapping("/create")
    @Operation(summary = "Create a new poll", description = "Creates a new poll with up to 4 options.")
    public ResponseEntity<PollDTO> createPoll(@Valid @RequestBody PollDTO pollDTO) {
        PollDTO created = pollService.createPoll(pollDTO);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    /**
     * Retrieves all polls created in the system.
     *
     * @return list of polls
     */
    @GetMapping("/all")
    @Operation(summary = "Get all polls", description = "Retrieves a list of all mess polls.")
    public ResponseEntity<List<PollDTO>> getPolls() {
        List<PollDTO> polls = pollService.getAllPolls();
        return ResponseEntity.ok(polls);
    }
}