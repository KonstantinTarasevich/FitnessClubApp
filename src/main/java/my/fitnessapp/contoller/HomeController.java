package my.fitnessapp.contoller;


import my.fitnessapp.model.UserData;
import my.fitnessapp.service.impl.ScheduleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {


    private final ScheduleServiceImpl scheduleService;

    @Autowired
    public HomeController(ScheduleServiceImpl scheduleService) {
        this.scheduleService = scheduleService;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("schedules", scheduleService.getAllSchedulesOrderedByDateTime());
        return "index";
    }
}
