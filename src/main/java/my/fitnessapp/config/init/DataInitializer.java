package my.fitnessapp.config.init;

import my.fitnessapp.model.entity.UserRoleEntity;
import my.fitnessapp.model.enums.RolesEnum;
import my.fitnessapp.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class DataInitializer {

    @Autowired
    private UserRoleRepository userRoleRepository;

    @PostConstruct
    public void initRoles() {
        if (userRoleRepository.count() == 0) {
            userRoleRepository.save(new UserRoleEntity().setRole(RolesEnum.USER));
            userRoleRepository.save(new UserRoleEntity().setRole(RolesEnum.ADMIN));
        }
    }
}