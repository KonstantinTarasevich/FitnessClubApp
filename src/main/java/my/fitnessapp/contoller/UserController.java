package my.fitnessapp.contoller;

import my.fitnessapp.model.dto.UserRegisterDTO;
import my.fitnessapp.service.impl.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/users")
public class UserController {

    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PutMapping("/{id}")
    public String updateUser(@PathVariable Long id, @RequestBody UserRegisterDTO userRegisterDTO) {
        userService.updateUser(id, userRegisterDTO);
        return "redirect:/users";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.delete(id);

        return "redirect:/";
    }
}
