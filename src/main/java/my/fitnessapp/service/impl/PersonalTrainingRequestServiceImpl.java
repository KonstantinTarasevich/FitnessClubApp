package my.fitnessapp.service.impl;

import my.fitnessapp.model.dto.PersonalTrainingRequestDTO;
import my.fitnessapp.model.entity.CoachEntity;
import my.fitnessapp.model.entity.PersonalTrainingRequestEntity;
import my.fitnessapp.model.entity.UserEntity;
import my.fitnessapp.repository.CoachRepository;
import my.fitnessapp.repository.PersonalTrainingRequestRepository;
import my.fitnessapp.service.PersonalTrainingRequestService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonalTrainingRequestServiceImpl implements PersonalTrainingRequestService {

    private final ModelMapper modelMapper;
    private final UserServiceImpl userService;
    private final CoachRepository coachRepository;
    private final PersonalTrainingRequestRepository personalTrainingRequestRepository;

    public PersonalTrainingRequestServiceImpl(ModelMapper modelMapper,
                                              UserServiceImpl userService,
                                              CoachRepository coachRepository,
                                              PersonalTrainingRequestRepository personalTrainingRequestRepository) {
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.coachRepository = coachRepository;
        this.personalTrainingRequestRepository = personalTrainingRequestRepository;
    }

    @Override
    public List<PersonalTrainingRequestDTO> getRequestsForCurrentUser() {
        UserEntity currentUser = userService.getCurrentUser();
        List<PersonalTrainingRequestEntity> requests = personalTrainingRequestRepository.findAllByUser(currentUser);

        return requests.stream()
                .map(request -> modelMapper.map(request, PersonalTrainingRequestDTO.class))
                .toList();
    }

    @Override
    public boolean addTrainingRequest(PersonalTrainingRequestDTO data) {
        PersonalTrainingRequestEntity requestEntity = modelMapper.map(data, PersonalTrainingRequestEntity.class);

        UserEntity currentUser = userService.getCurrentUser();
        requestEntity.setUser(currentUser);

        CoachEntity coach = coachRepository.findById(data.getCoachId())
                .orElseThrow(() -> new IllegalArgumentException("Coach not found with ID: " + data.getCoachId()));
        requestEntity.setCoach(coach);

        requestEntity.setDescription(data.getDescription());

        personalTrainingRequestRepository.save(requestEntity);

        return true;
    }
}