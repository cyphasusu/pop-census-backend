package com.ecl.popcensus.service;

import com.ecl.popcensus.model.Occupation;
import com.ecl.popcensus.repository.OccupationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class OccupationService {
   private final OccupationRepository repository;

   public OccupationService(OccupationRepository repository) {
       this.repository = repository;
   }

   public Occupation create(Occupation occupation) {
       if (repository.existsByOccupationCode(occupation.getOccupationCode())) {
           throw new IllegalStateException("Occupation code already exists: " + occupation.getOccupationCode());
       }
       return repository.save(occupation);
   }

   public List<Occupation> getAll() {
       return repository.findAll();
   }

   public Occupation getById(Long id) {
       return repository.findById(id)
           .orElseThrow(() -> new IllegalStateException("Occupation not found with ID: " + id));
   }

   public Occupation getByCode(String code) {
       return repository.findByOccupationCode(code)
           .orElseThrow(() -> new IllegalStateException("Occupation not found with code: " + code));
   }

   public Occupation update(Long id, Occupation occupation) {
       Occupation existing = getById(id);
       
       if (!existing.getOccupationCode().equals(occupation.getOccupationCode()) &&
           repository.existsByOccupationCode(occupation.getOccupationCode())) {
           throw new IllegalStateException("Occupation code already exists: " + occupation.getOccupationCode());
       }

       existing.setOccupationCode(occupation.getOccupationCode());
       existing.setOccupationDesc(occupation.getOccupationDesc());
       return repository.save(existing);
   }

   public void delete(Long id) {
       if (!repository.existsById(id)) {
           throw new IllegalStateException("Occupation not found with ID: " + id);
       }
       repository.deleteById(id);
   }
}
