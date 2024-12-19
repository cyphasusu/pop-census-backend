// EconomicActivityRepository.java
package com.ecl.popcensus.repository;

import com.ecl.popcensus.model.EconomicActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface EconomicActivityRepository extends JpaRepository<EconomicActivity, Long> {
    Optional<EconomicActivity> findByHouseholdIdAndPersonId(Long householdId, Integer personId);
    List<EconomicActivity> findAllByHouseholdId(Long householdId);
    List<EconomicActivity> findAllByCensusFormId(Long censusFormId);
    boolean existsByHouseholdIdAndPersonId(Long householdId, Integer personId);
}
