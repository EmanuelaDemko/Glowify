package com.example.project.service;
import com.example.project.model.Notification;
import com.example.project.repository.NotificationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDateTime;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class NotificationServiceTest {
    @Mock
    NotificationRepository repo;
    @InjectMocks
    NotificationService service;
    @Test
    void createNotification_setsSentFalse() {
        Notification n = new Notification();
        when(repo.save(any())).thenAnswer(i -> i.getArgument(0));
        Notification saved = service.createNotification(n);
        assertFalse(saved.isSent());
    }
    @Test
    void sendPendingNotifications_marksAsSent() {
        Notification n = new Notification();
        n.setSent(false);
        n.setSendAt(LocalDateTime.now().minusMinutes(1));

        when(repo.findBySentFalseAndSendAtBefore(any()))
                .thenReturn(List.of(n));

        service.sendPendingNotifications();

        assertTrue(n.isSent());
        verify(repo).save(n);
    }
}
