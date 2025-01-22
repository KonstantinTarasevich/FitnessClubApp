package my.fitnessapp.service;

import my.fitnessapp.model.dto.UserDetailsDTO;
import my.fitnessapp.model.dto.UserRegisterDTO;
import my.fitnessapp.model.entity.UserEntity;

import java.util.List;

public interface UserService {

    UserEntity findUserById(Long id);

    boolean updateUser(Long id, UserRegisterDTO data);

    List<UserDetailsDTO> getAllUserDetails();

    List<UserDetailsDTO> getAllUsersSortedByName();
}
