package com.ecl.popcensus.service;

import com.ecl.popcensus.model.HouseholdRoster;
import com.ecl.popcensus.repository.HouseholdRosterRepository;
import com.ecl.popcensus.repository.HouseholdInformationRepository;
import com.ecl.popcensus.repository.CensusFormRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class HouseholdRosterService {
    private final HouseholdRosterRepository rosterRepository;
    private final HouseholdInformationRepository householdRepository;
    private final CensusFormRepository censusFormRepository;

    public HouseholdRosterService(
        HouseholdRosterRepository rosterRepository,
        HouseholdInformationRepository householdRepository,
        CensusFormRepository censusFormRepository
    ) {
        this.rosterRepository = rosterRepository;
        this.householdRepository = householdRepository;
        this.censusFormRepository = censusFormRepository;
    }

    public HouseholdRoster createRoster(HouseholdRoster roster) {
        // Validate that household and census form exist
        if (!householdRepository.existsById(roster.getHouseholdId())) {
            throw new IllegalStateException("Household not found with ID: " + roster.getHouseholdId());
        }

        if (!censusFormRepository.existsById(roster.getCensusFormId())) {
            throw new IllegalStateException("Census form not found with ID: " + roster.getCensusFormId());
        }

        // Check if person already exists in household
        if (rosterRepository.existsByHouseholdIdAndPersonId(roster.getHouseholdId(), roster.getPersonId())) {
            throw new IllegalStateException("Person already exists in this household with ID: " + roster.getPersonId());
        }

        return rosterRepository.save(roster);
    }

    public List<HouseholdRoster> getAllRostersByHouseholdId(Long householdId) {
        return rosterRepository.findAllByHouseholdId(householdId);
    }

    public HouseholdRoster getRosterByHouseholdIdAndPersonId(Long householdId, Integer personId) {
        return rosterRepository.findByHouseholdIdAndPersonId(householdId, personId)
            .orElseThrow(() -> new IllegalStateException(
                "Person not found with ID: " + personId + " in household: " + householdId));
    }

    public HouseholdRoster getRosterByCensusFormId(Long censusFormId) {
        return rosterRepository.findByCensusFormId(censusFormId)
            .orElseThrow(() -> new IllegalStateException("Roster not found for census form: " + censusFormId));
    }

    public HouseholdRoster updateRoster(Long householdId, Integer personId, HouseholdRoster updatedRoster) {
        HouseholdRoster existingRoster = getRosterByHouseholdIdAndPersonId(householdId, personId);
        
        // Update all fields
        existingRoster.setFullName(updatedRoster.getFullName());
        existingRoster.setRelationshipToHead(updatedRoster.getRelationshipToHead());
        existingRoster.setRelationshipCode(updatedRoster.getRelationshipCode());
        existingRoster.setSex(updatedRoster.getSex());
        existingRoster.setStatus(updatedRoster.getStatus());
        existingRoster.setDOB(updatedRoster.getDOB());
        existingRoster.setAge(updatedRoster.getAge());
        existingRoster.setNationality(updatedRoster.getNationality());
        existingRoster.setEthnicityGroupName(updatedRoster.getEthnicityGroupName());
        existingRoster.setEthnicityGroupCode(updatedRoster.getEthnicityGroupCode());
        existingRoster.setBornInVillageOrTown(updatedRoster.getBornInVillageOrTown());
        existingRoster.setBirthPlace(updatedRoster.getBirthPlace());
        existingRoster.setLivingInVillageOrTown(updatedRoster.getLivingInVillageOrTown());
        existingRoster.setNumberOfYearsLivedInTown(updatedRoster.getNumberOfYearsLivedInTown());
        existingRoster.setReligionCode(updatedRoster.getReligionCode());
        existingRoster.setMaritalStatusCode(updatedRoster.getMaritalStatusCode());
        existingRoster.setLiteracyCode(updatedRoster.getLiteracyCode());
        existingRoster.setAttendedSchool(updatedRoster.getAttendedSchool());
        existingRoster.setHighestLevelOfSchooling(updatedRoster.getHighestLevelOfSchooling());
        existingRoster.setHighestEducationalGrade(updatedRoster.getHighestEducationalGrade());

        return rosterRepository.save(existingRoster);
    }

    public void deleteRoster(Long householdId, Integer personId) {
        HouseholdRoster roster = getRosterByHouseholdIdAndPersonId(householdId, personId);
        rosterRepository.deleteById(roster.getId());
    }
}
