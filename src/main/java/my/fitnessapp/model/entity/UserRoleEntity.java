package my.fitnessapp.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import my.fitnessapp.model.enums.RolesEnum;

@Entity
@Table(name = "roles")
public class UserRoleEntity extends BaseEntity {

    @NotNull
    @Column(unique = true)
    @Enumerated(EnumType.STRING)
    private RolesEnum role;

    public RolesEnum getRole() {
        return role;
    }

    public UserRoleEntity setRole(RolesEnum role) {
        this.role = role;
        return this;
    }
}
