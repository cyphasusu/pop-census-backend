// FertilityRepository.java
package com.ecl.popcensus.repository;

import com.ecl.popcensus.model.Fertility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FertilityRepository extends JpaRepository<Fertility, Long> {
    List<Fertility> findByCensusFormIdAndHouseholdId(Long censusFormId, Long householdId);
    Optional<Fertility> findByIdAndCensusFormIdAndHouseholdId(Long id, Long censusFormId, Long householdId);
    Optional<Fertility> findByPersonIdAndCensusFormId(Long personId, Long censusFormId);
    boolean existsByPersonIdAndCensusFormId(Long personId, Long censusFormId);
}
