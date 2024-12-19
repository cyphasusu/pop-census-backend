package com.ecl.popcensus.service;

import com.ecl.popcensus.model.LevelOfSchool;
import com.ecl.popcensus.repository.LevelOfSchoolRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class LevelOfSchoolService {
    private final LevelOfSchoolRepository levelOfSchoolRepository;

    public LevelOfSchoolService(LevelOfSchoolRepository levelOfSchoolRepository) {
        this.levelOfSchoolRepository = levelOfSchoolRepository;
    }

    public LevelOfSchool createLevelOfSchool(LevelOfSchool levelOfSchool) {
        if (levelOfSchoolRepository.existsBySchoolLevelCode(levelOfSchool.getSchoolLevelCode())) {
            throw new IllegalStateException("School level code already exists: " + levelOfSchool.getSchoolLevelCode());
        }
        return levelOfSchoolRepository.save(levelOfSchool);
    }

    public List<LevelOfSchool> getAllLevelsOfSchool() {
        return levelOfSchoolRepository.findAll();
    }

    public LevelOfSchool getLevelOfSchoolById(Long id) {
        return levelOfSchoolRepository.findById(id)
            .orElseThrow(() -> new IllegalStateException("Level of school not found with ID: " + id));
    }

    public LevelOfSchool getLevelOfSchoolByCode(String code) {
        return levelOfSchoolRepository.findBySchoolLevelCode(code)
            .orElseThrow(() -> new IllegalStateException("Level of school not found with code: " + code));
    }

    public LevelOfSchool updateLevelOfSchool(Long id, LevelOfSchool updatedLevel) {
        LevelOfSchool existingLevel = levelOfSchoolRepository.findById(id)
            .orElseThrow(() -> new IllegalStateException("Level of school not found with ID: " + id));

        // If school code is being changed, check for duplicates
        if (!existingLevel.getSchoolLevelCode().equals(updatedLevel.getSchoolLevelCode()) &&
            levelOfSchoolRepository.existsBySchoolLevelCode(updatedLevel.getSchoolLevelCode())) {
            throw new IllegalStateException("School level code already exists: " + updatedLevel.getSchoolLevelCode());
        }

        existingLevel.setSchoolLevelCode(updatedLevel.getSchoolLevelCode());
        existingLevel.setSchoolLevelDesc(updatedLevel.getSchoolLevelDesc());

        return levelOfSchoolRepository.save(existingLevel);
    }

    public void deleteLevelOfSchool(Long id) {
        if (!levelOfSchoolRepository.existsById(id)) {
            throw new IllegalStateException("Level of school not found with ID: " + id);
        }
        levelOfSchoolRepository.deleteById(id);
    }
}
