package com.ecl.popcensus.repository;

import com.ecl.popcensus.model.HouseholdInformation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HouseholdInformationRepository extends JpaRepository<HouseholdInformation, Long> {
    Optional<HouseholdInformation> findByIdAndCensusFormId(Long id, Long censusFormId);
    Optional<HouseholdInformation> findByCensusFormId(Long censusFormId);
    boolean existsByCensusFormIdAndVerificationId(Long censusFormId, String verificationId);
}

