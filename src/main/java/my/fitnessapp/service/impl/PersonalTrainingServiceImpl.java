package my.fitnessapp.service.impl;

import my.fitnessapp.model.entity.PersonalTrainingRequestEntity;
import my.fitnessapp.model.entity.UserEntity;
import my.fitnessapp.model.entity.CoachEntity;
import my.fitnessapp.model.enums.RequestStatusEnum;
import my.fitnessapp.repository.PersonalTrainingRequestRepository;
import my.fitnessapp.repository.UserRepository;
import my.fitnessapp.repository.CoachRepository;
import my.fitnessapp.service.PersonalTrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PersonalTrainingServiceImpl implements PersonalTrainingService {

    @Autowired
    private PersonalTrainingRequestRepository personalTrainingRequestRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CoachRepository coachRepository;

    // Заявка за персонална тренировка
    @Override
    public PersonalTrainingRequestEntity requestPersonalTraining(Long userId, Long coachId, LocalDateTime requestedDateTime) {
        UserEntity user = userRepository.findById(userId).orElse(null);
        CoachEntity coach = coachRepository.findById(coachId).orElse(null);

        if (user == null || coach == null) {
            return null;
        }

        PersonalTrainingRequestEntity request = new PersonalTrainingRequestEntity();
        request.setUser(user)
                .setCoach(coach)
                .setStatus(RequestStatusEnum.PENDING);

        return personalTrainingRequestRepository.save(request);
    }


    @Override
    public PersonalTrainingRequestEntity approveOrRejectRequest(Long requestId, RequestStatusEnum status) {
        PersonalTrainingRequestEntity request = personalTrainingRequestRepository.findById(requestId).orElse(null);
        if (request != null) {
            request.setStatus(status);
            return personalTrainingRequestRepository.save(request);
        }
        return null;
    }


    @Override
    public List<PersonalTrainingRequestEntity> getMostPopularTrainings() {

        return personalTrainingRequestRepository.findAll();
    }
}
