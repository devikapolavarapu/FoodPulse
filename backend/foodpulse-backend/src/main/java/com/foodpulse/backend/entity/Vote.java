package com.foodpulse.backend.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "votes")
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "student_id")
    private int studentId;

    @Column(name = "poll_id")
    private int pollId;

    @Column(name = "selected_option")
    private String selectedOption;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    public Vote(){}

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public int getStudentId() { return studentId; }

    public void setStudentId(int studentId) { this.studentId = studentId; }

    public int getPollId() { return pollId; }

    public void setPollId(int pollId) { this.pollId = pollId; }

    public String getSelectedOption() { return selectedOption; }

    public void setSelectedOption(String selectedOption) { this.selectedOption = selectedOption; }

    public LocalDateTime getCreatedAt() { return createdAt; }

    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}