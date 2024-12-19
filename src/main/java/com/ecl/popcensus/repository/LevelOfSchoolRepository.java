// LevelOfSchoolRepository.java
package com.ecl.popcensus.repository;

import com.ecl.popcensus.model.LevelOfSchool;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LevelOfSchoolRepository extends JpaRepository<LevelOfSchool, Long> {
    Optional<LevelOfSchool> findBySchoolLevelCode(String schoolLevelCode);
    boolean existsBySchoolLevelCode(String schoolLevelCode);
}
