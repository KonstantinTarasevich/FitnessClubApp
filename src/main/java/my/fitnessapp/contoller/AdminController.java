package my.fitnessapp.contoller;

import jakarta.validation.Valid;
import my.fitnessapp.model.dto.LoginHistoryDTO;
import my.fitnessapp.model.dto.UserDetailsDTO;
import my.fitnessapp.model.dto.UserRegisterDTO;
import my.fitnessapp.model.dto.CoachDTO;
import my.fitnessapp.service.impl.*;
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
    private final ScheduleServiceImpl scheduleService;
    private final CoachServiceImpl coachService;

    public AdminController(AdminServiceImpl adminService, UserServiceImpl userService, LoginHistoryServiceImpl loginHistoryService, ScheduleServiceImpl scheduleService, CoachServiceImpl coachService) {
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

    @ModelAttribute("coachData")
    public CoachDTO coachDTO() {
        return new CoachDTO();
    }

    @GetMapping("/admin-panel")
    public String adminPanel(Model model) {
        model.addAttribute("allUsers", userService.getAllUserDetails());

        long loginCount = loginHistoryService.getLoginsFromLastYearToNow();
        model.addAttribute("loginCount", loginCount);

        long usersCount = userService.getTotalRegisteredUsers();
        model.addAttribute("usersCount", usersCount);

        String mostPopularTraining = scheduleService.getMostPopularTraining();
        model.addAttribute("mostPopularTraining", mostPopularTraining);

        model.addAttribute("allCoaches", coachService.getAllCoaches());

        return "admin-panel";
    }

    @GetMapping("/members")
    public String getAllUsersSorted(Model model) {
        List<UserDetailsDTO> users = userService.getAllUsersSortedByName();
        model.addAttribute("allUsers", users);
        return "members";
    }

    @GetMapping("/register-admin")
    public String showRegisterForm() {
        return "register-admin";
    }

    @PostMapping("/register-admin")
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

            return "redirect:/admin-panel/register-admin";
        }

        boolean success = adminService.registerAdmin(data);

        if (!success) {
            redirectAttributes.addFlashAttribute("registerData", data);
            redirectAttributes.addFlashAttribute("errorMessage", "Username already exists.");
            return "redirect:/admin-panel/admin-register";
        }

        return "redirect:/admin-panel";
    }

    @GetMapping("/view-history/{id}")
    public String getLoginHistory(Model model ,@PathVariable Long id) {
        List<LoginHistoryDTO> loginHistory = loginHistoryService.getLoginHistoryByUserId(id);
        model.addAttribute("loginHistory", loginHistory);
        return "view-history";
    }


    @GetMapping("/admin-panel/add-coach")
    public String showAddCoachForm() {
        return "add-coach";
    }


    @PostMapping("/admin-panel/add-coach")
    public String addCoach(
            @Valid CoachDTO data,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes
    ) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("coachData", data);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.coachData", bindingResult);

            return "redirect:/admin-panel/add-coach";
        }

        boolean success = coachService.addCoach(data);

        if (!success) {
            redirectAttributes.addFlashAttribute("coachData", data);
            return "redirect:/admin-panel/add-coach";
        }
        return "redirect:/";
    }
}
