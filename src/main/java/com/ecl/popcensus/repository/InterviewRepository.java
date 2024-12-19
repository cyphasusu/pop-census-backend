// InterviewRepository.java
package com.ecl.popcensus.repository;

import com.ecl.popcensus.model.Interview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InterviewRepository extends JpaRepository<Interview, Long> {
    Optional<Interview> findByHouseholdId(Long householdId);
    Optional<Interview> findByCensusFormId(Long censusFormId);
    boolean existsByHouseholdId(Long householdId);
    boolean existsByCensusFormId(Long censusFormId);
}
