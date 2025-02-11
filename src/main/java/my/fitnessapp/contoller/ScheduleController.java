package my.fitnessapp.contoller;

import my.fitnessapp.model.dto.ScheduleDTO;
import my.fitnessapp.service.ScheduleService;
import my.fitnessapp.service.impl.ScheduleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/schedule")
public class ScheduleController {

    private final ScheduleServiceImpl scheduleService;

    @Autowired
    public ScheduleController(ScheduleServiceImpl scheduleService) {
        this.scheduleService = scheduleService;
    }

    @GetMapping
    public String getAllSchedules(Model model) {
        model.addAttribute("schedules", scheduleService.getAllSchedulesOrderedByDateTime());
        return "schedule";
    }

    @PostMapping("/reserve/{scheduleId}")
    public String reserveSpot(@PathVariable Long scheduleId, Model model) {
        boolean success = scheduleService.reserveSpot(scheduleId);
        if (success) {
            model.addAttribute("message", "Мястото е успешно запазено!");
        } else {
            model.addAttribute("message", "Няма налични свободни места.");
        }
        return "redirect:/schedule";
    }


}
