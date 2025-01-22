package my.fitnessapp.service;

import my.fitnessapp.model.dto.CoachDTO;

import java.util.List;

public interface CoachService {
    boolean addCoach(CoachDTO coachDTO);
    List<CoachDTO> getAllCoaches();

    CoachDTO getCoachById(Long id);
}
