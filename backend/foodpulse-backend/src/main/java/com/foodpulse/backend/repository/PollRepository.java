package com.foodpulse.backend.repository;

import com.foodpulse.backend.entity.Poll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PollRepository extends JpaRepository<Poll,Integer> {

    @Query("SELECT COUNT(p) FROM Poll p")
    long countTotalPolls();
}