package my.fitnessapp.contoller;

import my.fitnessapp.model.entity.PersonalTrainingRequestEntity;
import my.fitnessapp.model.enums.RequestStatusEnum;
import my.fitnessapp.service.PersonalTrainingService;
import my.fitnessapp.service.impl.PersonalTrainingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/training-requests")
public class PersonalTrainingController {

    private final PersonalTrainingServiceImpl personalTrainingService;

    @Autowired
    public PersonalTrainingController(PersonalTrainingServiceImpl personalTrainingService) {
        this.personalTrainingService = personalTrainingService;
    }

    @GetMapping
    public String getTrainingRequests(Model model) {
        List<PersonalTrainingRequestEntity> requests = personalTrainingService.getAllRequests();
        model.addAttribute("trainingRequests", requests);
        return "workoutRequest";
    }

    @PostMapping("/{id}/approve")
    public String approveRequest(@PathVariable Long id) {
        personalTrainingService.approveOrRejectRequest(id, RequestStatusEnum.APPROVED);
        return "redirect:/training-requests";
    }

    @PostMapping("/{id}/reject")
    public String rejectRequest(@PathVariable Long id) {
        personalTrainingService.approveOrRejectRequest(id, RequestStatusEnum.REJECTED);
        return "redirect:/training-requests";
    }
}
