package com.ecl.popcensus.service;

import com.ecl.popcensus.Filters.JwtTokenFilter;
import com.ecl.popcensus.dto.mapping.MapCensusForm;
import com.ecl.popcensus.dto.mapping.MapUser;
import com.ecl.popcensus.dto.requests.CensusFormRequest;
import com.ecl.popcensus.dto.requests.RegisterUserRequest;
import com.ecl.popcensus.dto.responses.*;
import com.ecl.popcensus.model.HouseholdInformation;
import com.ecl.popcensus.model.CensusForm;
import com.ecl.popcensus.model.User;
import com.ecl.popcensus.model.UserStatus;
import com.ecl.popcensus.repository.HouseholdInformationRepository;
import com.ecl.popcensus.repository.CensusFormRepository;
import com.ecl.popcensus.repository.UserRepository;
import com.ecl.popcensus.util.Settings;
import jakarta.persistence.criteria.Predicate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
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
        CensusForm form = censusFormRepository.findById(censusFormId)
            .orElseThrow(() -> new IllegalStateException("Census form not found with ID: " + censusFormId));

        // Check for duplicate verification ID within the same form
        if (householdRepository.existsByCensusForm_CensusFormIdAndVerificationId(
                censusFormId, householdInfo.getVerificationId())) {
            throw new IllegalStateException("Verification ID already exists in this census form");
        }

        householdInfo.setCensusForm(form);
        //householdInfo.setCreatedAt(new Date());
        //householdInfo.setCreatedBy(JwtTokenFilter.authenticatedUser);
        //householdInfo.setModifiedAt(new Date());
        //householdInfo.setModifiedBy(JwtTokenFilter.authenticatedUser);

        return householdRepository.save(householdInfo);
    }

    public Page<HouseholdInformation> getHouseholdInfoByFormId(
        Long censusFormId, 
        int page, 
        int size
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        return householdRepository.findByCensusFormId(censusFormId, pageable);
    }

    public HouseholdInformation updateHouseholdInfo(
        Long censusFormId,
        Long householdId,
        HouseholdInformation updatedInfo
    ) {
        HouseholdInformation existingInfo = householdRepository
            .findByIdAndCensusForm_CensusFormId(householdId, censusFormId)
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
        //existingInfo.setModifiedAt(new Date());
        //existingInfo.setModifiedBy(JwtTokenFilter.authenticatedUser);

        return householdRepository.save(existingInfo);
    }
}