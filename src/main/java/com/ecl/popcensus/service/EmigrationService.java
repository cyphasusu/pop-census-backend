package com.ecl.popcensus.service;

import com.ecl.popcensus.model.Emigration;
import com.ecl.popcensus.repository.EmigrationRepository;
import com.ecl.popcensus.repository.HouseholdInformationRepository;
import com.ecl.popcensus.repository.CensusFormRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EmigrationService {
    private final EmigrationRepository emigrationRepository;
    private final HouseholdInformationRepository householdRepository;
    private final CensusFormRepository censusFormRepository;

    public EmigrationService(
        EmigrationRepository emigrationRepository,
        HouseholdInformationRepository householdRepository,
        CensusFormRepository censusFormRepository
    ) {
        this.emigrationRepository = emigrationRepository;
        this.householdRepository = householdRepository;
        this.censusFormRepository = censusFormRepository;
    }

    public Emigration createEmigration(Emigration emigration) {
        // Validate that both household and census form exist
        if (!householdRepository.existsById(emigration.getHouseholdId())) {
            throw new IllegalStateException("Household not found with ID: " + emigration.getHouseholdId());
        }

        if (!censusFormRepository.existsById(emigration.getCensusFormId())) {
            throw new IllegalStateException("Census form not found with ID: " + emigration.getCensusFormId());
        }

        // Check if emigration record already exists for this household
        if (emigrationRepository.existsByHouseholdId(emigration.getHouseholdId())) {
            throw new IllegalStateException("Emigration record already exists for this household");
        }

        // Check if emigration record already exists for this census form
        if (emigrationRepository.existsByCensusFormId(emigration.getCensusFormId())) {
            throw new IllegalStateException("Emigration record already exists for this census form");
        }

        return emigrationRepository.save(emigration);
    }

    public Emigration getEmigrationByHouseholdId(Long householdId) {
        return emigrationRepository.findByHouseholdId(householdId)
            .orElseThrow(() -> new IllegalStateException("Emigration not found for household: " + householdId));
    }

    public Emigration getEmigrationByCensusFormId(Long censusFormId) {
        return emigrationRepository.findByCensusFormId(censusFormId)
            .orElseThrow(() -> new IllegalStateException("Emigration not found for census form: " + censusFormId));
    }

    public Emigration updateEmigration(Long id, Emigration updatedEmigration) {
        Emigration existingEmigration = emigrationRepository.findById(id)
            .orElseThrow(() -> new IllegalStateException("Emigration not found with ID: " + id));

        // Update fields
        existingEmigration.setFormerMembersLivingOutside(updatedEmigration.getFormerMembersLivingOutside());
        existingEmigration.setLineNumber(updatedEmigration.getLineNumber());
        existingEmigration.setFullName(updatedEmigration.getFullName());
        existingEmigration.setRelationshipToHead(updatedEmigration.getRelationshipToHead());
        existingEmigration.setCode(updatedEmigration.getCode());
        existingEmigration.setSex(updatedEmigration.getSex());
        existingEmigration.setAge(updatedEmigration.getAge());
        existingEmigration.setDestinationCountryName(updatedEmigration.getDestinationCountryName());
        existingEmigration.setDestinationCountryCode(updatedEmigration.getDestinationCountryCode());
        existingEmigration.setYearOfDeparture(updatedEmigration.getYearOfDeparture());
        existingEmigration.setActivityAbroadCode(updatedEmigration.getActivityAbroadCode());

        return emigrationRepository.save(existingEmigration);
    }

    public void deleteEmigration(Long id) {
        if (!emigrationRepository.existsById(id)) {
            throw new IllegalStateException("Emigration not found with ID: " + id);
        }
        emigrationRepository.deleteById(id);
    }
}
