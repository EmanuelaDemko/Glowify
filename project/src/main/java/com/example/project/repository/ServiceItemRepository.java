package com.example.project.repository;

import com.example.project.model.ServiceItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ServiceItemRepository extends JpaRepository<ServiceItem, Long> {

    Optional<ServiceItem> findByTitle(String title);
}
