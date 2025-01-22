package my.fitnessapp.contoller;

import my.fitnessapp.model.dto.UserRegisterDTO;
import my.fitnessapp.service.impl.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @DeleteMapping("/delete/{userId}")
    public String deleteUser(@PathVariable("userId") Long id) {
        userService.delete(id);
        return "redirect:/";
    }

    @PostMapping("/edit/{id}")
    public String editUser(@PathVariable Long id, @ModelAttribute UserRegisterDTO userRegisterDTO) {
        userService.updateUser(id, userRegisterDTO);
        return "redirect:/members";
    }
}
