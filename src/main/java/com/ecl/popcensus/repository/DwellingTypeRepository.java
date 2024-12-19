// Repositories
package com.ecl.popcensus.repository;

import com.ecl.popcensus.model.DwellingType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface DwellingTypeRepository extends JpaRepository<DwellingType, Long> {
    Optional<DwellingType> findByDwellingTypeCode(String code);
    boolean existsByDwellingTypeCode(String code);
}
