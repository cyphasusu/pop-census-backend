package com.ecl.popcensus.repository;

import com.ecl.popcensus.model.HouseholdInformation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HouseholdInformationRepository extends JpaRepository<HouseholdInformation, Long> {
    Optional<HouseholdInformation> findByIdAndCensusForm_CensusFormId(Long id, Long formId);
    
    @Query("SELECT h FROM HouseholdInformation h WHERE h.censusForm.censusFormId = :formId")
    Page<HouseholdInformation> findByCensusFormId(
        @Param("formId") Long formId, 
        Pageable pageable
    );

    boolean existsByCensusForm_CensusFormIdAndVerificationId(Long formId, String verificationId);
}
