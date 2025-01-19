package my.fitnessapp.service;

import my.fitnessapp.model.dto.ScheduleDTO;

import java.util.List;

public interface ScheduleService {

    ScheduleDTO createSchedule(ScheduleDTO scheduleDTO);
    ScheduleDTO updateSchedule(Long id, ScheduleDTO scheduleDTO);
    void deleteSchedule(Long id);
    List<ScheduleDTO> getAllSchedules();
}
