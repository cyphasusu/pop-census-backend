package com.ecl.popcensus.repository;

import com.ecl.popcensus.model.Occupation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface OccupationRepository extends JpaRepository<Occupation, Long> {
    Optional<Occupation> findByOccupationCode(String code);
    boolean existsByOccupationCode(String code);
}
