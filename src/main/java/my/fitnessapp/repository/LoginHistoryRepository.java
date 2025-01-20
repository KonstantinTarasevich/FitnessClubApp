package my.fitnessapp.repository;

import my.fitnessapp.model.entity.LoginHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface LoginHistoryRepository extends JpaRepository<LoginHistoryEntity, Long> {
    List<LoginHistoryEntity> findByUserId(Long userId);

    long countByLoginTimeBetween(LocalDateTime startDate, LocalDateTime endDate);
}
