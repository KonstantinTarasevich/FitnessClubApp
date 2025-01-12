package my.fitnessapp.service;

import my.fitnessapp.model.dto.UserDetailsDTO;

import java.util.List;

public interface UserService {

    List<UserDetailsDTO> getAllUserDetails();
}
