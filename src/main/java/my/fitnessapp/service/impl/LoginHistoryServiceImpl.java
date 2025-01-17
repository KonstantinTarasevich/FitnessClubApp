package my.fitnessapp.service.impl;

import my.fitnessapp.model.entity.LoginHistoryEntity;
import my.fitnessapp.model.entity.UserEntity;
import my.fitnessapp.repository.LoginHistoryRepository;
import my.fitnessapp.service.LoginHistoryService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class LoginHistoryServiceImpl implements LoginHistoryService {

    private final LoginHistoryRepository loginHistoryRepository;

    public LoginHistoryServiceImpl(LoginHistoryRepository loginHistoryRepository) {
        this.loginHistoryRepository = loginHistoryRepository;
    }

    public void logLogin(UserEntity user) {
        LoginHistoryEntity loginHistory = new LoginHistoryEntity();
        loginHistory.setUser(user);
        loginHistory.setLoginTime(LocalDateTime.now());
        loginHistoryRepository.save(loginHistory);
    }
}
