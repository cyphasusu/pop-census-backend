// ICTRepository.java
package com.ecl.popcensus.repository;

import com.ecl.popcensus.model.ICT;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICTRepository extends JpaRepository<ICT, Long> {
    Optional<ICT> findByCensusFormId(Long censusFormId);
    Optional<ICT> findByHouseholdId(Long householdId);
    Optional<ICT> findByCensusFormIdAndHouseholdId(Long censusFormId, Long householdId);
    boolean existsByCensusFormId(Long censusFormId);
    boolean existsByHouseholdId(Long householdId);
}
