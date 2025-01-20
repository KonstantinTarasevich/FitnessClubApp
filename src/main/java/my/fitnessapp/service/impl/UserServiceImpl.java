package my.fitnessapp.service.impl;

import my.fitnessapp.model.dto.UserDetailsDTO;
import my.fitnessapp.model.dto.UserRegisterDTO;
import my.fitnessapp.model.entity.UserEntity;
import my.fitnessapp.model.entity.UserRoleEntity;
import my.fitnessapp.repository.UserRepository;
import my.fitnessapp.repository.UserRoleRepository;
import my.fitnessapp.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserRoleRepository userRoleRepository;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder, UserRoleRepository userRoleRepository) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.userRoleRepository = userRoleRepository;
    }

    public String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }
    public UserEntity getCurrentUser() {
        String username = getCurrentUsername();
        return findUserByUsername(username);
    }

    public boolean register(UserRegisterDTO data) {
        try {
            Optional<UserEntity> existingUser = userRepository.findByUsername(data.getUsername());
            if (existingUser.isPresent()) {
                System.out.println("Username already exists: " + data.getUsername());
                return false;
            }

            UserEntity user = modelMapper.map(data, UserEntity.class);
            user.setPassword(passwordEncoder.encode(data.getPassword()));

            UserRoleEntity userRole = userRoleRepository.findById(1)
                    .orElseThrow(() -> new RuntimeException("Role not found"));

            user.getRoles().add(userRole);
            System.out.println("User before save: " + user);

            userRepository.save(user);
            System.out.println("User saved successfully with ID: " + user.getId());
            return true;
        } catch (Exception e) {
            System.out.println("Error during registration: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public UserEntity findUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with username " + username + " not found"));
    }

    public boolean updateUser(Long id, UserRegisterDTO data) {
        try {
            Optional<UserEntity> existingUserOptional = userRepository.findById(id);
            if (existingUserOptional.isEmpty()) {
                System.out.println("User with ID " + id + " does not exist.");
                return false;
            }

            UserEntity existingUser = existingUserOptional.get();

            if (!existingUser.getUsername().equals(data.getUsername())) {
                Optional<UserEntity> userWithNewUsername = userRepository.findByUsername(data.getUsername());
                if (userWithNewUsername.isPresent()) {
                    System.out.println("Username already exists: " + data.getUsername());
                    return false;
                }
                existingUser.setUsername(data.getUsername());
            }

            existingUser.setName(data.getName());
            existingUser.setEmail(data.getEmail());
            existingUser.setPhone(data.getPhone());

            if (data.getPassword() != null && !data.getPassword().isEmpty()) {
                existingUser.setPassword(passwordEncoder.encode(data.getPassword()));
            }

            userRepository.save(existingUser);
            System.out.println("User updated successfully with ID: " + existingUser.getId());
            return true;
        } catch (Exception e) {
            System.out.println("Error during user update: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }


    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<UserDetailsDTO> getAllUserDetails() {
        return userRepository
                .findAll()
                .stream()
                .map(UserServiceImpl::toUserDetails)
                .toList();
    }

    private static UserDetailsDTO toUserDetails(UserEntity userEntity) {
        return new UserDetailsDTO(
                userEntity.getId(),
                userEntity.getName(),
                userEntity.getUsername(),
                userEntity.getEmail(),
                userEntity.getPhone()
        );

    }

}
