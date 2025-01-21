package my.fitnessapp.service.impl;

import my.fitnessapp.model.dto.CoachDTO;
import my.fitnessapp.model.entity.CoachEntity;
import my.fitnessapp.repository.CoachRepository;
import my.fitnessapp.service.CoachService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
    public List<CoachDTO> getAllCoaches() {
        return coachRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }


    private CoachDTO mapToDTO(CoachEntity coachEntity) {
        CoachDTO coachDTO = new CoachDTO();
        coachDTO.setId(coachEntity.getId());
        coachDTO.setName(coachEntity.getName());
        return coachDTO;
    }
}
