package com.challenge.repository;

import com.challenge.entity.Acceleration;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface AccelerationRepository extends CrudRepository<Acceleration, Long> {
    Optional<Acceleration> findAccelerationById(Long id);

    Optional<Acceleration> findAccelerationByName(String name);

    @Query(value = "SELECT * FROM acceleration\n" +
            "JOIN candidate ON candidate.acceleration_id = acceleration.id\n" +
            "JOIN company ON company.id = candidate.company_id\n" +
            "WHERE company.id = ?1", nativeQuery = true)
    List<Acceleration> findByCompanyId(Long companyId);
}
