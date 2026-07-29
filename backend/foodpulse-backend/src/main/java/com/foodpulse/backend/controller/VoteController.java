package com.foodpulse.backend.controller;

import com.foodpulse.backend.dto.VoteDTO;
import com.foodpulse.backend.service.VoteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Controller exposing endpoints for voting operations.
 */
@RestController
@RequestMapping("/votes")
@CrossOrigin
@Tag(name = "Vote Controller", description = "Endpoints for casting votes and retrieving voting analytics")
public class VoteController {

    private final VoteService voteService;

    public VoteController(VoteService voteService) {
        this.voteService = voteService;
    }

    /**
     * Casts a student vote for a particular option in a poll.
     *
     * @param voteDTO the vote details DTO
     * @return the saved vote details
     */
    @PostMapping("/cast")
    @Operation(summary = "Cast a vote", description = "Casts a student vote for a poll option. Prevents duplicate votes per student per poll.")
    public ResponseEntity<VoteDTO> castVote(@Valid @RequestBody VoteDTO voteDTO) {
        VoteDTO savedVote = voteService.castVote(voteDTO);
        return new ResponseEntity<>(savedVote, HttpStatus.CREATED);
    }

    /**
     * Retrieves the raw list of votes cast for a poll.
     *
     * @param pollId the poll ID
     * @return list of votes
     */
    @GetMapping("/results/{pollId}")
    @Operation(summary = "Get raw votes for a poll", description = "Retrieves a list of all raw vote records cast for a poll.")
    public ResponseEntity<List<VoteDTO>> getVotes(@PathVariable int pollId) {
        List<VoteDTO> votes = voteService.getVotesByPoll(pollId);
        return ResponseEntity.ok(votes);
    }

    /**
     * Retrieves the count of votes grouped by option for a poll.
     *
     * @param pollId the poll ID
     * @return map of options and vote counts
     */
    @GetMapping("/count/{pollId}")
    @Operation(summary = "Get vote counts grouped by option", description = "Retrieves the count of votes per option for a poll.")
    public ResponseEntity<Map<String, Long>> getVoteCount(@PathVariable int pollId) {
        Map<String, Long> voteCount = voteService.getVoteCount(pollId);
        return ResponseEntity.ok(voteCount);
    }

    /**
     * Retrieves the winner of a poll.
     *
     * @param pollId the poll ID
     * @return leaderboard map containing the winning option and its votes count
     */
    @GetMapping("/leaderboard/{pollId}")
    @Operation(summary = "Get leaderboard / winner of a poll", description = "Retrieves the winning option name and its vote count.")
    public ResponseEntity<Map<String, Object>> getLeaderboard(@PathVariable int pollId) {
        Map<String, Object> leaderboard = voteService.getLeaderboard(pollId);
        return ResponseEntity.ok(leaderboard);
    }
}