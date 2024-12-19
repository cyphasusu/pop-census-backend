// GeographicalInformationRepository.java
package com.ecl.popcensus.repository;

import com.ecl.popcensus.model.GeographicalInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GeographicalInformationRepository extends JpaRepository<GeographicalInformation, Long> {
    Optional<GeographicalInformation> findByHouseholdId(Long householdId);
    Optional<GeographicalInformation> findByCensusFormId(Long censusFormId);
    boolean existsByHouseholdId(Long householdId);
    boolean existsByCensusFormId(Long censusFormId);
}
