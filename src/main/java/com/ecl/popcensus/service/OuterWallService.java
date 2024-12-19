// Services
package com.ecl.popcensus.service;

import com.ecl.popcensus.model.OuterWall;
import com.ecl.popcensus.repository.OuterWallRepository;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class OuterWallService {
    private final OuterWallRepository outerWallRepository;

    public OuterWallService(OuterWallRepository outerWallRepository) {
        this.outerWallRepository = outerWallRepository;
    }

    public OuterWall createOuterWall(OuterWall outerWall) {
        if (outerWallRepository.existsByOuterWallCode(outerWall.getOuterWallCode())) {
            throw new IllegalStateException("Outer wall code already exists");
        }
        return outerWallRepository.save(outerWall);
    }

    public List<OuterWall> getAllOuterWalls() {
        return outerWallRepository.findAll();
    }

    public OuterWall getOuterWallById(Long id) {
        return outerWallRepository.findById(id)
            .orElseThrow(() -> new IllegalStateException("Outer wall not found with ID: " + id));
    }

    public OuterWall updateOuterWall(Long id, OuterWall outerWall) {
        OuterWall existingWall = getOuterWallById(id);
        existingWall.setOuterWallDesc(outerWall.getOuterWallDesc());
        return outerWallRepository.save(existingWall);
    }
}
