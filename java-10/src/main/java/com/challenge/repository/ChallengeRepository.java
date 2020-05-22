package com.challenge.repository;

import com.challenge.entity.Challenge;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ChallengeRepository extends CrudRepository<Challenge, Long> {
    @Query(value = "SELECT * FROM challenge\n" +
            "JOIN acceleration ON acceleration.challenge_id = challenge.id\n" +
            "JOIN candidate ON candidate.acceleration_id = acceleration.id\n" +
            "WHERE acceleration.id = ?1 AND candidate.user_id = ?2", nativeQuery = true)
    List<Challenge> findByAccelerationIdAndUserId(Long accelerationId, Long userId);
}
