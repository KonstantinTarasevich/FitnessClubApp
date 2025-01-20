package my.fitnessapp.contoller;


import my.fitnessapp.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;

    @Autowired
    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @GetMapping
    public String getAllSchedules(Model model) {

        model.addAttribute("allSchedules", scheduleService.getAllSchedulesOrderedByDateTime());
        return "schedule";
    }
}
