package com.ecl.popcensus.service;

import com.ecl.popcensus.model.HouseholdInformation;
import com.ecl.popcensus.repository.HouseholdInformationRepository;
import com.ecl.popcensus.repository.CensusFormRepository;
import org.springframework.stereotype.Service;

@Service
public class HouseholdInformationService {
    private final HouseholdInformationRepository householdRepository;
    private final CensusFormRepository censusFormRepository;

    public HouseholdInformationService(
        HouseholdInformationRepository householdRepository,
        CensusFormRepository censusFormRepository
    ) {
        this.householdRepository = householdRepository;
        this.censusFormRepository = censusFormRepository;
    }

    public HouseholdInformation createHouseholdInfo(Long censusFormId, HouseholdInformation householdInfo) {
        // Verify census form exists
        if (!censusFormRepository.existsById(censusFormId)) {
            throw new IllegalStateException("Census form not found with ID: " + censusFormId);
        }
        System.out.println("Done checking if census form already exists");

        // Check if household info already exists for this census form
        if (householdRepository.findByCensusFormId(censusFormId).isPresent()) {
            throw new IllegalStateException("Household information already exists for this census form");
        }
        System.out.println("Done checking if household info already exists");

        // Check for duplicate verification ID
        if (householdRepository.existsByCensusFormIdAndVerificationId(
                censusFormId, householdInfo.getVerificationId())) {
            throw new IllegalStateException("Verification ID already exists in this census form");
        }

        System.out.println("Done checking for duplicates");

        householdInfo.setCensusFormId(censusFormId);
        System.out.println("Done setting census form id");
        return householdRepository.save(householdInfo);
    }

    public HouseholdInformation getHouseholdInfoByFormId(Long censusFormId) {
        return householdRepository.findByCensusFormId(censusFormId)
            .orElseThrow(() -> new IllegalStateException("Household information not found for census form: " + censusFormId));
    }

    public HouseholdInformation updateHouseholdInfo(
        Long censusFormId,
        Long householdId,
        HouseholdInformation updatedInfo
    ) {
        HouseholdInformation existingInfo = householdRepository
            .findByIdAndCensusFormId(householdId, censusFormId)
            .orElseThrow(() -> new IllegalStateException("Household information not found"));

        existingInfo.setRegionName(updatedInfo.getRegionName());
        existingInfo.setDistrictName(updatedInfo.getDistrictName());
        existingInfo.setDistrictType(updatedInfo.getDistrictType());
        existingInfo.setSubDistrict(updatedInfo.getSubDistrict());
        existingInfo.setLocalityName(updatedInfo.getLocalityName());
        existingInfo.setVerificationId(updatedInfo.getVerificationId());
        existingInfo.setDetailedAddress(updatedInfo.getDetailedAddress());
        existingInfo.setContactPhoneNumber1(updatedInfo.getContactPhoneNumber1());
        existingInfo.setContactPhoneNumber2(updatedInfo.getContactPhoneNumber2());

        return householdRepository.save(existingInfo);
    }
}