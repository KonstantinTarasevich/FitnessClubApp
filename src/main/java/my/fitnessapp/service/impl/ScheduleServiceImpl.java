package my.fitnessapp.service.impl;

import my.fitnessapp.model.dto.ScheduleDTO;
import my.fitnessapp.model.entity.ScheduleEntity;
import my.fitnessapp.repository.ScheduleRepository;
import my.fitnessapp.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;

    @Autowired
    public ScheduleServiceImpl(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public ScheduleDTO createSchedule(ScheduleDTO scheduleDTO) {
        ScheduleEntity schedule = new ScheduleEntity();
        schedule.setName(scheduleDTO.getName());
        schedule.setCoach(scheduleDTO.getCoach());
        schedule.setMaxParticipants(scheduleDTO.getMaxParticipants());
        schedule.setCurrentParticipants(0);
        schedule.setStartTime(scheduleDTO.getStartTime());
        schedule.setEndTime(scheduleDTO.getEndTime());
        schedule = scheduleRepository.save(schedule);
        return convertToDTO(schedule);
    }

    @Override
    public ScheduleDTO updateSchedule(Long id, ScheduleDTO scheduleDTO) {
        ScheduleEntity schedule = scheduleRepository.findById(id).orElseThrow(() -> new RuntimeException("Schedule not found"));
        schedule.setName(scheduleDTO.getName());
        schedule.setCoach(scheduleDTO.getCoach());
        schedule.setMaxParticipants(scheduleDTO.getMaxParticipants());
        schedule.setStartTime(scheduleDTO.getStartTime());
        schedule.setEndTime(scheduleDTO.getEndTime());
        schedule = scheduleRepository.save(schedule);
        return convertToDTO(schedule);
    }

    @Override
    public void deleteSchedule(Long id) {
        scheduleRepository.deleteById(id);
    }

    @Override
    public List<ScheduleDTO> getAllSchedules() {
        return scheduleRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ScheduleDTO> getAllSchedulesOrderedByDateTime() {
        return scheduleRepository.findAllByOrderByStartTimeAsc()
                .stream()
                .map(schedule -> new ScheduleDTO(
                        schedule.getId(),
                        schedule.getName(),
                        schedule.getCoach(),
                        schedule.getMaxParticipants(),
                        schedule.getStartTime(),
                        schedule.getEndTime()
                ))
                .collect(Collectors.toList());
    }


    private ScheduleDTO convertToDTO(ScheduleEntity entity) {
        ScheduleDTO dto = new ScheduleDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setCoach(entity.getCoach());
        dto.setMaxParticipants(entity.getMaxParticipants());
        dto.setCurrentParticipants(entity.getCurrentParticipants());
        dto.setStartTime(entity.getStartTime());
        dto.setEndTime(entity.getEndTime());
        return dto;
    }

    public String getMostPopularTraining() {
        List<ScheduleEntity> allSchedules = scheduleRepository.findAll();


        ScheduleEntity mostPopularSchedule = allSchedules.stream()
                .max(Comparator.comparingInt(ScheduleEntity::getMaxParticipants))
                .orElse(null);

        return mostPopularSchedule != null ? mostPopularSchedule.getName() : "there is no favourite training";
    }


}
