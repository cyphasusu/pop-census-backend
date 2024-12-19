// AgriculturalActivityService.java
package com.ecl.popcensus.service;

import com.ecl.popcensus.model.AgriculturalActivity;
import com.ecl.popcensus.repository.AgriculturalActivityRepository;
import com.ecl.popcensus.repository.HouseholdInformationRepository;
import com.ecl.popcensus.repository.CensusFormRepository;
import org.springframework.stereotype.Service;

@Service
public class AgriculturalActivityService {
    private final AgriculturalActivityRepository agriculturalActivityRepository;
    private final HouseholdInformationRepository householdRepository;
    private final CensusFormRepository censusFormRepository;

    public AgriculturalActivityService(
        AgriculturalActivityRepository agriculturalActivityRepository,
        HouseholdInformationRepository householdRepository,
        CensusFormRepository censusFormRepository
    ) {
        this.agriculturalActivityRepository = agriculturalActivityRepository;
        this.householdRepository = householdRepository;
        this.censusFormRepository = censusFormRepository;
    }

    public AgriculturalActivity createAgriculturalActivity(Long censusFormId, Long householdId, AgriculturalActivity activity) {
        // Verify census form exists
        if (!censusFormRepository.existsById(censusFormId)) {
            throw new IllegalStateException("Census form not found with ID: " + censusFormId);
        }

        // Verify household exists
        if (!householdRepository.existsById(householdId)) {
            throw new IllegalStateException("Household not found with ID: " + householdId);
        }

        // Check if agricultural activity info already exists (one-to-one relationship)
        if (agriculturalActivityRepository.existsByCensusFormId(censusFormId)) {
            throw new IllegalStateException("Agricultural activity information already exists for this census form");
        }

        if (agriculturalActivityRepository.existsByHouseholdId(householdId)) {
            throw new IllegalStateException("Agricultural activity information already exists for this household");
        }

        activity.setCensusFormId(censusFormId);
        activity.setHouseholdId(householdId);
        return agriculturalActivityRepository.save(activity);
    }

    public AgriculturalActivity getAgriculturalActivity(Long censusFormId, Long householdId) {
        return agriculturalActivityRepository.findByCensusFormIdAndHouseholdId(censusFormId, householdId)
            .orElseThrow(() -> new IllegalStateException(
                "Agricultural activity information not found for census form ID: " + censusFormId + 
                " and household ID: " + householdId));
    }

    public AgriculturalActivity updateAgriculturalActivity(Long censusFormId, Long householdId, AgriculturalActivity updatedActivity) {
        AgriculturalActivity existingActivity = getAgriculturalActivity(censusFormId, householdId);

        // Update fields
        existingActivity.setCropFarming(updatedActivity.getCropFarming());
        existingActivity.setTreeGrowing(updatedActivity.getTreeGrowing());
        existingActivity.setLivestock(updatedActivity.getLivestock());
        existingActivity.setFishFarming(updatedActivity.getFishFarming());
        existingActivity.setTotalMembersMale(updatedActivity.getTotalMembersMale());
        existingActivity.setTotalMembersFemale(updatedActivity.getTotalMembersFemale());

        return agriculturalActivityRepository.save(existingActivity);
    }
}
