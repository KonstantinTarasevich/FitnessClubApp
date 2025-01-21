package my.fitnessapp.repository;

import my.fitnessapp.model.entity.CoachEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoachRepository extends JpaRepository<CoachEntity, Long> {

    List<CoachEntity> findAll();
}
