package com.example.project.services;

import com.example.project.model.Client;
import com.example.project.model.MedicalRecord;
import com.example.project.model.Staff;
import com.example.project.repository.ClientRepository;
import com.example.project.repository.MedicalRecordRepository;
import com.example.project.repository.StaffRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class MedicalRecordService {

    private final MedicalRecordRepository medicalRecordRepository;
    private final ClientRepository clientRepository;
    private final StaffRepository staffRepository;

    public MedicalRecordService(MedicalRecordRepository medicalRecordRepository,
                                ClientRepository clientRepository,
                                StaffRepository staffRepository) {
        this.medicalRecordRepository = medicalRecordRepository;
        this.clientRepository = clientRepository;
        this.staffRepository = staffRepository;
    }


    public MedicalRecord createRecord(Long clientId, Long staffId, String description) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client not found"));

        Staff staff = staffRepository.findById(staffId)
                .orElseThrow(() -> new RuntimeException("Staff not found"));

        MedicalRecord record = new MedicalRecord();
        record.setClient(client);
        record.setStaff(staff);
        record.setDescription(description);
        record.setCreatedAt(LocalDateTime.now());

        return medicalRecordRepository.save(record);
    }

    public List<MedicalRecord> getRecordsByClient(Long clientId) {
        return medicalRecordRepository.findByClientId(clientId);
    }


    public void deleteRecord(Long id) {
        medicalRecordRepository.deleteById(id);
    }
}

