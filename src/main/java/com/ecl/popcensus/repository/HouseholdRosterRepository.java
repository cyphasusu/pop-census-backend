// HouseholdRosterRepository.java
package com.ecl.popcensus.repository;

import com.ecl.popcensus.model.HouseholdRoster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface HouseholdRosterRepository extends JpaRepository<HouseholdRoster, Long> {
    Optional<HouseholdRoster> findByHouseholdIdAndPersonId(Long householdId, Integer personId);
    List<HouseholdRoster> findAllByHouseholdId(Long householdId);
    Optional<HouseholdRoster> findByCensusFormId(Long censusFormId);
    boolean existsByHouseholdIdAndPersonId(Long householdId, Integer personId);
    boolean existsByCensusFormId(Long censusFormId);
}
