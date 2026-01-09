package com.example.project.service;
import com.example.project.model.*;
import com.example.project.repository.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.NoSuchElementException;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AppointmentServiceTest {

    @Mock
    AppointmentRepository appointmentRepo;

    @Mock
    ClientRepository clientRepo;

    @Mock
    StaffRepository staffRepo;

    @Mock
    ServiceItemRepository serviceRepo;

    @InjectMocks
    AppointmentService appointmentService;

    @Test
    void createAppointment_success() {
        Client client = new Client();
        Staff staff = new Staff();
        ServiceItem service = new ServiceItem();
        service.setDurationMinutes(60);

        when(clientRepo.findById(1L)).thenReturn(Optional.of(client));
        when(staffRepo.findById(2L)).thenReturn(Optional.of(staff));
        when(serviceRepo.findById(3L)).thenReturn(Optional.of(service));
        when(appointmentRepo.save(any())).thenAnswer(i -> i.getArgument(0));

        Appointment ap = appointmentService.createAppointment(
                1L, 2L, 3L, LocalDateTime.now());

        assertNotNull(ap);
        assertEquals(Appointment.Status.PENDING, ap.getStatus());
    }

    @Test
    void createAppointment_clientNotFound() {
        when(clientRepo.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class,
                () -> appointmentService.createAppointment(
                        1L, 2L, 3L, LocalDateTime.now()));
    }

    @Test
    void getAppointmentById_success() {
        Appointment ap = new Appointment();
        when(appointmentRepo.findById(1L)).thenReturn(Optional.of(ap));

        assertNotNull(appointmentService.getById(1L));
    }

    @Test
    void changeStatus_updatesCorrectly() {
        Appointment ap = new Appointment();
        when(appointmentRepo.findById(1L)).thenReturn(Optional.of(ap));
        when(appointmentRepo.save(any())).thenReturn(ap);

        Appointment updated =
                appointmentService.changeStatus(1L, Appointment.Status.CONFIRMED);

        assertEquals(Appointment.Status.CONFIRMED, updated.getStatus());
    }

    @Test
    void listAppointmentsForClient() {
        when(appointmentRepo.findByClientId(1L))
                .thenReturn(List.of(new Appointment()));

        assertEquals(1, appointmentService.listForClient(1L).size());
    }
}


