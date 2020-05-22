package com.challenge.repository;

import com.challenge.entity.Submission;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.math.BigDecimal;
import java.util.List;

public interface SubmissionRepository extends CrudRepository<Submission, Long> {
    @Query(value = "SELECT MAX (score) FROM submission\n" +
            "JOIN challenge ON challenge.id = submission.challenge_id\n" +
            "WHERE challenge.id = ?1", nativeQuery = true)
    BigDecimal findHigherScoreByChallengeId(Long challengeId);

    @Query(value = "SELECT * FROM submission\n" +
            "JOIN challenge ON challenge.id = submission.challenge_id\n" +
            "JOIN acceleration ON acceleration.challenge_id = challenge.id\n" +
            "WHERE challenge.id = ?1 AND acceleration.id = ?2", nativeQuery = true)
    List<Submission> findByChallengeIdAndAccelerationId(Long challengeId, Long accelerationId);
}
