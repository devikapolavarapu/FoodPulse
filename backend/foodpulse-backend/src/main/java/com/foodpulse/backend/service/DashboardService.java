package com.foodpulse.backend.service;

import com.foodpulse.backend.dto.DashboardSummaryDTO;

/**
 * Service interface for general dashboard statistics.
 */
public interface DashboardService {

    /**
     * Calculates and returns the summary statistics of the Hostel Mess.
     *
     * @return the dashboard summary details
     */
    DashboardSummaryDTO getDashboardSummary();
}
