package com.foodpulse.backend.repository;

import com.foodpulse.backend.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface VoteRepository extends JpaRepository<Vote, Integer> {

    List<Vote> findByPollId(int pollId);

    Optional<Vote> findByStudentIdAndPollId(int studentId, int pollId);

    @Query("SELECT v.selectedOption, COUNT(v) FROM Vote v WHERE v.pollId = :pollId GROUP BY v.selectedOption")
    List<Object[]> countVotesByOption(@Param("pollId") int pollId);

    @Query("SELECT COUNT(v) FROM Vote v")
    long countTotalVotes();

    @Query("SELECT v.selectedOption FROM Vote v GROUP BY v.selectedOption ORDER BY COUNT(v) DESC")
    List<String> findTopMenuChoices();

    @Query("SELECT COUNT(v) FROM Vote v WHERE v.createdAt >= :startOfDay")
    long countVotesToday(@Param("startOfDay") LocalDateTime startOfDay);

}