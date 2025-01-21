package my.fitnessapp.service.impl;

import my.fitnessapp.model.dto.CoachDTO;
import my.fitnessapp.model.entity.CoachEntity;
import my.fitnessapp.repository.CoachRepository;
import my.fitnessapp.service.CoachService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class CoachServiceImpl implements CoachService {

    private final CoachRepository coachRepository;
    private final ModelMapper modelMapper;

    public CoachServiceImpl(CoachRepository coachRepository, ModelMapper modelMapper) {
        this.coachRepository = coachRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean addCoach(CoachDTO data) {

        CoachEntity coach = modelMapper.map(data, CoachEntity.class);

        coachRepository.save(coach);

        return true;
    }

    @Override
    public List<CoachEntity> getAllCoaches() {
        return coachRepository.findAll();
    }


}
