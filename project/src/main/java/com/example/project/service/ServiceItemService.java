package com.example.project.service;

import com.example.project.model.ServiceItem;
import com.example.project.repository.ServiceItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceItemService {

    private final ServiceItemRepository serviceItemRepository;

    public ServiceItemService(ServiceItemRepository serviceItemRepository) {
        this.serviceItemRepository = serviceItemRepository;
    }

    public ServiceItem createService(ServiceItem serviceItem) {
        return serviceItemRepository.save(serviceItem);
    }

    public List<ServiceItem> getAllServices() {
        return serviceItemRepository.findAll();
    }


    public ServiceItem getServiceById(Long id) {
        return serviceItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Service not found with id: " + id));
    }


    public ServiceItem updateService(Long id, ServiceItem updatedService) {
        ServiceItem service = getServiceById(id);

        service.setTitle(updatedService.getTitle());
        service.setDescription(updatedService.getDescription());
        service.setPrice(updatedService.getPrice());
        service.setDurationMinutes(updatedService.getDurationMinutes());

        return serviceItemRepository.save(service);
    }

    public void deleteService(Long id) {
        serviceItemRepository.deleteById(id);
    }
}

