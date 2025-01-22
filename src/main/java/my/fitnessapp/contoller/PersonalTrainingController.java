package my.fitnessapp.contoller;

import my.fitnessapp.model.entity.PersonalTrainingRequestEntity;
import my.fitnessapp.model.enums.RequestStatusEnum;
import my.fitnessapp.service.impl.PersonalTrainingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PersonalTrainingController {

    private final PersonalTrainingServiceImpl personalTrainingService;

    @Autowired
    public PersonalTrainingController(PersonalTrainingServiceImpl personalTrainingService) {
        this.personalTrainingService = personalTrainingService;
    }

    @GetMapping("/workoutRequests")
    public String getTrainingRequests(Model model) {
        List<PersonalTrainingRequestEntity> requests = personalTrainingService.getAllRequests();
        if (requests == null) {
            System.out.println("Warning: No training requests found.");
            requests = List.of();
        } else {
            System.out.println("Fetched " + requests.size() + " training requests");
        }
        model.addAttribute("trainingRequests", requests);
        return "workoutRequests";
    }

    @PostMapping("/workoutRequests/{id}/approve")
    public String approveRequest(@PathVariable Long id) {
        System.out.println("Approving request ID: " + id);
        personalTrainingService.approveOrRejectRequest(id, RequestStatusEnum.APPROVED);
        return "redirect:/workoutRequests";
    }

    @PostMapping("/workoutRequests/{id}/reject")
    public String rejectRequest(@PathVariable Long id) {
        System.out.println("Rejecting request ID: " + id);
        personalTrainingService.approveOrRejectRequest(id, RequestStatusEnum.REJECTED);
        return "redirect:/workoutRequests";
    }
}
