package com.foodpulse.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * Data Transfer Object for casting or retrieving votes.
 */
public class VoteDTO {

    private int id;

    @NotNull(message = "Student ID is required")
    private Integer studentId;

    @NotNull(message = "Poll ID is required")
    private Integer pollId;

    @NotBlank(message = "Selected option is required")
    private String selectedOption;

    private LocalDateTime createdAt;

    public VoteDTO() {}

    public VoteDTO(int id, Integer studentId, Integer pollId, String selectedOption, LocalDateTime createdAt) {
        this.id = id;
        this.studentId = studentId;
        this.pollId = pollId;
        this.selectedOption = selectedOption;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getPollId() {
        return pollId;
    }

    public void setPollId(Integer pollId) {
        this.pollId = pollId;
    }

    public String getSelectedOption() {
        return selectedOption;
    }

    public void setSelectedOption(String selectedOption) {
        this.selectedOption = selectedOption;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
