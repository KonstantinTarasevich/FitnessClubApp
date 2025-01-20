package my.fitnessapp.contoller;

import jakarta.validation.Valid;
import my.fitnessapp.model.dto.LoginHistoryDTO;
import my.fitnessapp.model.dto.UserDetailsDTO;
import my.fitnessapp.model.dto.UserRegisterDTO;
import my.fitnessapp.model.dto.CoachDTO;
import my.fitnessapp.service.CoachService;
import my.fitnessapp.service.ScheduleService;
import my.fitnessapp.service.impl.AdminServiceImpl;
import my.fitnessapp.service.impl.LoginHistoryServiceImpl;
import my.fitnessapp.service.impl.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class AdminController {

    private final AdminServiceImpl adminService;
    private final UserServiceImpl userService;
    private final LoginHistoryServiceImpl loginHistoryService;
    private final ScheduleService scheduleService;
    private final CoachService coachService;

    public AdminController(AdminServiceImpl adminService, UserServiceImpl userService, LoginHistoryServiceImpl loginHistoryService, ScheduleService scheduleService, CoachService coachService) {
        this.adminService = adminService;
        this.userService = userService;
        this.loginHistoryService = loginHistoryService;
        this.scheduleService = scheduleService;
        this.coachService = coachService;
    }

    @ModelAttribute("registerData")
    public UserRegisterDTO registerDTO(){
        return new UserRegisterDTO();
    }

    @GetMapping("/admin-panel")
    public String adminPanel(Model model) {
        model.addAttribute("allUsers", userService.getAllUserDetails());

        long loginCount = loginHistoryService.getLoginsFromLastYearToNow();
        model.addAttribute("loginCount", loginCount);

        long usersCount = userService.getTotalRegisteredUsers();
        model.addAttribute("usersCount", usersCount);


        addPopularTrainingStats(model);


        model.addAttribute("allCoaches", coachService.getAllCoaches());

        return "admin-panel";
    }

    @GetMapping("/members")
    public String getAllUsersSorted(Model model) {
        List<UserDetailsDTO> users = userService.getAllUsersSortedByName();
        model.addAttribute("allUsers", users);
        return "members";
    }

    @GetMapping("/admin-register")
    public String showRegisterForm() {
        return "admin-register";
    }

    @PostMapping("/admin-register")
    public String doRegister(
            @Valid @ModelAttribute("registerData") UserRegisterDTO data,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes
    ) {
        if (bindingResult.hasErrors() || !data.getPassword().equals(data.getConfirmPassword())) {
            if (!data.getPassword().equals(data.getConfirmPassword())) {
                bindingResult.rejectValue("confirmPassword", "error.confirmPassword", "Passwords do not match");
            }

            redirectAttributes.addFlashAttribute("registerData", data);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.registerData", bindingResult);

            return "redirect:/admin-panel/admin-register";
        }

        boolean success = adminService.registerAdmin(data);

        if (!success) {
            redirectAttributes.addFlashAttribute("registerData", data);
            redirectAttributes.addFlashAttribute("errorMessage", "Username already exists.");
            return "redirect:/admin-panel/admin-register";
        }

        return "redirect:/admin-panel";
    }

    @GetMapping("/{userId}/login-history")
    public List<LoginHistoryDTO> getLoginHistory(@PathVariable Long userId) {
        return loginHistoryService.getLoginHistoryByUserId(userId);
    }


    private void addPopularTrainingStats(Model model) {
        String mostPopularTraining = scheduleService.getMostPopularTraining();
        model.addAttribute("mostPopularTraining", mostPopularTraining);
    }

    @GetMapping("/admin-panel/add-coach")
    public String showAddCoachForm(Model model) {
        model.addAttribute("coachDTO", new CoachDTO());
        return "add-coach-form";
    }


    @PostMapping("/admin-panel/add-coach")
    public String addCoach(@ModelAttribute("coachDTO") CoachDTO coachDTO,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {


        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("error", "Моля попълнете всички полета правилно.");
            return "redirect:/admin-panel";
        }

        try {

            coachService.addCoach(coachDTO);
            redirectAttributes.addFlashAttribute("message", "Треньорът беше успешно добавен!");
            return "redirect:/admin-panel";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Възникна грешка при добавянето на треньора.");
            return "redirect:/admin-panel";  
        }
    }
}
