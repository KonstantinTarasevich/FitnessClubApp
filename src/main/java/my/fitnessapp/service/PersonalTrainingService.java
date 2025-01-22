package my.fitnessapp.service;

import my.fitnessapp.model.entity.PersonalTrainingRequestEntity;
import my.fitnessapp.model.enums.RequestStatusEnum;

import java.time.LocalDateTime;
import java.util.List;

public interface PersonalTrainingService {

    PersonalTrainingRequestEntity requestPersonalTraining(Long userId, Long coachId, LocalDateTime requestedDateTime);

    PersonalTrainingRequestEntity approveOrRejectRequest(Long requestId, RequestStatusEnum status);

    List<PersonalTrainingRequestEntity> getMostPopularTrainings();

    List<PersonalTrainingRequestEntity> getAllRequests();
}
