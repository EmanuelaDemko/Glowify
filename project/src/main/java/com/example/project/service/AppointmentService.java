package com.example.project.service;

import com.example.project.DTO.AppointmentRequest;
import com.example.project.model.*;
import com.example.project.repository.*;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepo;
    private final ServiceItemRepository serviceRepo;
    private final ClientRepository clientRepo;
    private final StaffRepository staffRepo;

    public AppointmentService(AppointmentRepository appointmentRepo,
                              ServiceItemRepository serviceRepo,
                              ClientRepository clientRepo,
                              StaffRepository staffRepo) {
        this.appointmentRepo = appointmentRepo;
        this.serviceRepo = serviceRepo;
        this.clientRepo = clientRepo;
        this.staffRepo = staffRepo;
    }

    @Transactional
    public Appointment create(AppointmentRequest request) {
        return createAppointment(
                request.getClientId(),
                request.getStaffId(),
                request.getServiceId(),
                request.getDateTime()
        );
    }

    public List<Appointment> getAll() {
        return appointmentRepo.findAll();
    }

    public Appointment getById(Long id) {
        return appointmentRepo.findById(id).orElseThrow();
    }

    public void delete(Long id) {
        appointmentRepo.deleteById(id);
    }

    @Transactional
    public Appointment createAppointment(Long clientId, Long staffId, Long serviceId, LocalDateTime start) {
        Client client = clientRepo.findById(clientId).orElseThrow();
        Staff staff = staffRepo.findById(staffId).orElseThrow();
        ServiceItem service = serviceRepo.findById(serviceId).orElseThrow();

        Appointment ap = new Appointment();
        ap.setClient(client);
        ap.setStaff(staff);
        ap.setServiceItem(service);
        ap.setStartTime(start);
        ap.setEndTime(start.plusMinutes(service.getDurationMinutes() == null ? 60 : service.getDurationMinutes()));
        ap.setStatus(Appointment.Status.PENDING);

        return appointmentRepo.save(ap);
    }

    public List<Appointment> listAppointmentsForStaff(Long staffId, LocalDateTime from, LocalDateTime to) {
        return appointmentRepo.findByStaffIdAndStartTimeBetween(staffId, from, to);
    }

    public List<Appointment> listForClient(Long clientId) {
        return appointmentRepo.findByClientId(clientId);
    }

    public Appointment changeStatus(Long appointmentId, Appointment.Status status) {
        Appointment a = appointmentRepo.findById(appointmentId).orElseThrow();
        a.setStatus(status);
        return appointmentRepo.save(a);
    }
}
