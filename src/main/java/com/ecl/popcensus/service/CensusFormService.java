package com.ecl.popcensus.service;

import com.ecl.popcensus.Filters.JwtTokenFilter;
import com.ecl.popcensus.dto.mapping.MapCensusForm;
import com.ecl.popcensus.dto.mapping.MapUser;
import com.ecl.popcensus.dto.requests.CensusFormRequest;
import com.ecl.popcensus.dto.requests.RegisterUserRequest;
import com.ecl.popcensus.dto.responses.*;
import com.ecl.popcensus.model.CensusForm;
import com.ecl.popcensus.model.User;
import com.ecl.popcensus.model.UserStatus;
import com.ecl.popcensus.repository.CensusFormRepository;
import com.ecl.popcensus.repository.UserRepository;
import com.ecl.popcensus.util.Settings;
import jakarta.persistence.criteria.Predicate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
@Slf4j
public class CensusFormService {
    private final CensusFormRepository censusFormRepository;

    public CensusFormService(CensusFormRepository censusFormRepository) {
        this.censusFormRepository = censusFormRepository;
    }

    public CensusFormResponse createCensusForm(CensusForm censusForm) {
        var censusFormResponse = new CensusFormResponse();
        try{
                String authenticatedUser = JwtTokenFilter.authenticatedUser;
                censusForm.setCreatedBy(authenticatedUser);
                censusForm.setCreatedAt(new Date());
                censusForm.setModifiedBy(authenticatedUser);
                censusForm.setModifiedAt(new Date());
                censusForm.setCensusFormStatus(UserStatus.Active);
                var savedCensusForm = censusFormRepository.save(censusForm);

                censusFormResponse.setData(savedCensusForm);
                censusFormResponse.setCount(1);
                censusFormResponse.setResponseCode(Settings.getInstance("").getProperty("SUCCESS_CODE"));
                censusFormResponse.setResponseMessage("New census form created successfully");
                log.info("New census form created successfully");
        }catch(Exception ex){
            log.info("An error occurred creating census form. Error : " , ex);
                censusFormResponse.setResponseMessage(Settings.getInstance("").getProperty("INTERNAL_SERVER_ERROR"));
                censusFormResponse.setResponseCode("An error occurred creating census form. Error : " + ex);
        }


        return censusFormResponse;
    }

