package com.foodpulse.backend.service;

import com.foodpulse.backend.dto.FeedbackDTO;
import com.foodpulse.backend.entity.Feedback;
import com.foodpulse.backend.exception.ResourceNotFoundException;
import com.foodpulse.backend.repository.FeedbackRepository;
import com.foodpulse.backend.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Service implementation for Feedback operations.
 */
@Service
public class FeedbackServiceImpl implements FeedbackService {

    private static final Logger log = LoggerFactory.getLogger(FeedbackServiceImpl.class);

    private final FeedbackRepository feedbackRepository;
    private final StudentRepository studentRepository;

    public FeedbackServiceImpl(FeedbackRepository feedbackRepository, StudentRepository studentRepository) {
        this.feedbackRepository = feedbackRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public FeedbackDTO addFeedback(FeedbackDTO feedbackDTO) {
        log.info("Creating feedback for student ID: {}", feedbackDTO.getStudentId());

        if (!studentRepository.existsById(feedbackDTO.getStudentId())) {
            log.warn("Student ID {} not found. Feedback submission rejected.", feedbackDTO.getStudentId());
            throw new ResourceNotFoundException("Student not found with ID: " + feedbackDTO.getStudentId());
        }

        Feedback feedback = new Feedback(
                feedbackDTO.getStudentId(),
                feedbackDTO.getRating(),
                feedbackDTO.getComment()
        );

        Feedback saved = feedbackRepository.save(feedback);
        log.info("Feedback successfully submitted with ID: {}", saved.getId());

        return new FeedbackDTO(
                saved.getId(),
                saved.getStudentId(),
                saved.getRating(),
                saved.getComment(),
                saved.getCreatedAt()
        );
    }

    @Override
    public List<FeedbackDTO> getAllFeedback() {
        log.info("Fetching all feedbacks");
        List<Feedback> feedbacks = feedbackRepository.findAll();
        List<FeedbackDTO> dtos = new ArrayList<>();

        for (Feedback feedback : feedbacks) {
            dtos.add(new FeedbackDTO(
                    feedback.getId(),
                    feedback.getStudentId(),
                    feedback.getRating(),
                    feedback.getComment(),
                    feedback.getCreatedAt()
            ));
        }

        return dtos;
    }

    @Override
    public List<FeedbackDTO> getFeedbackByStudentId(int studentId) {
        log.info("Fetching feedback for student ID: {}", studentId);
        List<Feedback> feedbacks = feedbackRepository.findByStudentIdCustom(studentId);
        List<FeedbackDTO> dtos = new ArrayList<>();

        for (Feedback feedback : feedbacks) {
            dtos.add(new FeedbackDTO(
                    feedback.getId(),
                    feedback.getStudentId(),
                    feedback.getRating(),
                    feedback.getComment(),
                    feedback.getCreatedAt()
            ));
        }

        return dtos;
    }

    @Override
    public FeedbackDTO updateFeedback(int id, FeedbackDTO feedbackDetailsDTO) {
        log.info("Updating feedback ID: {}", id);
        Feedback feedback = feedbackRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Feedback not found with ID: " + id));

        feedback.setRating(feedbackDetailsDTO.getRating());
        feedback.setComment(feedbackDetailsDTO.getComment());

        Feedback saved = feedbackRepository.save(feedback);
        log.info("Feedback ID {} updated successfully", id);

        return new FeedbackDTO(
                saved.getId(),
                saved.getStudentId(),
                saved.getRating(),
                saved.getComment(),
                saved.getCreatedAt()
        );
    }

    @Override
    public void deleteFeedback(int id) {
        log.info("Deleting feedback ID: {}", id);
        if (!feedbackRepository.existsById(id)) {
            log.warn("Feedback ID {} not found for deletion", id);
            throw new ResourceNotFoundException("Feedback not found with ID: " + id);
        }
        feedbackRepository.deleteById(id);
        log.info("Feedback ID {} deleted successfully", id);
    }
}
