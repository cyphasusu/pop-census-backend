// AbsentMembersRepository.java
package com.ecl.popcensus.repository;

import com.ecl.popcensus.model.AbsentMembers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AbsentMembersRepository extends JpaRepository<AbsentMembers, Long> {
    Optional<AbsentMembers> findByHouseholdId(Long householdId);
    Optional<AbsentMembers> findByCensusFormId(Long censusFormId);
    boolean existsByHouseholdId(Long householdId);
    boolean existsByCensusFormId(Long censusFormId);
}
