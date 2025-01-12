package my.fitnessapp.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity {

    @NotNull(message = "Name is required")
    @Size(min = 3, max = 20, message = "Name must be between 3 and 20 characters")
    @Column(nullable = false, name = "full_name")
    private String name;

    @NotNull(message = "Username is required")
    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters")
    @Column(unique = true, nullable = false)
    private String username;

    @NotNull(message = "Password is required")
    @Size(min = 3, message = "Password must be between 3 and 20 characters")
    @Column(nullable = false)
    private String password;

    @NotNull(message = "Email is required")
    @Size(min = 3, max = 30, message = "Email must be between 3 and 30 characters")
    @Email(message = "Please provide a valid email address.")
    @Column(unique = true, nullable = false)
    private String email;

    @NotNull(message = "Phone number is required")
    @Size(min = 3, max = 20, message = "Phone number must be between 3 and 20 characters")
    @Column(nullable = false)
    private String phone;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<PersonalTrainingRequestEntity> trainingRequests = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<UserRoleEntity> roles = new ArrayList<>();

    public String getName() {
        return name;
    }

    public UserEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserEntity setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserEntity setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserEntity setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public UserEntity setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public Set<PersonalTrainingRequestEntity> getTrainingRequests() {
        return trainingRequests;
    }

    public UserEntity setTrainingRequests(Set<PersonalTrainingRequestEntity> trainingRequests) {
        this.trainingRequests = trainingRequests;
        return this;
    }

    public List<UserRoleEntity> getRoles() {
        return roles;
    }

    public UserEntity setRoles(List<UserRoleEntity> roles) {
        this.roles = roles;
        return this;
    }
}
