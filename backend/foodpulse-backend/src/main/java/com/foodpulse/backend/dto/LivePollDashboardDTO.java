package com.foodpulse.backend.dto;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * Data Transfer Object for the Live Poll Dashboard.
 */
public class LivePollDashboardDTO {

    private int pollId;
    private String question;
    private String winner;
    private long totalVotes;
    private Map<String, Long> results;
    private Map<String, Double> percentage;
    private LocalDateTime lastUpdated;

    public LivePollDashboardDTO() {}

    public LivePollDashboardDTO(int pollId, String question, String winner, long totalVotes,
                                Map<String, Long> results, Map<String, Double> percentage, LocalDateTime lastUpdated) {
        this.pollId = pollId;
        this.question = question;
        this.winner = winner;
        this.totalVotes = totalVotes;
        this.results = results;
        this.percentage = percentage;
        this.lastUpdated = lastUpdated;
    }

    public int getPollId() {
        return pollId;
    }

    public void setPollId(int pollId) {
        this.pollId = pollId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public long getTotalVotes() {
        return totalVotes;
    }

    public void setTotalVotes(long totalVotes) {
        this.totalVotes = totalVotes;
    }

    public Map<String, Long> getResults() {
        return results;
    }

    public void setResults(Map<String, Long> results) {
        this.results = results;
    }

    public Map<String, Double> getPercentage() {
        return percentage;
    }

    public void setPercentage(Map<String, Double> percentage) {
        this.percentage = percentage;
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}
