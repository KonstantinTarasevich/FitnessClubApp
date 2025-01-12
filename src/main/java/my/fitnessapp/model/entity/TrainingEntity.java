package my.fitnessapp.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

@Entity
@Table(name = "trainings")
public class TrainingEntity extends BaseEntity {

    @NotNull(message = "Training name is required")
    @Size(min = 3, max = 50, message = "Training name must be between 3 and 50 characters")
    @Column(nullable = false, unique = true)
    private String name;

    @ManyToOne
    @JoinColumn(name = "coach_id", nullable = false)
    private CoachEntity coach;

    @Positive(message = "Max participants must be a positive number")
    @Column(nullable = false, name = "max_participants")
    private int maxParticipants;

    @NotNull(message = "Date and time are required")
    @Column(nullable = false, name = "date_time")
    private LocalDateTime dateTime;

    public String getName() {
        return name;
    }

    public TrainingEntity setName(String name) {
        this.name = name;
        return this;
    }

    public CoachEntity getCoach() {
        return coach;
    }

    public TrainingEntity setCoach(CoachEntity coach) {
        this.coach = coach;
        return this;
    }

    public int getMaxParticipants() {
        return maxParticipants;
    }

    public TrainingEntity setMaxParticipants(int maxParticipants) {
        this.maxParticipants = maxParticipants;
        return this;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public TrainingEntity setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
        return this;
    }
}
