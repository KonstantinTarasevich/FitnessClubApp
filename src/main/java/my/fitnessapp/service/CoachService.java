package my.fitnessapp.service;

import my.fitnessapp.model.dto.CoachDTO;
import my.fitnessapp.model.entity.CoachEntity;

import java.util.List;

public interface CoachService {
    void addCoach(CoachDTO coachDTO);
    List<CoachEntity> getAllCoaches();
}
