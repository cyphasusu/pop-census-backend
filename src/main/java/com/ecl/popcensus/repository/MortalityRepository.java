// MortalityRepository.java
package com.ecl.popcensus.repository;

import com.ecl.popcensus.model.Mortality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MortalityRepository extends JpaRepository<Mortality, Long> {
    Optional<Mortality> findByCensusFormId(Long censusFormId);
    Optional<Mortality> findByHouseholdId(Long householdId);
    Optional<Mortality> findByCensusFormIdAndHouseholdId(Long censusFormId, Long householdId);
    boolean existsByCensusFormId(Long censusFormId);
    boolean existsByHouseholdId(Long householdId);
}
