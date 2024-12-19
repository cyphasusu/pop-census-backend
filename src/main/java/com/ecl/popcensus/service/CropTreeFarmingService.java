// CropTreeFarmingService.java
package com.ecl.popcensus.service;

import com.ecl.popcensus.model.CropTreeFarming;
import com.ecl.popcensus.repository.CropTreeFarmingRepository;
import com.ecl.popcensus.repository.HouseholdInformationRepository;
import com.ecl.popcensus.repository.CensusFormRepository;
import org.springframework.stereotype.Service;

@Service
public class CropTreeFarmingService {
    private final CropTreeFarmingRepository cropTreeFarmingRepository;
    private final HouseholdInformationRepository householdRepository;
    private final CensusFormRepository censusFormRepository;

    public CropTreeFarmingService(
        CropTreeFarmingRepository cropTreeFarmingRepository,
        HouseholdInformationRepository householdRepository,
        CensusFormRepository censusFormRepository
    ) {
        this.cropTreeFarmingRepository = cropTreeFarmingRepository;
        this.householdRepository = householdRepository;
        this.censusFormRepository = censusFormRepository;
    }

    public CropTreeFarming createCropTreeFarming(Long censusFormId, Long householdId, CropTreeFarming farming) {
        // Verify census form exists
        if (!censusFormRepository.existsById(censusFormId)) {
            throw new IllegalStateException("Census form not found with ID: " + censusFormId);
        }

        // Verify household exists
        if (!householdRepository.existsById(householdId)) {
            throw new IllegalStateException("Household not found with ID: " + householdId);
        }

        // Check if crop tree farming info already exists (one-to-one relationship)
        if (cropTreeFarmingRepository.existsByCensusFormId(censusFormId)) {
            throw new IllegalStateException("Crop tree farming information already exists for this census form");
        }

        if (cropTreeFarmingRepository.existsByHouseholdId(householdId)) {
            throw new IllegalStateException("Crop tree farming information already exists for this household");
        }

        farming.setCensusFormId(censusFormId);
        farming.setHouseholdId(householdId);
        return cropTreeFarmingRepository.save(farming);
    }

    public CropTreeFarming getCropTreeFarming(Long censusFormId, Long householdId) {
        return cropTreeFarmingRepository.findByCensusFormIdAndHouseholdId(censusFormId, householdId)
            .orElseThrow(() -> new IllegalStateException(
                "Crop tree farming information not found for census form ID: " + censusFormId + 
                " and household ID: " + householdId));
    }

    public CropTreeFarming updateCropTreeFarming(Long censusFormId, Long householdId, CropTreeFarming updatedFarming) {
        CropTreeFarming existingFarming = getCropTreeFarming(censusFormId, householdId);

        // Update fields
        existingFarming.setLineNumber(updatedFarming.getLineNumber());
        existingFarming.setTypeOfCropAndTreePlanting(updatedFarming.getTypeOfCropAndTreePlanting());
        existingFarming.setCropCode(updatedFarming.getCropCode());
        existingFarming.setFarmSize(updatedFarming.getFarmSize());
        existingFarming.setMeasurementUnit(updatedFarming.getMeasurementUnit());
        existingFarming.setTypeOfCropping(updatedFarming.getTypeOfCropping());

        return cropTreeFarmingRepository.save(existingFarming);
    }
}
