// HouseholdMembersSummaryRepository.java
package com.ecl.popcensus.repository;

import com.ecl.popcensus.model.HouseholdMembersSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HouseholdMembersSummaryRepository extends JpaRepository<HouseholdMembersSummary, Long> {
    Optional<HouseholdMembersSummary> findByHouseholdId(Long householdId);
    Optional<HouseholdMembersSummary> findByCensusFormId(Long censusFormId);
    boolean existsByHouseholdId(Long householdId);
    boolean existsByCensusFormId(Long censusFormId);
}

