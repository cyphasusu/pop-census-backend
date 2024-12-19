// Repositories
package com.ecl.popcensus.repository;

import com.ecl.popcensus.model.FloorType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface FloorTypeRepository extends JpaRepository<FloorType, Long> {
    Optional<FloorType> findByFloorTypeCode(String code);
    boolean existsByFloorTypeCode(String code);
}
