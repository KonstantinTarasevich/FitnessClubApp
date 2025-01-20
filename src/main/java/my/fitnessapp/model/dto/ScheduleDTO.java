package my.fitnessapp.model.dto;

import java.time.LocalDateTime;

public class ScheduleDTO {

    private Long id;
    private String name;
    private String coach;
    private int maxParticipants;
    private int currentParticipants;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public ScheduleDTO(Long id, String name, String coach, int maxParticipants, LocalDateTime startTime, LocalDateTime endTime) {
        this.id = id;
        this.name = name;
        this.coach = coach;
        this.maxParticipants = maxParticipants;
        this.currentParticipants = currentParticipants;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCoach() {
        return coach;
    }

    public void setCoach(String coach) {
        this.coach = coach;
    }

    public int getMaxParticipants() {
        return maxParticipants;
    }

    public void setMaxParticipants(int maxParticipants) {
        this.maxParticipants = maxParticipants;
    }

    public int getCurrentParticipants() {
        return currentParticipants;
    }

    public void setCurrentParticipants(int currentParticipants) {
        this.currentParticipants = currentParticipants;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public ScheduleDTO() {

    }


}
