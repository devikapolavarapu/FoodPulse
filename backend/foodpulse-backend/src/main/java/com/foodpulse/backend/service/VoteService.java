package com.foodpulse.backend.service;

import com.foodpulse.backend.dto.VoteDTO;

import java.util.List;
import java.util.Map;

/**
 * Service interface for managing student votes.
 */
public interface VoteService {

    /**
     * Casts a vote. Prevents duplicates if the student has already voted in this poll.
     *
     * @param voteDTO the vote details
     * @return the cast vote DTO
     */
    VoteDTO castVote(VoteDTO voteDTO);

    /**
     * Retrieves all votes cast for a poll.
     *
     * @param pollId the poll ID
     * @return list of votes
     */
    List<VoteDTO> getVotesByPoll(int pollId);

    /**
     * Retrieves the count of votes per option for a poll.
     *
     * @param pollId the poll ID
     * @return map of options and vote counts
     */
    Map<String, Long> getVoteCount(int pollId);

    /**
     * Retrieves leaderboard containing winner option and vote count.
     *
     * @param pollId the poll ID
     * @return leaderboard map containing winner and votes count
     */
    Map<String, Object> getLeaderboard(int pollId);
}
