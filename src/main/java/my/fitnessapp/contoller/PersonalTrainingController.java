package my.fitnessapp.contoller;

import my.fitnessapp.model.entity.PersonalTrainingRequestEntity;
import my.fitnessapp.model.enums.RequestStatusEnum;
import my.fitnessapp.service.PersonalTrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/personal-trainings")
public class PersonalTrainingController {

    @Autowired
    private PersonalTrainingService personalTrainingService;

    // Заявка за персонална тренировка
    @PostMapping("/request")
    public PersonalTrainingRequestEntity requestPersonalTraining(@RequestParam Long userId, @RequestParam Long coachId, @RequestParam LocalDateTime requestedDateTime) {
        return personalTrainingService.requestPersonalTraining(userId, coachId, requestedDateTime);
    }

    // Одобряване или отхвърляне на заявка
    @PostMapping("/approve-reject/{requestId}")
    public PersonalTrainingRequestEntity approveOrRejectRequest(@PathVariable Long requestId, @RequestParam RequestStatusEnum status) {
        return personalTrainingService.approveOrRejectRequest(requestId, status);
    }

    // Получаване на най-популярните тренировки
    @GetMapping("/popular")
    public List<PersonalTrainingRequestEntity> getMostPopularTrainings() {
        return personalTrainingService.getMostPopularTrainings();
    }
}
