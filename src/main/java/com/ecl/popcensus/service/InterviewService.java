package com.ecl.popcensus.service;

import com.ecl.popcensus.model.Interview;
import com.ecl.popcensus.repository.InterviewRepository;
import com.ecl.popcensus.repository.HouseholdInformationRepository;
import com.ecl.popcensus.repository.CensusFormRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
public class InterviewService {
    private final InterviewRepository interviewRepository;
    private final HouseholdInformationRepository householdRepository;
    private final CensusFormRepository censusFormRepository;

    public InterviewService(
        InterviewRepository interviewRepository,
        HouseholdInformationRepository householdRepository,
        CensusFormRepository censusFormRepository
    ) {
        this.interviewRepository = interviewRepository;
        this.householdRepository = householdRepository;
        this.censusFormRepository = censusFormRepository;
    }

    public Interview createInterview(Interview interview) {
        // Validate that both household and census form exist
        if (!householdRepository.existsById(interview.getHouseholdId())) {
            throw new IllegalStateException("Household not found with ID: " + interview.getHouseholdId());
        }

        if (!censusFormRepository.existsById(interview.getCensusFormId())) {
            throw new IllegalStateException("Census form not found with ID: " + interview.getCensusFormId());
        }

        // Check if interview already exists for this household
        if (interviewRepository.existsByHouseholdId(interview.getHouseholdId())) {
            throw new IllegalStateException("Interview already exists for this household");
        }

        // Check if interview already exists for this census form
        if (interviewRepository.existsByCensusFormId(interview.getCensusFormId())) {
            throw new IllegalStateException("Interview already exists for this census form");
        }

        return interviewRepository.save(interview);
    }

    public Interview getInterviewByHouseholdId(Long householdId) {
        return interviewRepository.findByHouseholdId(householdId)
            .orElseThrow(() -> new IllegalStateException("Interview not found for household: " + householdId));
    }

    public Interview getInterviewByCensusFormId(Long censusFormId) {
        return interviewRepository.findByCensusFormId(censusFormId)
            .orElseThrow(() -> new IllegalStateException("Interview not found for census form: " + censusFormId));
    }

    public Interview updateInterview(Long id, Interview updatedInterview) {
        Interview existingInterview = interviewRepository.findById(id)
            .orElseThrow(() -> new IllegalStateException("Interview not found with ID: " + id));

        // Update fields
        existingInterview.setDateStarted(updatedInterview.getDateStarted());
        existingInterview.setDateCompleted(updatedInterview.getDateCompleted());
        existingInterview.setTotalNumberOfVisits(updatedInterview.getTotalNumberOfVisits());

        return interviewRepository.save(existingInterview);
    }

    public Interview completeInterview(Long id) {
        Interview interview = interviewRepository.findById(id)
            .orElseThrow(() -> new IllegalStateException("Interview not found with ID: " + id));
        
        interview.setDateCompleted(LocalDateTime.now());
        return interviewRepository.save(interview);
    }

    public void deleteInterview(Long id) {
        if (!interviewRepository.existsById(id)) {
            throw new IllegalStateException("Interview not found with ID: " + id);
        }
        interviewRepository.deleteById(id);
    }
}
