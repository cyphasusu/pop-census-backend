package com.ecl.popcensus.service;

import com.ecl.popcensus.model.EconomicActivity;
import com.ecl.popcensus.repository.EconomicActivityRepository;
import com.ecl.popcensus.repository.HouseholdInformationRepository;
import com.ecl.popcensus.repository.CensusFormRepository;
import com.ecl.popcensus.repository.HouseholdRosterRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EconomicActivityService {
    private final EconomicActivityRepository economicActivityRepository;
    private final HouseholdInformationRepository householdRepository;
    private final CensusFormRepository censusFormRepository;
    private final HouseholdRosterRepository rosterRepository;

    public EconomicActivityService(
        EconomicActivityRepository economicActivityRepository,
        HouseholdInformationRepository householdRepository,
        CensusFormRepository censusFormRepository,
        HouseholdRosterRepository rosterRepository
    ) {
        this.economicActivityRepository = economicActivityRepository;
        this.householdRepository = householdRepository;
        this.censusFormRepository = censusFormRepository;
        this.rosterRepository = rosterRepository;
    }

    public EconomicActivity createEconomicActivity(EconomicActivity economicActivity) {
        // Validate that household, census form, and person exist
        if (!householdRepository.existsById(economicActivity.getHouseholdId())) {
            throw new IllegalStateException("Household not found with ID: " + economicActivity.getHouseholdId());
        }

        if (!censusFormRepository.existsById(economicActivity.getCensusFormId())) {
            throw new IllegalStateException("Census form not found with ID: " + economicActivity.getCensusFormId());
        }

        if (!rosterRepository.existsByHouseholdIdAndPersonId(
                economicActivity.getHouseholdId(), 
                economicActivity.getPersonId())) {
            throw new IllegalStateException(
                "Person not found with ID: " + economicActivity.getPersonId() +
                " in household: " + economicActivity.getHouseholdId()
            );
        }

        // Check if economic activity record already exists for this person
        if (economicActivityRepository.existsByHouseholdIdAndPersonId(
                economicActivity.getHouseholdId(), 
                economicActivity.getPersonId())) {
            throw new IllegalStateException("Economic activity record already exists for this person");
        }

        return economicActivityRepository.save(economicActivity);
    }

    public List<EconomicActivity> getAllEconomicActivitiesByHouseholdId(Long householdId) {
        return economicActivityRepository.findAllByHouseholdId(householdId);
    }

    public List<EconomicActivity> getAllEconomicActivitiesByCensusFormId(Long censusFormId) {
        return economicActivityRepository.findAllByCensusFormId(censusFormId);
    }

    public EconomicActivity getEconomicActivityByPerson(Long householdId, Integer personId) {
        return economicActivityRepository.findByHouseholdIdAndPersonId(householdId, personId)
            .orElseThrow(() -> new IllegalStateException(
                "Economic activity not found for person: " + personId + 
                " in household: " + householdId));
    }


    public EconomicActivity updateEconomicActivity(Long id, EconomicActivity updatedActivity) {
        EconomicActivity existingActivity = economicActivityRepository.findById(id)
            .orElseThrow(() -> new IllegalStateException("Economic activity not found with ID: " + id));
    
        // Update all fields
        existingActivity.setPersonId(updatedActivity.getPersonId());
        existingActivity.setHelpingFamilyBusiness(updatedActivity.getHelpingFamilyBusiness());
        existingActivity.setHowPersonMainlyEngaged(updatedActivity.getHowPersonMainlyEngaged());
        existingActivity.setWhyPersonNotSeekWork(updatedActivity.getWhyPersonNotSeekWork());
        existingActivity.setOccupationDescription(updatedActivity.getOccupationDescription());
        existingActivity.setOccupationCode(updatedActivity.getOccupationCode());
        existingActivity.setNameAndPhysicalLocation(updatedActivity.getNameAndPhysicalLocation());
        existingActivity.setIndustryMainProductsOrService(updatedActivity.getIndustryMainProductsOrService());
        existingActivity.setIndustryMainProductsOrServiceCode(updatedActivity.getIndustryMainProductsOrServiceCode());
        existingActivity.setEmploymentStatus(updatedActivity.getEmploymentStatus());
        existingActivity.setEmploymentSector(updatedActivity.getEmploymentSector());
        existingActivity.setDisabilitySight(updatedActivity.getDisabilitySight());
        existingActivity.setDisabilityHearing(updatedActivity.getDisabilityHearing());
        existingActivity.setDisabilitySpeech(updatedActivity.getDisabilitySpeech());
        existingActivity.setDisabilityPhysical(updatedActivity.getDisabilityPhysical());
        existingActivity.setDisabilityIntellect(updatedActivity.getDisabilityIntellect());
        existingActivity.setDisabilityEmotional(updatedActivity.getDisabilityEmotional());
        existingActivity.setDisabilityOther(updatedActivity.getDisabilityOther());
        existingActivity.setUseMobilePhone(updatedActivity.getUseMobilePhone());
        existingActivity.setUseMobileFacility(updatedActivity.getUseMobileFacility());
        existingActivity.setUseInternetFacility(updatedActivity.getUseInternetFacility());
        existingActivity.setHouseholdId(updatedActivity.getHouseholdId());
        existingActivity.setCensusFormId(updatedActivity.getCensusFormId());
    
        return economicActivityRepository.save(existingActivity);
    }

    public void deleteEconomicActivity(Long id) {
        if (!economicActivityRepository.existsById(id)) {
            throw new IllegalStateException("Economic activity not found with ID: " + id);
        }
        economicActivityRepository.deleteById(id);
    }
}
