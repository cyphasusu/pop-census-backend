// Services
package com.ecl.popcensus.service;

import com.ecl.popcensus.model.FloorType;
import com.ecl.popcensus.repository.FloorTypeRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FloorTypeService {
    private final FloorTypeRepository floorTypeRepository;

    public FloorTypeService(FloorTypeRepository floorTypeRepository) {
        this.floorTypeRepository = floorTypeRepository;
    }

    public FloorType createFloorType(FloorType floorType) {
        if (floorTypeRepository.existsByFloorTypeCode(floorType.getFloorTypeCode())) {
            throw new IllegalStateException("Floor type code already exists");
        }
        return floorTypeRepository.save(floorType);
    }

    public List<FloorType> getAllFloorTypes() {
        return floorTypeRepository.findAll();
    }

    public FloorType getFloorTypeById(Long id) {
        return floorTypeRepository.findById(id)
            .orElseThrow(() -> new IllegalStateException("Floor type not found with ID: " + id));
    }

    public FloorType updateFloorType(Long id, FloorType floorType) {
        FloorType existingType = getFloorTypeById(id);
        existingType.setFloorTypeDesc(floorType.getFloorTypeDesc());
        return floorTypeRepository.save(existingType);
    }
}
