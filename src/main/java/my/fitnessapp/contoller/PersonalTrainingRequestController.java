package my.fitnessapp.contoller;

import jakarta.validation.Valid;
import my.fitnessapp.model.dto.CoachDTO;
import my.fitnessapp.model.dto.PersonalTrainingRequestDTO;
import my.fitnessapp.service.CoachService;
import my.fitnessapp.service.PersonalTrainingRequestService;
import my.fitnessapp.service.impl.CoachServiceImpl;
import my.fitnessapp.service.impl.PersonalTrainingRequestServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class PersonalTrainingRequestController {

    private final PersonalTrainingRequestServiceImpl personalTrainingRequestService;
    private final CoachServiceImpl coachService;

    public PersonalTrainingRequestController(PersonalTrainingRequestServiceImpl personalTrainingRequestService, CoachServiceImpl coachService) {
        this.personalTrainingRequestService = personalTrainingRequestService;
        this.coachService = coachService;
    }

    @GetMapping("/request-workout")
    public String personalTrainingRequest(Model model) {
        List<PersonalTrainingRequestDTO> personalTrainingRequest = personalTrainingRequestService.getRequestsForCurrentUser();
        model.addAttribute("personalTrainingRequest", personalTrainingRequest);
        List<CoachDTO> coaches = coachService.getAllCoaches();
        model.addAttribute("coaches", coaches);
        return "personal-workout";
    }

    @PostMapping("/request-workout")
    public String addWorkoutRequest(
            @Valid PersonalTrainingRequestDTO data,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {

            if (bindingResult.hasErrors()) {
                redirectAttributes.addFlashAttribute("personalTrainingData", data);
                redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.personalTrainingData", bindingResult);
                return "personal-workout";
            }

            boolean success = personalTrainingRequestService.addTrainingRequest(data);

            if (!success) {
                redirectAttributes.addFlashAttribute("personalTrainingData", data);
                return "redirect:/request-workout";
            }
            return "redirect:/";
    }
}
