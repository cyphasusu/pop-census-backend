// Repositories
package com.ecl.popcensus.repository;

import com.ecl.popcensus.model.OuterWall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;


@Repository
public interface OuterWallRepository extends JpaRepository<OuterWall, Long> {
    Optional<OuterWall> findByOuterWallCode(String code);
    boolean existsByOuterWallCode(String code);
}
