package my.fitnessapp.model.dto;

import jakarta.validation.constraints.NotNull;
import my.fitnessapp.model.enums.RequestStatusEnum;

public class PersonalTrainingRequestDTO {

    @NotNull(message = "User ID is required")
    private Long userId;

    @NotNull(message = "Coach ID is required")
    private Long coachId;

    @NotNull(message = "Status is required")
    private RequestStatusEnum status;

    public Long getUserId() {
        return userId;
    }

    public PersonalTrainingRequestDTO setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public Long getCoachId() {
        return coachId;
    }

    public PersonalTrainingRequestDTO setCoachId(Long coachId) {
        this.coachId = coachId;
        return this;
    }

    public RequestStatusEnum getStatus() {
        return status;
    }

    public PersonalTrainingRequestDTO setStatus(RequestStatusEnum status) {
        this.status = status;
        return this;
    }
}
