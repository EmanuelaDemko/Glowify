package com.example.project.service;

import com.example.project.model.ServiceItem;
import com.example.project.repository.ServiceItemRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ServiceItemServiceTest {

    @Mock
    ServiceItemRepository repo;

    @InjectMocks
    ServiceItemService service;

    @Test
    void createService_success() {
        ServiceItem item = new ServiceItem();
        when(repo.save(any())).thenReturn(item);

        assertNotNull(service.createService(item));
    }

    @Test
    void getAllServices() {
        when(repo.findAll()).thenReturn(List.of(new ServiceItem()));

        assertEquals(1, service.getAllServices().size());
    }

    @Test
    void getServiceById_notFound() {
        when(repo.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class,
                () -> service.getServiceById(1L));
    }

    @Test
    void updateService_updatesFields() {
        ServiceItem existing = new ServiceItem();
        when(repo.findById(1L)).thenReturn(Optional.of(existing));
        when(repo.save(any())).thenReturn(existing);

        ServiceItem updated = new ServiceItem();
        updated.setTitle("Updated");

        ServiceItem result = service.updateService(1L, updated);

        assertEquals("Updated", result.getTitle());
    }
}
