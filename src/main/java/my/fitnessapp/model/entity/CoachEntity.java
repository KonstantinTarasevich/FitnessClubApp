package my.fitnessapp.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "coaches")
public class CoachEntity extends BaseEntity {

    @NotNull(message = "Coach name is required")
    @Size(min = 3, max = 50, message = "Coach name must be between 3 and 50 characters")
    @Column(nullable = false)
    private String name;

    @Size(max = 100, message = "Description cannot exceed 100 characters")
    @Column(nullable = true)
    private String description;

    @Size(max = 50, message = "Specialization cannot exceed 50 characters")
    @Column(nullable = true)
    private String specialization;

    @NotNull(message = "Phone number is required")
    @Size(min = 3, max = 20, message = "Phone number must be between 3 and 20 characters")
    @Column(nullable = false)
    private String phone;

    public CoachEntity() {
    }

    public String getName() {
        return name;
    }

    public CoachEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public CoachEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getSpecialization() {
        return specialization;
    }

    public CoachEntity setSpecialization(String specialization) {
        this.specialization = specialization;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public CoachEntity setPhone(String phone) {
        this.phone = phone;
        return this;
    }
}
