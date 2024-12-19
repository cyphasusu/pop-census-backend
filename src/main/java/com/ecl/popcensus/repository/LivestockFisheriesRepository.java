// LivestockFisheriesRepository.java
package com.ecl.popcensus.repository;

import com.ecl.popcensus.model.LivestockFisheries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LivestockFisheriesRepository extends JpaRepository<LivestockFisheries, Long> {
    Optional<LivestockFisheries> findByCensusFormId(Long censusFormId);
    Optional<LivestockFisheries> findByHouseholdId(Long householdId);
    Optional<LivestockFisheries> findByCensusFormIdAndHouseholdId(Long censusFormId, Long householdId);
    boolean existsByCensusFormId(Long censusFormId);
    boolean existsByHouseholdId(Long householdId);
}
