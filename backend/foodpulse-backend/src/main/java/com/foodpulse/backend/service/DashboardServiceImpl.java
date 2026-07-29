package com.foodpulse.backend.service;

import com.foodpulse.backend.dto.DashboardSummaryDTO;
import com.foodpulse.backend.repository.FeedbackRepository;
import com.foodpulse.backend.repository.PollRepository;
import com.foodpulse.backend.repository.StudentRepository;
import com.foodpulse.backend.repository.VoteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Service implementation for overall mess analytics.
 */
@Service
public class DashboardServiceImpl implements DashboardService {

    private static final Logger log = LoggerFactory.getLogger(DashboardServiceImpl.class);

    private final StudentRepository studentRepository;
    private final PollRepository pollRepository;
    private final VoteRepository voteRepository;
    private final FeedbackRepository feedbackRepository;

    public DashboardServiceImpl(StudentRepository studentRepository, PollRepository pollRepository,
                                VoteRepository voteRepository, FeedbackRepository feedbackRepository) {
        this.studentRepository = studentRepository;
        this.pollRepository = pollRepository;
        this.voteRepository = voteRepository;
        this.feedbackRepository = feedbackRepository;
    }

    @Override
    public DashboardSummaryDTO getDashboardSummary() {
        log.info("Calculating overall mess dashboard summary statistics");

        long totalStudents = studentRepository.countTotalStudents();
        long totalPolls = pollRepository.countTotalPolls();
        long totalVotes = voteRepository.countTotalVotes();

        Double avg = feedbackRepository.getAverageRating();
        double averageRating = avg != null ? Math.round(avg * 10.0) / 10.0 : 0.0;

        List<String> topChoices = voteRepository.findTopMenuChoices();
        String topMenuChoice = (topChoices != null && !topChoices.isEmpty()) ? topChoices.get(0) : "N/A";

        LocalDateTime startOfDay = LocalDateTime.now().toLocalDate().atStartOfDay();
        long todayVotes = voteRepository.countVotesToday(startOfDay);

        log.info("Dashboard stats computed successfully: Students={}, Polls={}, Votes={}, Rating={}, TopMenu={}, TodayVotes={}",
                totalStudents, totalPolls, totalVotes, averageRating, topMenuChoice, todayVotes);

        return new DashboardSummaryDTO(
                totalStudents,
                totalPolls,
                totalVotes,
                averageRating,
                topMenuChoice,
                todayVotes
        );
    }
}
