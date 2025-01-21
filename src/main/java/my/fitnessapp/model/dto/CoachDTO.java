package my.fitnessapp.model.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CoachDTO {

    private long id;

    @NotNull(message = "Coach name is required")
    @Size(min = 3, max = 50, message = "Coach name must be between 3 and 50 characters")
    private String name;

    @Size(max = 100, message = "Description cannot exceed 100 characters")
    private String description;

    @Size(max = 50, message = "Specialization cannot exceed 50 characters")
    private String specialization;

    @NotNull(message = "Phone number is required")
    @Size(min = 3, max = 20, message = "Phone number must be between 3 and 20 characters")
    private String phone;


    public String getName() {
        return name;
    }

    public CoachDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public CoachDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getSpecialization() {
        return specialization;
    }

    public CoachDTO setSpecialization(String specialization) {
        this.specialization = specialization;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public CoachDTO setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public long getId() {
        return id;
    }

    public CoachDTO setId(long id) {
        this.id = id;
        return this;
    }
}
