package service;

import com.example.project.repository.MedicalRecordRepository;
import com.example.project.service.MedicalRecordService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
class MedicalRecordServiceTest {

    @Mock
    MedicalRecordRepository medicalRecordRepository;

    @InjectMocks
    MedicalRecordService medicalRecordService;

    @Test
    void contextLoads() {
        assertNotNull(medicalRecordService);
    }
}

