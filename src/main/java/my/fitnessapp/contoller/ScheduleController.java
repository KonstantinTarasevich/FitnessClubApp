package my.fitnessapp.contoller;


import my.fitnessapp.model.dto.ScheduleDTO;
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

    //запазване на тренировка:
    @PostMapping("/reserve/{scheduleId}")
    public String reserveSpot(@PathVariable Long scheduleId) {
        boolean success = scheduleService.reserveSpot(scheduleId);
        if (success) {
            return "Мястото е успешно запазено!";
        } else {
            return "Няма налични свободни места.";
        }
    }

    // Създаване на нова тренировка
    @PostMapping("/create-workout")
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        return scheduleService.createSchedule(scheduleDTO);
    }

    // Редактиране на съществуваща тренировка
    @PutMapping("/update-workout/{scheduleId}")
    public ScheduleDTO updateSchedule(@PathVariable Long scheduleId, @RequestBody ScheduleDTO scheduleDTO) {
        return scheduleService.updateSchedule(scheduleId, scheduleDTO);
    }

    // Премахване на тренировка
    @DeleteMapping("/delete-workout/{scheduleId}")
    public String deleteSchedule(@PathVariable Long scheduleId) {
        scheduleService.deleteSchedule(scheduleId);
        return "Тренировката е успешно премахната.";
    }

}