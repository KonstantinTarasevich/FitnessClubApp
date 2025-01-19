package my.fitnessapp.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "trainings")
public class TrainingEntity extends BaseEntity {

    @NotNull(message = "Training name is required")
    @Size(min = 3, max = 50, message = "Training name must be between 3 and 50 characters")
    @Column(nullable = false)
    private String name;

    @NotNull(message = "Date is required")
    @Column(nullable = false, name = "start_date")
    private LocalDateTime startDate;

    @NotNull(message = "Date is required")
    @Column(nullable = false, name = "end_date")
    private LocalDateTime endDate;

    @NotNull(message = "Coach is required")
    @ManyToOne
    @JoinColumn(name = "coach_id", nullable = false)
    private CoachEntity coach;

    @ManyToMany
    @JoinTable(
            name = "training_users",
            joinColumns = @JoinColumn(name = "training_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<UserEntity> participants = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public TrainingEntity setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
        return this;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public TrainingEntity setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
        return this;
    }

    public CoachEntity getCoach() {
        return coach;
    }

    public void setCoach(CoachEntity coach) {
        this.coach = coach;
    }

    public List<UserEntity> getParticipants() {
        return participants;
    }

    public void setParticipants(List<UserEntity> participants) {
        this.participants = participants;
    }
}
