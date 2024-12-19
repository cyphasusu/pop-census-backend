// MortalityService.java
package com.ecl.popcensus.service;

import com.ecl.popcensus.model.Mortality;
import com.ecl.popcensus.repository.MortalityRepository;
import com.ecl.popcensus.repository.HouseholdInformationRepository;
import com.ecl.popcensus.repository.CensusFormRepository;
import org.springframework.stereotype.Service;

@Service
public class MortalityService {
    private final MortalityRepository mortalityRepository;
    private final HouseholdInformationRepository householdRepository;
    private final CensusFormRepository censusFormRepository;

    public MortalityService(
        MortalityRepository mortalityRepository,
        HouseholdInformationRepository householdRepository,
        CensusFormRepository censusFormRepository
    ) {
        this.mortalityRepository = mortalityRepository;
        this.householdRepository = householdRepository;
        this.censusFormRepository = censusFormRepository;
    }

    public Mortality createMortalityInfo(Long censusFormId, Long householdId, Mortality mortality) {
        // Verify census form exists
        if (!censusFormRepository.existsById(censusFormId)) {
            throw new IllegalStateException("Census form not found with ID: " + censusFormId);
        }

        // Verify household exists
        if (!householdRepository.existsById(householdId)) {
            throw new IllegalStateException("Household not found with ID: " + householdId);
        }

        // Check if mortality info already exists for this census form or household (one-to-one relationship)
        if (mortalityRepository.existsByCensusFormId(censusFormId)) {
            throw new IllegalStateException("Mortality information already exists for this census form");
        }

        if (mortalityRepository.existsByHouseholdId(householdId)) {
            throw new IllegalStateException("Mortality information already exists for this household");
        }

        mortality.setCensusFormId(censusFormId);
        mortality.setHouseholdId(householdId);
        return mortalityRepository.save(mortality);
    }

    public Mortality getMortalityInfo(Long censusFormId, Long householdId) {
        return mortalityRepository.findByCensusFormIdAndHouseholdId(censusFormId, householdId)
            .orElseThrow(() -> new IllegalStateException(
                "Mortality information not found for census form ID: " + censusFormId + 
                " and household ID: " + householdId));
    }

    public Mortality updateMortalityInfo(Long censusFormId, Long householdId, Mortality updatedInfo) {
        Mortality existingInfo = getMortalityInfo(censusFormId, householdId);

        // Update fields
        existingInfo.setMemberDiedInPastYear(updatedInfo.getMemberDiedInPastYear());
        existingInfo.setLineNumber(updatedInfo.getLineNumber());
        existingInfo.setNameOfDeceased(updatedInfo.getNameOfDeceased());
        existingInfo.setSexOfDeceased(updatedInfo.getSexOfDeceased());
        existingInfo.setAgeAtDeath(updatedInfo.getAgeAtDeath());
        existingInfo.setDeathDueToAccidentViolenceHomicideSuicide(
            updatedInfo.getDeathDueToAccidentViolenceHomicideSuicide());
        existingInfo.setDeathWhilePregnant(updatedInfo.getDeathWhilePregnant());

        return mortalityRepository.save(existingInfo);
    }
}
