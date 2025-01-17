package my.fitnessapp.service.impl;

import my.fitnessapp.model.UserData;
import my.fitnessapp.model.entity.UserEntity;
import my.fitnessapp.model.entity.UserRoleEntity;
import my.fitnessapp.model.enums.RolesEnum;
import my.fitnessapp.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final UserRepository userRepository;
    private final LoginHistoryServiceImpl loginHistoryService;

    public UserDetailsService(UserRepository userRepository, LoginHistoryServiceImpl loginHistoryService) {
        this.userRepository = userRepository;
        this.loginHistoryService = loginHistoryService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return userRepository
                .findByUsername(username)
                .map(user -> {
                    loginHistoryService.logLogin(user);
                    return map(user);
                })
                .orElseThrow(() -> new UsernameNotFoundException("User with username " + username + " not found"));

    }

    private static UserDetails map(UserEntity userEntity) {
        return new UserData(
                userEntity.getUsername(),
                userEntity.getPassword(),
                userEntity.getRoles().stream().map(UserRoleEntity::getRole).map(UserDetailsService::map).toList(),
                userEntity.getName()
        );
    }

    private static GrantedAuthority map(RolesEnum role) {
        return new SimpleGrantedAuthority("ROLE_" + role);
    }
}
