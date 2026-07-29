package com.foodpulse.backend.service;

import com.foodpulse.backend.dto.LivePollDashboardDTO;
import com.foodpulse.backend.dto.PollDTO;
import com.foodpulse.backend.entity.Poll;
import com.foodpulse.backend.exception.ResourceNotFoundException;
import com.foodpulse.backend.repository.PollRepository;
import com.foodpulse.backend.repository.VoteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Service implementation for Poll management and dashboards.
 */
@Service
public class PollServiceImpl implements PollService {

    private static final Logger log = LoggerFactory.getLogger(PollServiceImpl.class);

    private final PollRepository pollRepository;
    private final VoteRepository voteRepository;

    public PollServiceImpl(PollRepository pollRepository, VoteRepository voteRepository) {
        this.pollRepository = pollRepository;
        this.voteRepository = voteRepository;
    }

    @Override
    public PollDTO createPoll(PollDTO pollDTO) {
        log.info("Creating new poll: {}", pollDTO.getQuestion());

        Poll poll = new Poll();
        poll.setQuestion(pollDTO.getQuestion());
        poll.setOption1(pollDTO.getOption1());
        poll.setOption2(pollDTO.getOption2());
        poll.setOption3(pollDTO.getOption3());
        poll.setOption4(pollDTO.getOption4());

        Poll saved = pollRepository.save(poll);

        return new PollDTO(
                saved.getId(),
                saved.getQuestion(),
                saved.getOption1(),
                saved.getOption2(),
                saved.getOption3(),
                saved.getOption4()
        );
    }

    @Override
    public List<PollDTO> getAllPolls() {
        log.info("Fetching all polls");
        List<Poll> polls = pollRepository.findAll();
        List<PollDTO> dtos = new ArrayList<>();

        for (Poll poll : polls) {
            dtos.add(new PollDTO(
                    poll.getId(),
                    poll.getQuestion(),
                    poll.getOption1(),
                    poll.getOption2(),
                    poll.getOption3(),
                    poll.getOption4()
            ));
        }

        return dtos;
    }

    @Override
    public LivePollDashboardDTO getLivePollDashboard(int pollId) {
        log.info("Calculating live poll dashboard statistics for poll ID: {}", pollId);

        Poll poll = pollRepository.findById(pollId)
                .orElseThrow(() -> new ResourceNotFoundException("Poll not found with ID: " + pollId));

        List<Object[]> results = voteRepository.countVotesByOption(pollId);

        Map<String, Long> voteMap = new HashMap<>();
        long totalVotes = 0;
        String winner = "No votes yet";
        long maxVotes = 0;

        // Populate options with default 0 votes to be user friendly
        if (poll.getOption1() != null) voteMap.put(poll.getOption1(), 0L);
        if (poll.getOption2() != null) voteMap.put(poll.getOption2(), 0L);
        if (poll.getOption3() != null && !poll.getOption3().trim().isEmpty()) voteMap.put(poll.getOption3(), 0L);
        if (poll.getOption4() != null && !poll.getOption4().trim().isEmpty()) voteMap.put(poll.getOption4(), 0L);

        // Fill actual votes
        for (Object[] row : results) {
            String option = (String) row[0];
            Long count = (Long) row[1];
            if (option != null && count != null) {
                voteMap.put(option, count);
                totalVotes += count;

                if (count > maxVotes) {
                    maxVotes = count;
                    winner = option;
                }
            }
        }

        // Calculate percentage distribution
        Map<String, Double> percentageMap = new HashMap<>();
        for (Map.Entry<String, Long> entry : voteMap.entrySet()) {
            double percentage = 0.0;
            if (totalVotes > 0) {
                percentage = Math.round((entry.getValue() * 100.0 / totalVotes) * 10.0) / 10.0;
            }
            percentageMap.put(entry.getKey(), percentage);
        }

        return new LivePollDashboardDTO(
                pollId,
                poll.getQuestion(),
                winner,
                totalVotes,
                voteMap,
                percentageMap,
                LocalDateTime.now()
        );
    }
}
