package my.fitnessapp.service.impl;

import my.fitnessapp.model.dto.CoachDTO;
import my.fitnessapp.model.entity.CoachEntity;
import my.fitnessapp.repository.CoachRepository;
import my.fitnessapp.service.CoachService;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class CoachServiceImpl implements CoachService {

    private final CoachRepository coachRepository;

    @Autowired
    public CoachServiceImpl(CoachRepository coachRepository) {
        this.coachRepository = coachRepository;
    }

    @Override
    public void addCoach(CoachDTO coachDTO) {
        CoachEntity coachEntity = new CoachEntity();
        coachEntity.setName(coachDTO.getName());
        coachEntity.setDescription(coachDTO.getDescription());
        coachEntity.setSpecialization(coachDTO.getSpecialization());
        coachEntity.setPhone(coachDTO.getPhone());

        coachRepository.save(coachEntity);
    }

    @Override
    public List<CoachEntity> getAllCoaches() {
        return coachRepository.findAll();
    }


}
