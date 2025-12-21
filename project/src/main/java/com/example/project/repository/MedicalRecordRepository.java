package com.example.project.repository;

import com.example.project.model.Appointment;
import com.example.project.model.MedicalRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Long> {

    List<MedicalRecord> findByClientId(Long clientId);

}
