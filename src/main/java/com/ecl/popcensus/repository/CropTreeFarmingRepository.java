// CropTreeFarmingRepository.java
package com.ecl.popcensus.repository;

import com.ecl.popcensus.model.CropTreeFarming;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CropTreeFarmingRepository extends JpaRepository<CropTreeFarming, Long> {
    Optional<CropTreeFarming> findByCensusFormId(Long censusFormId);
    Optional<CropTreeFarming> findByHouseholdId(Long householdId);
    Optional<CropTreeFarming> findByCensusFormIdAndHouseholdId(Long censusFormId, Long householdId);
    boolean existsByCensusFormId(Long censusFormId);
    boolean existsByHouseholdId(Long householdId);
}
