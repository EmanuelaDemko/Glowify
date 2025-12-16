package com.example.project.services;

import com.example.project.model.Notification;
import com.example.project.repository.NotificationRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public Notification createNotification(Notification notification) {
        notification.setSent(false);
        return notificationRepository.save(notification);
    }


    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }


    @Scheduled(fixedRate = 60000)
    public void sendPendingNotifications() {
        List<Notification> pending =
                notificationRepository.findBySentFalseAndSendAtBefore(LocalDateTime.now());

        for (Notification n : pending) {
            // TODO: integrate email/SMS here
            n.setSent(true);
            notificationRepository.save(n);
        }
    }
}

