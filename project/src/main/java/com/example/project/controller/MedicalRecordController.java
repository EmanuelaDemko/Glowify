package com.example.project.controller;

import com.example.project.model.MedicalRecord;
import com.example.project.service.MedicalRecordService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/records")
public class MedicalRecordController {

    private final MedicalRecordService service;

    public MedicalRecordController(MedicalRecordService service) {
        this.service = service;
    }

    @PostMapping
    public MedicalRecord create(@RequestBody MedicalRecord record) {
        return service.create(record);
    }

    @GetMapping
    public List<MedicalRecord> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public MedicalRecord getById(@PathVariable Long id) {
        return service.getById(id);
    }
}
