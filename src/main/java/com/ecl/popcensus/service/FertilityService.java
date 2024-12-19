// FertilityService.java
package com.ecl.popcensus.service;

import com.ecl.popcensus.model.Fertility;
import com.ecl.popcensus.repository.FertilityRepository;
import com.ecl.popcensus.repository.HouseholdInformationRepository;
import com.ecl.popcensus.repository.CensusFormRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FertilityService {
    private final FertilityRepository fertilityRepository;
    private final HouseholdInformationRepository householdRepository;
    private final CensusFormRepository censusFormRepository;

    public FertilityService(
        FertilityRepository fertilityRepository,
        HouseholdInformationRepository householdRepository,
        CensusFormRepository censusFormRepository
    ) {
        this.fertilityRepository = fertilityRepository;
        this.householdRepository = householdRepository;
        this.censusFormRepository = censusFormRepository;
    }

    public Fertility createFertilityInfo(Long censusFormId, Long householdId, Fertility fertility) {
        // Verify census form exists
        if (!censusFormRepository.existsById(censusFormId)) {
            throw new IllegalStateException("Census form not found with ID: " + censusFormId);
        }

        // Verify household exists
        if (!householdRepository.existsById(householdId)) {
            throw new IllegalStateException("Household not found with ID: " + householdId);
        }

        // Check if fertility info already exists for this person in this census
        if (fertilityRepository.existsByPersonIdAndCensusFormId(fertility.getPersonId(), censusFormId)) {
            throw new IllegalStateException("Fertility information already exists for this person in this census");
        }

        fertility.setCensusFormId(censusFormId);
        fertility.setHouseholdId(householdId);
        return fertilityRepository.save(fertility);
    }

    public List<Fertility> getFertilityInfoByHousehold(Long censusFormId, Long householdId) {
        return fertilityRepository.findByCensusFormIdAndHouseholdId(censusFormId, householdId);
    }

    public Fertility updateFertilityInfo(Long censusFormId, Long householdId, Long fertilityId, Fertility updatedInfo) {
        Fertility existingInfo = fertilityRepository
            .findByIdAndCensusFormIdAndHouseholdId(fertilityId, censusFormId, householdId)
            .orElseThrow(() -> new IllegalStateException("Fertility information not found"));

        // Update fields
        existingInfo.setTotalChildrenEverBornMale(updatedInfo.getTotalChildrenEverBornMale());
        existingInfo.setTotalChildrenEverBornFemale(updatedInfo.getTotalChildrenEverBornFemale());
        existingInfo.setTotalChildrenSurvivingMale(updatedInfo.getTotalChildrenSurvivingMale());
        existingInfo.setTotalChildrenSurvivingFemale(updatedInfo.getTotalChildrenSurvivingFemale());
        existingInfo.setTotalChildrenBornInPastYearBoy(updatedInfo.getTotalChildrenBornInPastYearBoy());
        existingInfo.setTotalChildrenBornInPastYearGirl(updatedInfo.getTotalChildrenBornInPastYearGirl());

        return fertilityRepository.save(existingInfo);
    }
}
