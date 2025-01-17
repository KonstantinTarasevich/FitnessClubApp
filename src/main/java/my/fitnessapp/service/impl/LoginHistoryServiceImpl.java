package my.fitnessapp.service.impl;

import my.fitnessapp.model.dto.LoginHistoryDTO;
import my.fitnessapp.model.entity.LoginHistoryEntity;
import my.fitnessapp.model.entity.UserEntity;
import my.fitnessapp.repository.LoginHistoryRepository;
import my.fitnessapp.service.LoginHistoryService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

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

    public List<LoginHistoryDTO> getLoginHistoryByUserId(Long userId) {
        return loginHistoryRepository.findByUserId(userId)
                .stream()
                .map(entity -> new LoginHistoryDTO(
                        entity.getUser().getId(),
                        entity.getUser().getUsername(),
                        entity.getLoginTime()))
                .toList();
    }
}
