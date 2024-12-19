// GeographicalInformationService.java
package com.ecl.popcensus.service;

import com.ecl.popcensus.model.GeographicalInformation;
import com.ecl.popcensus.repository.GeographicalInformationRepository;
import com.ecl.popcensus.repository.HouseholdInformationRepository;
import com.ecl.popcensus.repository.CensusFormRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class GeographicalInformationService {
    private final GeographicalInformationRepository geoRepository;
    private final HouseholdInformationRepository householdRepository;
    private final CensusFormRepository censusFormRepository;

    public GeographicalInformationService(
        GeographicalInformationRepository geoRepository,
        HouseholdInformationRepository householdRepository,
        CensusFormRepository censusFormRepository
    ) {
        this.geoRepository = geoRepository;
        this.householdRepository = householdRepository;
        this.censusFormRepository = censusFormRepository;
    }

    public GeographicalInformation createGeographicalInfo(GeographicalInformation geoInfo) {
        // Validate that both household and census form exist
        if (!householdRepository.existsById(geoInfo.getHouseholdId())) {
            throw new IllegalStateException("Household not found with ID: " + geoInfo.getHouseholdId());
        }

        if (!censusFormRepository.existsById(geoInfo.getCensusFormId())) {
            throw new IllegalStateException("Census form not found with ID: " + geoInfo.getCensusFormId());
        }

        // Check if geographical information already exists for this household
        if (geoRepository.existsByHouseholdId(geoInfo.getHouseholdId())) {
            throw new IllegalStateException("Geographical information already exists for this household");
        }

        // Check if geographical information already exists for this census form
        if (geoRepository.existsByCensusFormId(geoInfo.getCensusFormId())) {
            throw new IllegalStateException("Geographical information already exists for this census form");
        }

        return geoRepository.save(geoInfo);
    }

    public GeographicalInformation getGeographicalInfoByHouseholdId(Long householdId) {
        return geoRepository.findByHouseholdId(householdId)
            .orElseThrow(() -> new IllegalStateException("Geographical information not found for household: " + householdId));
    }

    public GeographicalInformation getGeographicalInfoByCensusFormId(Long censusFormId) {
        return geoRepository.findByCensusFormId(censusFormId)
            .orElseThrow(() -> new IllegalStateException("Geographical information not found for census form: " + censusFormId));
    }

    public GeographicalInformation updateGeographicalInfo(Long id, GeographicalInformation updatedInfo) {
        GeographicalInformation existingInfo = geoRepository.findById(id)
            .orElseThrow(() -> new IllegalStateException("Geographical information not found with ID: " + id));

        // Update fields
        existingInfo.setEnumerationAreaCode(updatedInfo.getEnumerationAreaCode());
        existingInfo.setEAType(updatedInfo.getEAType());
        existingInfo.setLocalityCode(updatedInfo.getLocalityCode());
        existingInfo.setStructureNumber(updatedInfo.getStructureNumber());
        existingInfo.setHouseholdNumber(updatedInfo.getHouseholdNumber());
        existingInfo.setTypeOfResidence(updatedInfo.getTypeOfResidence());

        return geoRepository.save(existingInfo);
    }

    public void deleteGeographicalInfo(Long id) {
        if (!geoRepository.existsById(id)) {
            throw new IllegalStateException("Geographical information not found with ID: " + id);
        }
        geoRepository.deleteById(id);
    }
}
