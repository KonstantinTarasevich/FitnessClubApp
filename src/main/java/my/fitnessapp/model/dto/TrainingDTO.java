package my.fitnessapp.model.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public class TrainingDTO {

    @NotNull(message = "Training name is required")
    @Size(min = 3, max = 50, message = "Training name must be between 3 and 50 characters")
    private String name;

    @NotNull(message = "Coach ID is required")
    private Long coachId;

    @NotNull(message = "Maximum participants are required")
    @Min(value = 1, message = "There must be at least one participant")
    private int maxParticipants;

    @NotNull(message = "Date and time are required")
    @Future(message = "Date and time must be in the future")
    private LocalDateTime dateTime;

    public String getName() {
        return name;
    }

    public TrainingDTO setName(String name) {
        this.name = name;
        return this;
    }

    public Long getCoachId() {
        return coachId;
    }

    public TrainingDTO setCoachId(Long coachId) {
        this.coachId = coachId;
        return this;
    }

    public int getMaxParticipants() {
        return maxParticipants;
    }

    public TrainingDTO setMaxParticipants(int maxParticipants) {
        this.maxParticipants = maxParticipants;
        return this;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public TrainingDTO setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
        return this;
    }
}
