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

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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


    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserEntity findUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User with ID " + id + " not found"));
    }

    @Override
    public boolean updateUser(Long id, UserRegisterDTO data) {
        try {
            UserEntity user = userRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("User with ID " + id + " not found"));

            user.setName(data.getName());
            user.setEmail(data.getEmail());
            user.setPhone(data.getPhone());

            if (data.getPassword() != null && !data.getPassword().isEmpty()) {
                user.setPassword(passwordEncoder.encode(data.getPassword()));
            }

            userRepository.save(user);
            return true;
        } catch (Exception e) {
            System.out.println("Error updating user: " + e.getMessage());
            return false;
        }
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

    public long getTotalRegisteredUsers() {
        return userRepository.count();
    }

    @Override
    public List<UserDetailsDTO> getAllUsersSortedByName() {
        return userRepository.findAllByOrderByUsernameAsc()
                .stream()
                .map(user -> new UserDetailsDTO(
                        user.getId(),
                        user.getName(),
                        user.getUsername(),
                        user.getEmail(),
                        user.getPhone()
                ))
                .collect(Collectors.toList());
    }

}
