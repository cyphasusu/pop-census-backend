// LivestockFisheriesService.java
package com.ecl.popcensus.service;

import com.ecl.popcensus.model.LivestockFisheries;
import com.ecl.popcensus.repository.LivestockFisheriesRepository;
import com.ecl.popcensus.repository.HouseholdInformationRepository;
import com.ecl.popcensus.repository.CensusFormRepository;
import org.springframework.stereotype.Service;

@Service
public class LivestockFisheriesService {
    private final LivestockFisheriesRepository livestockFisheriesRepository;
    private final HouseholdInformationRepository householdRepository;
    private final CensusFormRepository censusFormRepository;

    public LivestockFisheriesService(
        LivestockFisheriesRepository livestockFisheriesRepository,
        HouseholdInformationRepository householdRepository,
        CensusFormRepository censusFormRepository
    ) {
        this.livestockFisheriesRepository = livestockFisheriesRepository;
        this.householdRepository = householdRepository;
        this.censusFormRepository = censusFormRepository;
    }

    public LivestockFisheries createLivestockFisheries(Long censusFormId, Long householdId, LivestockFisheries livestock) {
        // Verify census form exists
        if (!censusFormRepository.existsById(censusFormId)) {
            throw new IllegalStateException("Census form not found with ID: " + censusFormId);
        }

        // Verify household exists
        if (!householdRepository.existsById(householdId)) {
            throw new IllegalStateException("Household not found with ID: " + householdId);
        }

        // Check if livestock fisheries info already exists (one-to-one relationship)
        if (livestockFisheriesRepository.existsByCensusFormId(censusFormId)) {
            throw new IllegalStateException("Livestock fisheries information already exists for this census form");
        }

        if (livestockFisheriesRepository.existsByHouseholdId(householdId)) {
            throw new IllegalStateException("Livestock fisheries information already exists for this household");
        }

        livestock.setCensusFormId(censusFormId);
        livestock.setHouseholdId(householdId);
        return livestockFisheriesRepository.save(livestock);
    }

    public LivestockFisheries getLivestockFisheries(Long censusFormId, Long householdId) {
        return livestockFisheriesRepository.findByCensusFormIdAndHouseholdId(censusFormId, householdId)
            .orElseThrow(() -> new IllegalStateException(
                "Livestock fisheries information not found for census form ID: " + censusFormId + 
                " and household ID: " + householdId));
    }

    public LivestockFisheries updateLivestockFisheries(Long censusFormId, Long householdId, LivestockFisheries updatedLivestock) {
        LivestockFisheries existingLivestock = getLivestockFisheries(censusFormId, householdId);

        // Update fields
        existingLivestock.setLineNumber(updatedLivestock.getLineNumber());
        existingLivestock.setTypeOfLivestock(updatedLivestock.getTypeOfLivestock());
        existingLivestock.setLivestockFisheryCode(updatedLivestock.getLivestockFisheryCode());
        existingLivestock.setNumber(updatedLivestock.getNumber());

        return livestockFisheriesRepository.save(existingLivestock);
    }
}
