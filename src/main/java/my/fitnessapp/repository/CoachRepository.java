package my.fitnessapp.repository;

import my.fitnessapp.model.entity.CoachEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoachRepository {
    void save(CoachEntity coachEntity);

    List<CoachEntity> findAll();
}
