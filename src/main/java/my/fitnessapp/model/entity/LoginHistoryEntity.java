package my.fitnessapp.model.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "login_history")
public class LoginHistoryEntity extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @Column(nullable = false, name = "login_time")
    private LocalDateTime loginTime;

    public LoginHistoryEntity(UserEntity user, LocalDateTime loginTime) {
        this.user = user;
        this.loginTime = loginTime;
    }

    public LoginHistoryEntity() {

    }

    public UserEntity getUser() {
        return user;
    }

    public LoginHistoryEntity setUser(UserEntity user) {
        this.user = user;
        return this;
    }

    public LocalDateTime getLoginTime() {
        return loginTime;
    }

    public LoginHistoryEntity setLoginTime(LocalDateTime loginTime) {
        this.loginTime = loginTime;
        return this;
    }
}
