package com.foodpulse.backend.repository;

import com.foodpulse.backend.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Repository interface for Feedback entity operations.
 */
public interface FeedbackRepository extends JpaRepository<Feedback, Integer> {

    /**
     * Finds all feedback submitted by a specific student using JPQL.
     *
     * @param studentId the student ID
     * @return list of feedback
     */
    @Query("SELECT f FROM Feedback f WHERE f.studentId = :studentId")
    List<Feedback> findByStudentIdCustom(@Param("studentId") int studentId);

    /**
     * Calculates the average rating of all mess feedback using JPQL.
     *
     * @return the average rating as a Double
     */
    @Query("SELECT AVG(f.rating) FROM Feedback f")
    Double getAverageRating();
}
