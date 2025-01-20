package my.fitnessapp.service;

import my.fitnessapp.model.dto.UserDetailsDTO;
import my.fitnessapp.model.dto.UserRegisterDTO;

import java.util.List;

public interface UserService {

    List<UserDetailsDTO> getAllUserDetails();

    List<UserDetailsDTO> getAllUsersSortedByName();
}
