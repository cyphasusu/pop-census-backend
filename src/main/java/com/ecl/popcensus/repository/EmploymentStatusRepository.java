package com.ecl.popcensus.repository;

import com.ecl.popcensus.model.EmploymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface EmploymentStatusRepository extends JpaRepository<EmploymentStatus, Long> {
    Optional<EmploymentStatus> findByEmploymentStatusCode(String code);
    boolean existsByEmploymentStatusCode(String code);
}
