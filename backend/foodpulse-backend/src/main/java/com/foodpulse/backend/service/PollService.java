package com.foodpulse.backend.service;

import com.foodpulse.backend.dto.LivePollDashboardDTO;
import com.foodpulse.backend.dto.PollDTO;

import java.util.List;

/**
 * Service interface for managing polls and live dashboards.
 */
public interface PollService {

    /**
     * Creates a new poll.
     *
     * @param pollDTO the poll details DTO
     * @return the created poll DTO
     */
    PollDTO createPoll(PollDTO pollDTO);

    /**
     * Retrieves all polls.
     *
     * @return list of polls
     */
    List<PollDTO> getAllPolls();

    /**
     * Retrieves the live poll dashboard for a specific poll.
     *
     * @param pollId the poll ID
     * @return the live poll dashboard details
     */
    LivePollDashboardDTO getLivePollDashboard(int pollId);
}
