package my.fitnessapp.config.init;

import my.fitnessapp.model.dto.UserRegisterDTO;
import my.fitnessapp.model.entity.UserEntity;
import my.fitnessapp.model.entity.UserRoleEntity;
import my.fitnessapp.model.enums.RolesEnum;
import my.fitnessapp.repository.UserRepository;
import my.fitnessapp.repository.UserRoleRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class DataInitializer {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(UserRepository userRepository, UserRoleRepository userRoleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void init() {
        initRoles();

        if (!userRepository.findByUsername("admin").isPresent()) {
            createAdmin();
        }
    }

    private void initRoles() {
        if (userRoleRepository.count() == 0) {
            userRoleRepository.save(new UserRoleEntity().setRole(RolesEnum.USER));
            userRoleRepository.save(new UserRoleEntity().setRole(RolesEnum.ADMIN));
            System.out.println("Roles have been initialized");
        }
    }

    private void createAdmin() {
        UserRegisterDTO adminData = new UserRegisterDTO();
        adminData.setUsername("admin");
        adminData.setPassword("admin123");
        adminData.setConfirmPassword("admin123");
        adminData.setEmail("admin@fitnessapp.com");
        adminData.setName("Administrator");
        adminData.setPhone("1234567890");

        UserRoleEntity adminRole = userRoleRepository.findById(2)
                .orElseThrow(() -> new RuntimeException("Role not found"));

        System.out.println("Found Admin Role: " + adminRole.getRole());

        if (userRepository.findByUsername(adminData.getUsername()).isEmpty()) {
            UserEntity adminUser = new UserEntity();
            adminUser.setUsername(adminData.getUsername());
            adminUser.setPassword(passwordEncoder.encode(adminData.getPassword()));
            adminUser.setEmail(adminData.getEmail());
            adminUser.setName(adminData.getName());
            adminUser.setPhone(adminData.getPhone());

            adminUser.getRoles().add(adminRole);
            userRepository.save(adminUser);

            System.out.println("Admin user created successfully: " + adminUser.getUsername());
        } else {
            System.out.println("Admin user already exists.");
        }
    }
}