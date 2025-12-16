package com.example.project.repository;

import com.example.project.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

    List<Notification> findBySentFalseAndSendAtBefore(LocalDateTime time);
}
