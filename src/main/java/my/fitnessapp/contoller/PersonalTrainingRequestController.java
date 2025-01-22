package my.fitnessapp.contoller;

import my.fitnessapp.model.dto.CoachDTO;
import my.fitnessapp.model.dto.PersonalTrainingRequestDTO;
import my.fitnessapp.service.impl.CoachServiceImpl;
import my.fitnessapp.service.impl.PersonalTrainingRequestServiceImpl;
import my.fitnessapp.service.impl.UserServiceImpl;
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
    private final UserServiceImpl userService;

    public PersonalTrainingRequestController(PersonalTrainingRequestServiceImpl personalTrainingRequestService, CoachServiceImpl coachService, UserServiceImpl userService) {
        this.personalTrainingRequestService = personalTrainingRequestService;
        this.coachService = coachService;
        this.userService = userService;
    }

    @GetMapping("/request-workout")
    public String personalTrainingRequest(Model model) {
        if (!model.containsAttribute("personalTrainingData")) {
            model.addAttribute("personalTrainingData", new PersonalTrainingRequestDTO());
        }
        System.out.println("Model data: " + model.getAttribute("personalTrainingData"));
        if (!model.containsAttribute("personalTrainingData")) {
            model.addAttribute("personalTrainingData", new PersonalTrainingRequestDTO());
        }

        List<PersonalTrainingRequestDTO> personalTrainingRequests = personalTrainingRequestService.getRequestsForCurrentUser();
        model.addAttribute("personalTrainingRequest", personalTrainingRequests);

        List<CoachDTO> coaches = coachService.getAllCoaches();
        model.addAttribute("coaches", coaches);

        return "personal-workout";
    }


    @PostMapping("/request-workout")
    public String addWorkoutRequest(
            PersonalTrainingRequestDTO data,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("personalTrainingData", data);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.personalTrainingData", bindingResult);
            return "redirect:/request-workout";
        }

        boolean success = personalTrainingRequestService.addTrainingRequest(data);
        if (!success) {
            redirectAttributes.addFlashAttribute("personalTrainingData", data);
            return "redirect:/request-workout";
        }

        return "redirect:/request-workout";
    }
}
