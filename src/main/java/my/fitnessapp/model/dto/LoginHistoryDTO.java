package my.fitnessapp.model.dto;

import java.time.LocalDateTime;

public class LoginHistoryDTO {

    private Long userId;
    private String username;
    private LocalDateTime loginTime;

    public LoginHistoryDTO(Long userId, String username, LocalDateTime loginTime) {
        this.userId = userId;
        this.username = username;
        this.loginTime = loginTime;
    }

    public Long getUserId() {
        return userId;
    }

    public LoginHistoryDTO setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public LoginHistoryDTO setUsername(String username) {
        this.username = username;
        return this;
    }

    public LocalDateTime getLoginTime() {
        return loginTime;
    }

    public LoginHistoryDTO setLoginTime(LocalDateTime loginTime) {
        this.loginTime = loginTime;
        return this;
    }
}