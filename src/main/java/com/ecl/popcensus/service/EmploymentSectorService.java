package com.ecl.popcensus.service;

import com.ecl.popcensus.model.EmploymentSector;
import com.ecl.popcensus.repository.EmploymentSectorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class EmploymentSectorService {
    private final EmploymentSectorRepository repository;

    public EmploymentSectorService(EmploymentSectorRepository repository) {
        this.repository = repository;
    }

    public EmploymentSector create(EmploymentSector sector) {
        if (repository.existsByEmploymentSectorCode(sector.getEmploymentSectorCode())) {
            throw new IllegalStateException("Employment sector code already exists: " + sector.getEmploymentSectorCode());
        }
        return repository.save(sector);
    }

    public List<EmploymentSector> getAll() {
        return repository.findAll();
    }

    public EmploymentSector getById(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new IllegalStateException("Employment sector not found with ID: " + id));
    }

    public EmploymentSector getByCode(String code) {
        return repository.findByEmploymentSectorCode(code)
            .orElseThrow(() -> new IllegalStateException("Employment sector not found with code: " + code));
    }

    public EmploymentSector update(Long id, EmploymentSector sector) {
        EmploymentSector existing = getById(id);
        
        if (!existing.getEmploymentSectorCode().equals(sector.getEmploymentSectorCode()) &&
            repository.existsByEmploymentSectorCode(sector.getEmploymentSectorCode())) {
            throw new IllegalStateException("Employment sector code already exists: " + sector.getEmploymentSectorCode());
        }

        existing.setEmploymentSectorCode(sector.getEmploymentSectorCode());
        existing.setEmploymentSectorDesc(sector.getEmploymentSectorDesc());
        return repository.save(existing);
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new IllegalStateException("Employment sector not found with ID: " + id);
        }
        repository.deleteById(id);
    }
}
