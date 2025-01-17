package my.fitnessapp.contoller;

import jakarta.validation.Valid;
import my.fitnessapp.model.dto.LoginHistoryDTO;
import my.fitnessapp.model.dto.UserRegisterDTO;
import my.fitnessapp.service.LoginHistoryService;
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
@RequestMapping("/admin-panel")
public class AdminController {

    private final AdminServiceImpl adminService;
    private final UserServiceImpl userService;
    private final LoginHistoryServiceImpl loginHistoryService;

    public AdminController(AdminServiceImpl adminService, UserServiceImpl userService, LoginHistoryServiceImpl loginHistoryService) {
        this.adminService = adminService;
        this.userService = userService;
        this.loginHistoryService = loginHistoryService;
    }

    @ModelAttribute("registerData")
    public UserRegisterDTO registerDTO(){
        return new UserRegisterDTO();
    }

    @GetMapping()
    public String adminPanel(Model model) {

        model.addAttribute("allUsers", userService.getAllUserDetails());

        return "admin-panel";
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
}
