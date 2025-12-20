package com.example.project.controller;

import com.example.project.model.ServiceItem;
import com.example.project.service.ServiceItemService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/services")
public class ServiceItemController {

    private final ServiceItemService service;

    public ServiceItemController(ServiceItemService service) {
        this.service = service;
    }

    @PostMapping
    public ServiceItem create(@RequestBody ServiceItem s) {
        return service.create(s);
    }

    @GetMapping
    public List<ServiceItem> getAll() {
        return service.getAll();
    }

    @PutMapping("/{id}")
    public ServiceItem update(@PathVariable Long id, @RequestBody ServiceItem newData) {
        return service.update(id, newData);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}

