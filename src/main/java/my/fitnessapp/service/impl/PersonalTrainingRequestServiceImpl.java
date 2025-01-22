package my.fitnessapp.service.impl;

import my.fitnessapp.model.dto.CoachDTO;
import my.fitnessapp.model.dto.PersonalTrainingRequestDTO;
import my.fitnessapp.model.entity.CoachEntity;
import my.fitnessapp.model.entity.PersonalTrainingRequestEntity;
import my.fitnessapp.model.entity.UserEntity;
import my.fitnessapp.repository.PersonalTrainingRequestRepository;
import my.fitnessapp.service.CoachService;
import my.fitnessapp.service.PersonalTrainingRequestService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonalTrainingRequestServiceImpl implements PersonalTrainingRequestService {

    private final ModelMapper modelMapper;
    private final UserServiceImpl userService;
    private final CoachService coachService;
    private final PersonalTrainingRequestRepository personalTrainingRequestRepository;

    public PersonalTrainingRequestServiceImpl(ModelMapper modelMapper,
                                              UserServiceImpl userService,
                                              CoachService coachService,
                                              PersonalTrainingRequestRepository personalTrainingRequestRepository) {
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.coachService = coachService;
        this.personalTrainingRequestRepository = personalTrainingRequestRepository;
    }

    @Override
    public List<PersonalTrainingRequestDTO> getRequestsForCurrentUser() {
        UserEntity currentUser = userService.getCurrentUser();
        List<PersonalTrainingRequestEntity> requests = personalTrainingRequestRepository.findAllByUser(currentUser);

        return requests.stream()
                .map(request -> {
                    PersonalTrainingRequestDTO dto = modelMapper.map(request, PersonalTrainingRequestDTO.class);
                    CoachDTO coach = coachService.getCoachById(dto.getCoachId());
                    if (coach != null) {
                        dto.setCoachName(coach.getName()); // Populate coachName
                    }
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public boolean addTrainingRequest(PersonalTrainingRequestDTO data) {
        PersonalTrainingRequestEntity requestEntity = modelMapper.map(data, PersonalTrainingRequestEntity.class);

        UserEntity currentUser = userService.getCurrentUser();
        requestEntity.setUser(currentUser);

        requestEntity.setCoach(
                coachService.getCoachById(data.getCoachId()) != null
                        ? modelMapper.map(coachService.getCoachById(data.getCoachId()), CoachEntity.class)
                        : null
        );

        personalTrainingRequestRepository.save(requestEntity);
        return true;
    }
}
