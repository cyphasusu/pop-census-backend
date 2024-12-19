// Services
package com.ecl.popcensus.service;

import com.ecl.popcensus.model.DwellingType;
import com.ecl.popcensus.repository.DwellingTypeRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DwellingTypeService {
    private final DwellingTypeRepository dwellingTypeRepository;

    public DwellingTypeService(DwellingTypeRepository dwellingTypeRepository) {
        this.dwellingTypeRepository = dwellingTypeRepository;
    }

    public DwellingType createDwellingType(DwellingType dwellingType) {
        if (dwellingTypeRepository.existsByDwellingTypeCode(dwellingType.getDwellingTypeCode())) {
            throw new IllegalStateException("Dwelling type code already exists");
        }
        return dwellingTypeRepository.save(dwellingType);
    }

    public List<DwellingType> getAllDwellingTypes() {
        return dwellingTypeRepository.findAll();
    }

    public DwellingType getDwellingTypeById(Long id) {
        return dwellingTypeRepository.findById(id)
            .orElseThrow(() -> new IllegalStateException("Dwelling type not found with ID: " + id));
    }

    public DwellingType updateDwellingType(Long id, DwellingType dwellingType) {
        DwellingType existingType = getDwellingTypeById(id);
        existingType.setDwellingTypeDesc(dwellingType.getDwellingTypeDesc());
        return dwellingTypeRepository.save(existingType);
    }
}
