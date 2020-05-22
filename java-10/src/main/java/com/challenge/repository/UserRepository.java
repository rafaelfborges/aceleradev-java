package com.challenge.repository;

import com.challenge.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {

    @Query(value = "SELECT * FROM users\n" +
            "JOIN candidate ON users.id = candidate.user_id\n" +
            "JOIN acceleration ON acceleration.id = candidate.acceleration_id\n" +
            "WHERE acceleration.name = ?1", nativeQuery = true)
    List<User> findByAccelerationName(String name);

    @Query(value = "SELECT * FROM users\n" +
            "JOIN candidate ON users.id = candidate.user_id\n" +
            "JOIN company ON company.id = candidate.company_id\n" +
            "WHERE company.id = ?1", nativeQuery = true)
    List<User> findByCompanyId(Long companyId);
}
