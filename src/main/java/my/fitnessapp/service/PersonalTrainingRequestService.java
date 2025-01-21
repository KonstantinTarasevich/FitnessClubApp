package my.fitnessapp.service;

import my.fitnessapp.model.dto.PersonalTrainingRequestDTO;

import java.util.List;

public interface PersonalTrainingRequestService {
    List<PersonalTrainingRequestDTO> getRequestsForCurrentUser();

    boolean addTrainingRequest(PersonalTrainingRequestDTO data);
}
