// MedicalRecordController.java
package com.example.project.controller;

import com.example.project.model.MedicalRecord;
import com.example.project.repository.MedicalRecordRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medical-records")
public class MedicalRecordController {

    private final MedicalRecordRepository repo;

    public MedicalRecordController(MedicalRecordRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<MedicalRecord> getAll() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicalRecord> getById(@PathVariable Long id) {
        return repo.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public MedicalRecord create(@RequestBody MedicalRecord record) {
        record.setId(null);
        return repo.save(record);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicalRecord> update(@PathVariable Long id, @RequestBody MedicalRecord body) {
        return repo.findById(id).map(existing -> {
            return ResponseEntity.ok(repo.save(existing));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!repo.existsById(id)) return ResponseEntity.notFound().build();
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
