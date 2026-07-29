package com.foodpulse.backend.controller;

import com.foodpulse.backend.dto.FeedbackDTO;
import com.foodpulse.backend.service.FeedbackService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller exposing CRUD endpoints for mess feedback.
 */
@RestController
@RequestMapping("/feedback")
@CrossOrigin
@Tag(name = "Feedback Controller", description = "Endpoints for student feedback CRUD operations")
public class FeedbackController {

    private final FeedbackService feedbackService;

    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    /**
     * Submits a new student feedback.
     *
     * @param feedbackDTO the feedback details
     * @return the saved feedback details
     */
    @PostMapping("/add")
    @Operation(summary = "Submit student feedback", description = "Adds a student rating (1-5) and comment. Verifies student exists first.")
    public ResponseEntity<FeedbackDTO> addFeedback(@Valid @RequestBody FeedbackDTO feedbackDTO) {
        FeedbackDTO created = feedbackService.addFeedback(feedbackDTO);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    /**
     * Retrieves all feedback records.
     *
     * @return list of feedback
     */
    @GetMapping("/all")
    @Operation(summary = "Get all feedback", description = "Retrieves a list of all feedback records in the system.")
    public ResponseEntity<List<FeedbackDTO>> getAllFeedback() {
        List<FeedbackDTO> feedbackList = feedbackService.getAllFeedback();
        return ResponseEntity.ok(feedbackList);
    }

    /**
     * Retrieves all feedback submitted by a specific student.
     *
     * @param id the student ID
     * @return list of feedback from the student
     */
    @GetMapping("/student/{id}")
    @Operation(summary = "Get feedback by student ID", description = "Retrieves all feedback records submitted by a specific student.")
    public ResponseEntity<List<FeedbackDTO>> getFeedbackByStudent(@PathVariable int id) {
        List<FeedbackDTO> feedbackList = feedbackService.getFeedbackByStudentId(id);
        return ResponseEntity.ok(feedbackList);
    }

    /**
     * Updates an existing feedback (rating and comment).
     *
     * @param id the feedback ID
     * @param feedbackDetailsDTO the updated feedback details
     * @return the updated feedback details
     */
    @PutMapping("/{id}")
    @Operation(summary = "Update feedback", description = "Updates the rating and comment of a specific feedback record.")
    public ResponseEntity<FeedbackDTO> updateFeedback(@PathVariable int id, @Valid @RequestBody FeedbackDTO feedbackDetailsDTO) {
        FeedbackDTO updated = feedbackService.updateFeedback(id, feedbackDetailsDTO);
        return ResponseEntity.ok(updated);
    }

    /**
     * Deletes a feedback record by its ID.
     *
     * @param id the feedback ID
     * @return response entity with no content status
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete feedback", description = "Deletes a specific feedback record by its ID.")
    public ResponseEntity<Void> deleteFeedback(@PathVariable int id) {
        feedbackService.deleteFeedback(id);
        return ResponseEntity.noContent().build();
    }
}
