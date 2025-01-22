package my.fitnessapp.contoller;

import my.fitnessapp.model.dto.ScheduleDTO;
import my.fitnessapp.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller  // Changed to Controller to return HTML views
@RequestMapping("/schedule")
public class ScheduleController {

    private final ScheduleService scheduleService;

    @Autowired
    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    // Show the schedule page
    @GetMapping
    public String getAllSchedules(Model model) {
        model.addAttribute("schedules", scheduleService.getAllSchedulesOrderedByDateTime());
        return "schedule";  // Return the HTML page name
    }

    // Reserve a spot for a workout
    @PostMapping("/reserve/{scheduleId}")
    public String reserveSpot(@PathVariable Long scheduleId, Model model) {
        boolean success = scheduleService.reserveSpot(scheduleId);
        if (success) {
            model.addAttribute("message", "Мястото е успешно запазено!");
        } else {
            model.addAttribute("message", "Няма налични свободни места.");
        }
        return "redirect:/schedule";  // Redirect to the schedule page after booking
    }


}
