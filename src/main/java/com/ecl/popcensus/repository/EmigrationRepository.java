// EmigrationRepository.java
package com.ecl.popcensus.repository;

import com.ecl.popcensus.model.Emigration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmigrationRepository extends JpaRepository<Emigration, Long> {
    Optional<Emigration> findByHouseholdId(Long householdId);
    Optional<Emigration> findByCensusFormId(Long censusFormId);
    boolean existsByHouseholdId(Long householdId);
    boolean existsByCensusFormId(Long censusFormId);
}
