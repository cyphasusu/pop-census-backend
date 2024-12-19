// Repositories
package com.ecl.popcensus.repository;

import com.ecl.popcensus.model.EmploymentSector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;


@Repository
public interface EmploymentSectorRepository extends JpaRepository<EmploymentSector, Long> {
    Optional<EmploymentSector> findByEmploymentSectorCode(String code);
    boolean existsByEmploymentSectorCode(String code);
}