    public CensusListResponse getCensusForms(int page, int size, String censusFormName) {
        log.info("Getting list of census forms");

        CensusListResponse censusListResponse = new CensusListResponse();
        try {
            Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "modifiedAt"));
            Specification<CensusForm> spec = (root, query, criteriaBuilder) -> {
                List<Predicate> predicates = new ArrayList<>();
                if (censusFormName != null && !censusFormName.isEmpty()) {
                    predicates.add(criteriaBuilder.like(root.get("censusFormName"), "%" + censusFormName + "%"));
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
            };

            var censusForms = this.censusFormRepository.findAll(spec, pageable);
            var metadata = new Metadata();
            metadata.setTotalCount(censusForms.getTotalElements());
            metadata.setTotalPages(censusForms.getTotalPages());
            metadata.setHasNextPage(censusForms.hasNext());
            metadata.setHasPreviousPage(censusForms.hasPrevious());
            metadata.setCurrentPage(censusForms.getNumber());
            metadata.setPageSize(censusForms.getSize());

            censusListResponse.setMetadata(metadata);
            censusListResponse.setData(MapCensusForm.mapToCensusFormList(censusForms.getContent()));
            censusListResponse.setCount((int) censusForms.getTotalElements());
            censusListResponse.setIsCollection(!censusForms.isEmpty());
            censusListResponse.setResponseCode(Settings.getInstance("").getProperty("SUCCESS_CODE"));
            censusListResponse.setResponseMessage("Successfully retrieved list of all census forms");
            log.info("Census forms fetched successfully");
            return censusListResponse;
        }catch(Exception ex){
            censusListResponse.setResponseMessage(Settings.getInstance("").getProperty("INTERNAL_SERVER_ERROR"));
            censusListResponse.setResponseMessage("An error occurred while fetching census form list. Error : " + ex);
            return censusListResponse;
        }

    }


    public CensusFormResponse getCensusFormById(Long censusFormId){
        CensusFormResponse censusFormResponse = new CensusFormResponse();
        try {
            CensusForm u = censusFormRepository.findById(censusFormId).orElseThrow(() -> new IllegalStateException("This census form does not exist"));

            censusFormResponse.setData(u);
            censusFormResponse.setCount(1);
            censusFormResponse.setResponseCode(Settings.getInstance("").getProperty("SUCCESS_CODE"));
            censusFormResponse.setResponseMessage("Successfully retrieved census form");
            log.info("Census form with censusFormId {} fetched successfully", censusFormId);
        }catch (IllegalStateException illegalStateException){
            censusFormResponse.setResponseCode(Settings.getInstance("").getProperty("INTERNAL_SERVER_ERROR"));
            censusFormResponse.setResponseMessage("An error occurred getting census form by censusFormId. Error : " + illegalStateException);
            log.info("An error occurred getting user by userId");
        }
        return censusFormResponse;
    }

    public CensusFormResponse deleteCensusFormById(Long censusFormId) {
        CensusFormResponse censusFormResponse = new CensusFormResponse();
        try {
            String authenticatedUser = JwtTokenFilter.authenticatedUser;
            CensusForm u = censusFormRepository.findById(censusFormId).orElseThrow(() -> new IllegalStateException("This census form does not exist"));
            u.setDeletedAt(new Date());
            u.setDeletedBy(authenticatedUser);
            u.setCensusFormStatus(UserStatus.Deactivated);
            u.setModifiedAt(new Date());
            u.setModifiedBy(authenticatedUser);

            log.info("SavedCensusForm : ", u);
            censusFormRepository.save(u);

            censusFormResponse.setData(u);
            censusFormResponse.setCount(1);
            censusFormResponse.setResponseCode(Settings.getInstance("").getProperty("SUCCESS_CODE"));
            censusFormResponse.setResponseMessage("Successfully deleted census form with id " +  censusFormId);
            log.info("Census form with censusFormId {} deleted successfully", censusFormId);
        }catch (IllegalStateException illegalStateException){
            censusFormResponse.setResponseCode(Settings.getInstance("").getProperty("INTERNAL_SERVER_ERROR"));
            censusFormResponse.setResponseMessage("An error occurred while deleting census form with censusFormId. Error : " + illegalStateException);
            log.error("An error occurred while deleting census form with censusFormId : " + censusFormId);
        }

        return censusFormResponse;
    }

    public CensusFormResponse updateCensusForm(Long censusFormId, CensusForm censusForm) {
        var censusFormResponse = new CensusFormResponse();
        try{
            String authenticatedUser = JwtTokenFilter.authenticatedUser;
            var censusFormOptional = this.censusFormRepository.findById(censusFormId);
            if(censusFormOptional.isPresent()){
                var existingCensusForm = censusFormOptional.get();
                existingCensusForm.setCensusFormName(censusForm.getCensusFormName());
                existingCensusForm.setCensusFormLocation(censusForm.getCensusFormLocation());
                existingCensusForm.setCensusFormDesc(censusForm.getCensusFormDesc());
                existingCensusForm.setModifiedBy(authenticatedUser);
                existingCensusForm.setModifiedAt(new Date());

                censusFormRepository.save(existingCensusForm);

                var client = MapCensusForm.mapToCensusFormEntity(existingCensusForm);
                censusFormResponse.setData(client);
                censusFormResponse.setCount(1);
                censusFormResponse.setResponseCode(Settings.getInstance("").getProperty("SUCCESS_CODE"));
                censusFormResponse.setResponseMessage("Census form updated successfully");
                log.info("Successfully updated census form with id: " + censusFormId);
            } else {
                log.info("Census form with id {} does not exist", censusFormId);
                censusFormResponse.setResponseCode(Settings.getInstance("").getProperty("BAD_REQUEST_CODE"));
                censusFormResponse.setResponseMessage("Census form with id : " + censusFormId + " does not exist");
            }
        }catch(Exception ex){
            log.info("An error occurred updating census form with id {}. Error : " , censusFormId, ex);
            censusFormResponse.setResponseCode(Settings.getInstance("").getProperty("INTERNAL_SERVER_ERROR"));
            censusFormResponse.setResponseMessage("An error occurred while updating census form with censusFormId. Error : " + ex);
        }

        return censusFormResponse;
    }




}
