package com.foodpulse.backend.service;

import com.foodpulse.backend.dto.VoteDTO;
import com.foodpulse.backend.entity.Vote;
import com.foodpulse.backend.repository.VoteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Service implementation for Vote operations.
 */
@Service
public class VoteServiceImpl implements VoteService {

    private static final Logger log = LoggerFactory.getLogger(VoteServiceImpl.class);

    private final VoteRepository voteRepository;

    public VoteServiceImpl(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }

    @Override
    public VoteDTO castVote(VoteDTO voteDTO) {
        log.info("Casting vote for student: {} in poll: {}", voteDTO.getStudentId(), voteDTO.getPollId());

        Optional<Vote> existingVote = voteRepository.findByStudentIdAndPollId(
                voteDTO.getStudentId(),
                voteDTO.getPollId()
        );

        if (existingVote.isPresent()) {
            log.warn("Student {} has already voted in poll {}", voteDTO.getStudentId(), voteDTO.getPollId());
            throw new IllegalArgumentException("Student already voted for this poll");
        }

        Vote vote = new Vote();
        vote.setStudentId(voteDTO.getStudentId());
        vote.setPollId(voteDTO.getPollId());
        vote.setSelectedOption(voteDTO.getSelectedOption());
        vote.setCreatedAt(LocalDateTime.now());

        Vote saved = voteRepository.save(vote);
        log.info("Vote cast successfully with ID: {}", saved.getId());

        return new VoteDTO(
                saved.getId(),
                saved.getStudentId(),
                saved.getPollId(),
                saved.getSelectedOption(),
                saved.getCreatedAt()
        );
    }

    @Override
    public List<VoteDTO> getVotesByPoll(int pollId) {
        log.info("Fetching raw votes for poll ID: {}", pollId);
        List<Vote> votes = voteRepository.findByPollId(pollId);
        List<VoteDTO> dtos = new ArrayList<>();

        for (Vote vote : votes) {
            dtos.add(new VoteDTO(
                    vote.getId(),
                    vote.getStudentId(),
                    vote.getPollId(),
                    vote.getSelectedOption(),
                    vote.getCreatedAt()
            ));
        }

        return dtos;
    }

    @Override
    public Map<String, Long> getVoteCount(int pollId) {
        log.info("Fetching vote counts for poll ID: {}", pollId);
        List<Object[]> results = voteRepository.countVotesByOption(pollId);
        Map<String, Long> voteCount = new HashMap<>();

        for (Object[] row : results) {
            String option = (String) row[0];
            Long count = (Long) row[1];
            if (option != null && count != null) {
                voteCount.put(option, count);
            }
        }

        return voteCount;
    }

    @Override
    public Map<String, Object> getLeaderboard(int pollId) {
        log.info("Fetching leaderboard for poll ID: {}", pollId);
        List<Object[]> results = voteRepository.countVotesByOption(pollId);

        String winner = "No votes yet";
        long maxVotes = 0;

        for (Object[] row : results) {
            String option = (String) row[0];
            Long count = (Long) row[1];
            if (option != null && count != null && count > maxVotes) {
                maxVotes = count;
                winner = option;
            }
        }

        Map<String, Object> leaderboard = new HashMap<>();
        leaderboard.put("winner", winner);
        leaderboard.put("votes", maxVotes);

        return leaderboard;
    }
}
