// AgriculturalActivityRepository.java
package com.ecl.popcensus.repository;

import com.ecl.popcensus.model.AgriculturalActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AgriculturalActivityRepository extends JpaRepository<AgriculturalActivity, Long> {
    Optional<AgriculturalActivity> findByCensusFormId(Long censusFormId);
    Optional<AgriculturalActivity> findByHouseholdId(Long householdId);
    Optional<AgriculturalActivity> findByCensusFormIdAndHouseholdId(Long censusFormId, Long householdId);
    boolean existsByCensusFormId(Long censusFormId);
    boolean existsByHouseholdId(Long householdId);
}
