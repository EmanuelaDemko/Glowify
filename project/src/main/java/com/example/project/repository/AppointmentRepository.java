package com.example.project.repository;

import com.example.project.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    List<Appointment> findByStaffId(Long staffId);

    List<Appointment> findByClientId(Long clientId);

    List<Appointment> findByStartTimeBetween(
            LocalDateTime start,
            LocalDateTime end
    );
    List<Appointment> findByStaffIdAndStartTimeBetween(
            Long staffId,
            LocalDateTime start,
            LocalDateTime end
    );
}
