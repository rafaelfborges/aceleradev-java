package com.challenge.repository;

import com.challenge.entity.Candidate;
import com.challenge.entity.CandidateId;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CandidateRepository extends CrudRepository<Candidate, Long> {

    Optional<Candidate> findById(CandidateId id);

    @Query(value = "SELECT * FROM candidate\n" +
            "WHERE candidate.user_id = ?1 " +
            "AND candidate.company_id = ?2 " +
            "AND candidate.acceleration_id = ?3", nativeQuery = true)
    Optional<Candidate> findById(Long userId, Long companyId, Long accelerationId);

    @Query(value = "SELECT * FROM candidate\n" +
            "JOIN company ON company.id = candidate.company_id\n" +
            "WHERE company.id = ?1", nativeQuery = true)
    List<Candidate> findByCompanyId(Long companyId);

    @Query(value = "SELECT * FROM candidate\n" +
            "JOIN acceleration ON acceleration.id = candidate.acceleration_id\n" +
            "WHERE acceleration.id = ?1", nativeQuery = true)
    List<Candidate> findByAccelerationId(Long accelerationId);




}
