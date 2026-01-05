package service;

import com.example.project.repository.UserRepository;
import com.example.project.service.AuthService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
class AuthServiceTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    AuthService authService;

    @Test
    void contextLoads() {
        assertNotNull(authService);
    }
}

