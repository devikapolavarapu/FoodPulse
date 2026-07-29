package com.foodpulse.backend.service;

import com.foodpulse.backend.dto.FeedbackDTO;

import java.util.List;

/**
 * Service interface for managing feedback operations.
 */
public interface FeedbackService {

    /**
     * Submits new feedback. Verifies that the student exists before saving.
     *
     * @param feedbackDTO the feedback details
     * @return the saved feedback details
     */
    FeedbackDTO addFeedback(FeedbackDTO feedbackDTO);

    /**
     * Retrieves all feedback.
     *
     * @return list of feedback
     */
    List<FeedbackDTO> getAllFeedback();

    /**
     * Retrieves all feedback submitted by a specific student.
     *
     * @param studentId the student ID
     * @return list of feedback from the student
     */
    List<FeedbackDTO> getFeedbackByStudentId(int studentId);

    /**
     * Updates an existing feedback (rating and comment).
     *
     * @param id the feedback ID
     * @param feedbackDetailsDTO the updated feedback details
     * @return the updated feedback details
     */
    FeedbackDTO updateFeedback(int id, FeedbackDTO feedbackDetailsDTO);

    /**
     * Deletes a feedback record.
     *
     * @param id the feedback ID
     */
    void deleteFeedback(int id);
}
