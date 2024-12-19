// ICTService.java
package com.ecl.popcensus.service;

import com.ecl.popcensus.model.ICT;
import com.ecl.popcensus.repository.ICTRepository;
import com.ecl.popcensus.repository.HouseholdInformationRepository;
import com.ecl.popcensus.repository.CensusFormRepository;
import org.springframework.stereotype.Service;

@Service
public class ICTService {
    private final ICTRepository ictRepository;
    private final HouseholdInformationRepository householdRepository;
    private final CensusFormRepository censusFormRepository;

    public ICTService(
        ICTRepository ictRepository,
        HouseholdInformationRepository householdRepository,
        CensusFormRepository censusFormRepository
    ) {
        this.ictRepository = ictRepository;
        this.householdRepository = householdRepository;
        this.censusFormRepository = censusFormRepository;
    }

    public ICT createICTInfo(Long censusFormId, Long householdId, ICT ict) {
        // Verify census form exists
        if (!censusFormRepository.existsById(censusFormId)) {
            throw new IllegalStateException("Census form not found with ID: " + censusFormId);
        }

        // Verify household exists
        if (!householdRepository.existsById(householdId)) {
            throw new IllegalStateException("Household not found with ID: " + householdId);
        }

        // Check if ICT info already exists for this census form or household (one-to-one relationship)
        if (ictRepository.existsByCensusFormId(censusFormId)) {
            throw new IllegalStateException("ICT information already exists for this census form");
        }

        if (ictRepository.existsByHouseholdId(householdId)) {
            throw new IllegalStateException("ICT information already exists for this household");
        }

        ict.setCensusFormId(censusFormId);
        ict.setHouseholdId(householdId);
        return ictRepository.save(ict);
    }

    public ICT getICTInfo(Long censusFormId, Long householdId) {
        return ictRepository.findByCensusFormIdAndHouseholdId(censusFormId, householdId)
            .orElseThrow(() -> new IllegalStateException(
                "ICT information not found for census form ID: " + censusFormId + 
                " and household ID: " + householdId));
    }

    public ICT updateICTInfo(Long censusFormId, Long householdId, ICT updatedInfo) {
        ICT existingInfo = getICTInfo(censusFormId, householdId);

        // Update fields
        existingInfo.setHomeTelephoneLine(updatedInfo.getHomeTelephoneLine());
        existingInfo.setHouseMemberOwnComputer(updatedInfo.getHouseMemberOwnComputer());

        return ictRepository.save(existingInfo);
    }
}
