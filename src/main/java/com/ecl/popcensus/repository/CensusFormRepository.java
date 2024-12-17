package com.ecl.popcensus.repository;

import com.ecl.popcensus.model.CensusForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface CensusFormRepository extends JpaRepository<CensusForm, Long>, JpaSpecificationExecutor<CensusForm> {

    @Query("SELECT u FROM CensusForm u WHERE u.createdBy = ?1 AND u.deletedAt is NULL ORDER BY u.id DESC") //taking this out still gives same effect
    Optional<CensusForm> findCensusFormByEmail(String email);

    @Query("SELECT u FROM CensusForm u WHERE u.deletedAt is NULL ORDER BY u.id DESC")
    Page<CensusForm> findAll(Specification<CensusForm> spec, Pageable pageable);
   // List<CensusForm> findAll();

    @Query("SELECT u FROM CensusForm u WHERE u.censusFormId = ?1 AND u.deletedAt is NULL")
    Optional<CensusForm> findById(Long censusFormId);

}