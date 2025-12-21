package com.example.project.controller;

import com.example.project.model.ServiceItem;
import com.example.project.repository.ServiceItemRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/service-items")
public class ServiceItemController {

    private final ServiceItemRepository repo;

    public ServiceItemController(ServiceItemRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<ServiceItem> getAll() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceItem> getById(@PathVariable Long id) {
        return repo.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ServiceItem create(@RequestBody ServiceItem item) {
        item.setId(null);
        return repo.save(item);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServiceItem> update(@PathVariable Long id, @RequestBody ServiceItem body) {
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
