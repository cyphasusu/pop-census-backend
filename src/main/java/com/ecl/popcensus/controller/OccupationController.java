package com.ecl.popcensus.controller;

import com.ecl.popcensus.model.Occupation;
import com.ecl.popcensus.service.OccupationService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/occupations")
@SecurityRequirement(name = "bearerAuth")
public class OccupationController {
   private final OccupationService service;

   public OccupationController(OccupationService service) {
       this.service = service;
   }

   @PostMapping
   public ResponseEntity<Occupation> create(@Valid @RequestBody Occupation occupation) {
       return ResponseEntity.ok(service.create(occupation));
   }

   @GetMapping
   public ResponseEntity<List<Occupation>> getAll() {
       return ResponseEntity.ok(service.getAll());
   }

   @GetMapping("/{id}")
   public ResponseEntity<Occupation> getById(@PathVariable Long id) {
       return ResponseEntity.ok(service.getById(id));
   }

   @GetMapping("/code/{code}")
   public ResponseEntity<Occupation> getByCode(@PathVariable String code) {
       return ResponseEntity.ok(service.getByCode(code));
   }

   @PutMapping("/{id}")
   public ResponseEntity<Occupation> update(
       @PathVariable Long id,
       @Valid @RequestBody Occupation occupation
   ) {
       return ResponseEntity.ok(service.update(id, occupation));
   }

   @DeleteMapping("/{id}")
   public ResponseEntity<Void> delete(@PathVariable Long id) {
       service.delete(id);
       return ResponseEntity.ok().build();
   }
}
