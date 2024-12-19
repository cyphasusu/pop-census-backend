// HouseholdMembersSummaryService.java
package com.ecl.popcensus.service;

import com.ecl.popcensus.model.HouseholdMembersSummary;
import com.ecl.popcensus.repository.HouseholdMembersSummaryRepository;
import com.ecl.popcensus.repository.HouseholdInformationRepository;
import com.ecl.popcensus.repository.CensusFormRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class HouseholdMembersSummaryService {
    private final HouseholdMembersSummaryRepository summaryRepository;
    private final HouseholdInformationRepository householdRepository;
    private final CensusFormRepository censusFormRepository;

    public HouseholdMembersSummaryService(
        HouseholdMembersSummaryRepository summaryRepository,
        HouseholdInformationRepository householdRepository,
        CensusFormRepository censusFormRepository
    ) {
        this.summaryRepository = summaryRepository;
        this.householdRepository = householdRepository;
        this.censusFormRepository = censusFormRepository;
    }

    public HouseholdMembersSummary createSummary(HouseholdMembersSummary summary) {
        // Validate that both household and census form exist
        if (!householdRepository.existsById(summary.getHouseholdId())) {
            throw new IllegalStateException("Household not found with ID: " + summary.getHouseholdId());
        }

        if (!censusFormRepository.existsById(summary.getCensusFormId())) {
            throw new IllegalStateException("Census form not found with ID: " + summary.getCensusFormId());
        }

        // Check if summary already exists for this household
        if (summaryRepository.existsByHouseholdId(summary.getHouseholdId())) {
            throw new IllegalStateException("Summary already exists for this household");
        }

        // Check if summary already exists for this census form
        if (summaryRepository.existsByCensusFormId(summary.getCensusFormId())) {
            throw new IllegalStateException("Summary already exists for this census form");
        }

        return summaryRepository.save(summary);
    }

    public HouseholdMembersSummary getSummaryByHouseholdId(Long householdId) {
        return summaryRepository.findByHouseholdId(householdId)
            .orElseThrow(() -> new IllegalStateException("Summary not found for household: " + householdId));
    }

    public HouseholdMembersSummary getSummaryByCensusFormId(Long censusFormId) {
        return summaryRepository.findByCensusFormId(censusFormId)
            .orElseThrow(() -> new IllegalStateException("Summary not found for census form: " + censusFormId));
    }

    public HouseholdMembersSummary updateSummary(Long id, HouseholdMembersSummary updatedSummary) {
        HouseholdMembersSummary existingSummary = summaryRepository.findById(id)
            .orElseThrow(() -> new IllegalStateException("Summary not found with ID: " + id));

        // Update fields
        existingSummary.setStatusAMale(updatedSummary.getStatusAMale());
        existingSummary.setStatusAFemale(updatedSummary.getStatusAFemale());
        existingSummary.setStatusBMale(updatedSummary.getStatusBMale());
        existingSummary.setStatusBFemale(updatedSummary.getStatusBFemale());
        existingSummary.setStatusCMale(updatedSummary.getStatusCMale());
        existingSummary.setStatusCFemale(updatedSummary.getStatusCFemale());
        existingSummary.setStatusABMale(updatedSummary.getStatusABMale());
        existingSummary.setStatusABFemale(updatedSummary.getStatusABFemale());

        return summaryRepository.save(existingSummary);
    }

    public void deleteSummary(Long id) {
        if (!summaryRepository.existsById(id)) {
            throw new IllegalStateException("Summary not found with ID: " + id);
        }
        summaryRepository.deleteById(id);
    }
}
