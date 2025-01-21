package my.fitnessapp.repository;


import my.fitnessapp.model.entity.PersonalTrainingRequestEntity;
import my.fitnessapp.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonalTrainingRequestRepository extends JpaRepository<PersonalTrainingRequestEntity, Long> {

    List<PersonalTrainingRequestEntity> findAllByUser(UserEntity currentUser);
}
