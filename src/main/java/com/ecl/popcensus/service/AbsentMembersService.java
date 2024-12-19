package com.ecl.popcensus.service;

import com.ecl.popcensus.model.AbsentMembers;
import com.ecl.popcensus.repository.AbsentMembersRepository;
import com.ecl.popcensus.repository.HouseholdInformationRepository;
import com.ecl.popcensus.repository.CensusFormRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AbsentMembersService {
    private final AbsentMembersRepository absentMembersRepository;
    private final HouseholdInformationRepository householdRepository;
    private final CensusFormRepository censusFormRepository;

    public AbsentMembersService(
        AbsentMembersRepository absentMembersRepository,
        HouseholdInformationRepository householdRepository,
        CensusFormRepository censusFormRepository
    ) {
        this.absentMembersRepository = absentMembersRepository;
        this.householdRepository = householdRepository;
        this.censusFormRepository = censusFormRepository;
    }

    public AbsentMembers createAbsentMembers(AbsentMembers absentMembers) {
        // Validate that both household and census form exist
        if (!householdRepository.existsById(absentMembers.getHouseholdId())) {
            throw new IllegalStateException("Household not found with ID: " + absentMembers.getHouseholdId());
        }

        if (!censusFormRepository.existsById(absentMembers.getCensusFormId())) {
            throw new IllegalStateException("Census form not found with ID: " + absentMembers.getCensusFormId());
        }

        // Check if absent members record already exists for this household
        if (absentMembersRepository.existsByHouseholdId(absentMembers.getHouseholdId())) {
            throw new IllegalStateException("Absent members record already exists for this household");
        }

        // Check if absent members record already exists for this census form
        if (absentMembersRepository.existsByCensusFormId(absentMembers.getCensusFormId())) {
            throw new IllegalStateException("Absent members record already exists for this census form");
        }

        return absentMembersRepository.save(absentMembers);
    }

    public AbsentMembers getAbsentMembersByHouseholdId(Long householdId) {
        return absentMembersRepository.findByHouseholdId(householdId)
            .orElseThrow(() -> new IllegalStateException("Absent members not found for household: " + householdId));
    }

    public AbsentMembers getAbsentMembersByCensusFormId(Long censusFormId) {
        return absentMembersRepository.findByCensusFormId(censusFormId)
            .orElseThrow(() -> new IllegalStateException("Absent members not found for census form: " + censusFormId));
    }

    public AbsentMembers updateAbsentMembers(Long id, AbsentMembers updatedMembers) {
        AbsentMembers existingMembers = absentMembersRepository.findById(id)
            .orElseThrow(() -> new IllegalStateException("Absent members not found with ID: " + id));

        // Update fields
        existingMembers.setNumberOfAbsentees(updatedMembers.getNumberOfAbsentees());
        existingMembers.setLineNumber(updatedMembers.getLineNumber());
        existingMembers.setFullName(updatedMembers.getFullName());
        existingMembers.setRelationshipToHead(updatedMembers.getRelationshipToHead());
        existingMembers.setCode(updatedMembers.getCode());
        existingMembers.setSex(updatedMembers.getSex());
        existingMembers.setAge(updatedMembers.getAge());
        existingMembers.setDestination(updatedMembers.getDestination());
        existingMembers.setRegionCountryCode(updatedMembers.getRegionCountryCode());
        existingMembers.setMonthsAbsent(updatedMembers.getMonthsAbsent());

        return absentMembersRepository.save(existingMembers);
    }

    public void deleteAbsentMembers(Long id) {
        if (!absentMembersRepository.existsById(id)) {
            throw new IllegalStateException("Absent members not found with ID: " + id);
        }
        absentMembersRepository.deleteById(id);
    }
}
