package com.challenge.repository;

import com.challenge.entity.Company;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CompanyRepository extends CrudRepository<Company, Long> {

    List<Company> findDistinctByCandidatesIdAccelerationId(Long accelerationId);

    @Query(value = "SELECT * FROM company\n" +
            "JOIN candidate ON candidate.company_id = company.id\n" +
            "JOIN users ON users.id = candidate.user_id\n" +
            "WHERE users.id = ?1", nativeQuery = true)
    List<Company> findByUserId(Long userId);
}
