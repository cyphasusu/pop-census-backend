package com.ecl.popcensus.repository;

import com.ecl.popcensus.model.CensusForm;
import com.ecl.popcensus.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface CensusFormRepository extends JpaRepository<CensusForm, Long>, JpaSpecificationExecutor<CensusForm> {

    @Query("SELECT u FROM CensusForm u WHERE u.createdBy = ?1 AND u.deletedAt is NULL ORDER BY u.id DESC") //taking this out still gives same effect
    Optional<CensusForm> findCensusFormByEmail(String email);

    @Query("SELECT u FROM CensusForm u WHERE u.deletedAt is NULL ORDER BY u.id DESC")
    List<CensusForm> findAll();

    @Query("SELECT u FROM CensusForm u WHERE u.censusFormId = ?1 AND u.deletedAt is NULL")
    Optional<CensusForm> findById(Long censusFormId);

}