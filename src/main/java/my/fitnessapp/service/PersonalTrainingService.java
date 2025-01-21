package my.fitnessapp.service;

import my.fitnessapp.model.entity.PersonalTrainingRequestEntity;
import my.fitnessapp.model.enums.RequestStatusEnum;

import java.time.LocalDateTime;
import java.util.List;

public interface PersonalTrainingService {

    // Заявка за персонална тренировка
    PersonalTrainingRequestEntity requestPersonalTraining(Long userId, Long coachId, LocalDateTime requestedDateTime);

    // Одобряване или отхвърляне на заявка
    PersonalTrainingRequestEntity approveOrRejectRequest(Long requestId, RequestStatusEnum status);

    // Връща най-популярните тренировки
    List<PersonalTrainingRequestEntity> getMostPopularTrainings();
}
