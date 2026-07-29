package com.foodpulse.backend.dto;

/**
 * Data Transfer Object for overall Hostel Mess Dashboard Summary.
 */
public class DashboardSummaryDTO {

    private long totalStudents;
    private long totalPolls;
    private long totalVotes;
    private double averageRating;
    private String topMenuChoice;
    private long todayVotes;

    public DashboardSummaryDTO() {}

    public DashboardSummaryDTO(long totalStudents, long totalPolls, long totalVotes,
                               double averageRating, String topMenuChoice, long todayVotes) {
        this.totalStudents = totalStudents;
        this.totalPolls = totalPolls;
        this.totalVotes = totalVotes;
        this.averageRating = averageRating;
        this.topMenuChoice = topMenuChoice;
        this.todayVotes = todayVotes;
    }

    public long getTotalStudents() {
        return totalStudents;
    }

    public void setTotalStudents(long totalStudents) {
        this.totalStudents = totalStudents;
    }

    public long getTotalPolls() {
        return totalPolls;
    }

    public void setTotalPolls(long totalPolls) {
        this.totalPolls = totalPolls;
    }

    public long getTotalVotes() {
        return totalVotes;
    }

    public void setTotalVotes(long totalVotes) {
        this.totalVotes = totalVotes;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    public String getTopMenuChoice() {
        return topMenuChoice;
    }

    public void setTopMenuChoice(String topMenuChoice) {
        this.topMenuChoice = topMenuChoice;
    }

    public long getTodayVotes() {
        return todayVotes;
    }

    public void setTodayVotes(long todayVotes) {
        this.todayVotes = todayVotes;
    }
}
