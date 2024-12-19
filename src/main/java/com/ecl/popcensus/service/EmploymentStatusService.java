// Services
package com.ecl.popcensus.service;

import com.ecl.popcensus.model.EmploymentStatus;
import com.ecl.popcensus.repository.EmploymentStatusRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class EmploymentStatusService {
    private final EmploymentStatusRepository repository;

    public EmploymentStatusService(EmploymentStatusRepository repository) {
        this.repository = repository;
    }

    public EmploymentStatus create(EmploymentStatus status) {
        if (repository.existsByEmploymentStatusCode(status.getEmploymentStatusCode())) {
            throw new IllegalStateException("Employment status code already exists: " + status.getEmploymentStatusCode());
        }
        return repository.save(status);
    }

    public List<EmploymentStatus> getAll() {
        return repository.findAll();
    }

    public EmploymentStatus getById(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new IllegalStateException("Employment status not found with ID: " + id));
    }

    public EmploymentStatus getByCode(String code) {
        return repository.findByEmploymentStatusCode(code)
            .orElseThrow(() -> new IllegalStateException("Employment status not found with code: " + code));
    }

    public EmploymentStatus update(Long id, EmploymentStatus status) {
        EmploymentStatus existing = getById(id);
        
        if (!existing.getEmploymentStatusCode().equals(status.getEmploymentStatusCode()) &&
            repository.existsByEmploymentStatusCode(status.getEmploymentStatusCode())) {
            throw new IllegalStateException("Employment status code already exists: " + status.getEmploymentStatusCode());
        }

        existing.setEmploymentStatusCode(status.getEmploymentStatusCode());
        existing.setEmploymentStatusDesc(status.getEmploymentStatusDesc());
        return repository.save(existing);
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new IllegalStateException("Employment status not found with ID: " + id);
        }
        repository.deleteById(id);
    }
}
