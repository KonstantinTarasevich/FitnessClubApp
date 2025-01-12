package my.fitnessapp.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import my.fitnessapp.model.enums.RequestStatusEnum;

@Entity
@Table(name = "personal_training_requests")
public class PersonalTrainingRequestEntity extends BaseEntity {

    @NotNull(message = "User is required")
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "coach_id", nullable = false)
    private CoachEntity coach;

    @NotNull(message = "Status is required")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RequestStatusEnum status = RequestStatusEnum.PENDING;

    public UserEntity getUser() {
        return user;
    }

    public PersonalTrainingRequestEntity setUser(UserEntity user) {
        this.user = user;
        return this;
    }

    public CoachEntity getCoach() {
        return coach;
    }

    public PersonalTrainingRequestEntity setCoach(CoachEntity coach) {
        this.coach = coach;
        return this;
    }

    public RequestStatusEnum getStatus() {
        return status;
    }

    public PersonalTrainingRequestEntity setStatus(RequestStatusEnum status) {
        this.status = status;
        return this;
    }
}
