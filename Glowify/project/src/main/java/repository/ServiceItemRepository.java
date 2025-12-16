package repository;

import com.glowify.model.ServiceItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ServiceItemRepository extends JpaRepository<ServiceItem, Long> {

    Optional<ServiceItem> findByTitle(String title);
}
