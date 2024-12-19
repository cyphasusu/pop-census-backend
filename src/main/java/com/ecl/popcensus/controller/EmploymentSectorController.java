package com.ecl.popcensus.controller;

import com.ecl.popcensus.model.EmploymentSector;
import com.ecl.popcensus.service.EmploymentSectorService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/employment-sectors")
@SecurityRequirement(name = "bearerAuth")
public class EmploymentSectorController {
   private final EmploymentSectorService service;

   public EmploymentSectorController(EmploymentSectorService service) {
       this.service = service;
   }

   @PostMapping
   public ResponseEntity<EmploymentSector> create(@Valid @RequestBody EmploymentSector sector) {
       return ResponseEntity.ok(service.create(sector));
   }

   @GetMapping
   public ResponseEntity<List<EmploymentSector>> getAll() {
       return ResponseEntity.ok(service.getAll());
   }

   @GetMapping("/{id}")
   public ResponseEntity<EmploymentSector> getById(@PathVariable Long id) {
       return ResponseEntity.ok(service.getById(id));
   }

   @GetMapping("/code/{code}")
   public ResponseEntity<EmploymentSector> getByCode(@PathVariable String code) {
       return ResponseEntity.ok(service.getByCode(code));
   }

   @PutMapping("/{id}")
   public ResponseEntity<EmploymentSector> update(
       @PathVariable Long id,
       @Valid @RequestBody EmploymentSector sector
   ) {
       return ResponseEntity.ok(service.update(id, sector));
   }

   @DeleteMapping("/{id}")
   public ResponseEntity<Void> delete(@PathVariable Long id) {
       service.delete(id);
       return ResponseEntity.ok().build();
   }
}
