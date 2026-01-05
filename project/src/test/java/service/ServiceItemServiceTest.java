package service;
import com.example.project.repository.ServiceItemRepository;
import com.example.project.service.ServiceItemService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)

class ServiceItemServiceTest {

    @Mock

    ServiceItemRepository serviceItemRepository;

    @InjectMocks

    ServiceItemService serviceItemService;

    @Test

    void contextLoads() {

        assertNotNull(serviceItemService);

    }

}