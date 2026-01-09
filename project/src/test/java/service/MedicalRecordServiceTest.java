package com.example.project.service;

import com.example.project.model.Client;
import com.example.project.model.MedicalRecord;
import com.example.project.model.Staff;
import com.example.project.repository.ClientRepository;
import com.example.project.repository.MedicalRecordRepository;
import com.example.project.repository.StaffRepository;

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
class MedicalRecordServiceTest {

    @Mock
    MedicalRecordRepository recordRepo;

    @Mock
    ClientRepository clientRepo;

    @Mock
    StaffRepository staffRepo;

    @InjectMocks
    MedicalRecordService service;

    @Test
    void createMedicalRecord_success() {
        when(clientRepo.findById(1L)).thenReturn(Optional.of(new Client()));
        when(staffRepo.findById(2L)).thenReturn(Optional.of(new Staff()));
        when(recordRepo.save(any())).thenAnswer(i -> i.getArgument(0));

        MedicalRecord record =
                service.createRecord(1L, 2L, "Test record");

        assertNotNull(record);
        assertNotNull(record.getCreatedAt());
    }

    @Test
    void createMedicalRecord_clientNotFound() {
        when(clientRepo.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class,
                () -> service.createRecord(1L, 2L, "Test"));
    }

    @Test
    void getRecordsByClient() {
        when(recordRepo.findByClientId(1L))
                .thenReturn(List.of(new MedicalRecord()));

        assertEquals(1, service.getRecordsByClient(1L).size());
    }
}
