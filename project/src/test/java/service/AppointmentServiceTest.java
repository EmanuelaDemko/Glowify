package service;

import com.example.project.repository.AppointmentRepository;
import com.example.project.service.AppointmentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
class AppointmentServiceTest {

    @Mock
    AppointmentRepository appointmentRepository;

    @InjectMocks
    AppointmentService appointmentService;

    @Test
    void contextLoads() {
        assertNotNull(appointmentService);
    }
}
