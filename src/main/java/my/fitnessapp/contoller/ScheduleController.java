package my.fitnessapp.contoller;


import my.fitnessapp.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/schedule")
public class ScheduleController {

    private final ScheduleService scheduleService;

    @Autowired
    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @GetMapping()
    public String schedulePage(Model model) {
        model.addAttribute("Trainings", scheduleService.getAllSchedulesOrderedByDateTime());
        model.addAttribute("customMessage", "Welcome to the schedule page!");
        return "schedules";
    }
}


//    @GetMapping("/create")
//    public String showCreateForm(Model model) {
//        model.addAttribute("schedule", new ScheduleDTO());
//        return "schedule";
//    }
//
//    @PostMapping("/create")
//    public String createSchedule(@ModelAttribute ScheduleDTO scheduleDTO) {
//        scheduleService.createSchedule(scheduleDTO);
//        return "redirect:/schedules";
//    }
//
//    @DeleteMapping("/delete/{id}")
//    public String deleteSchedule(@PathVariable Long id) {
//        scheduleService.deleteSchedule(id);
//        return "redirect:/schedules";
//    }
//}
