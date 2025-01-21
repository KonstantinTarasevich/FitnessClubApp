package my.fitnessapp.repository;


import my.fitnessapp.model.entity.PersonalTrainingRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonalTrainingRequestRepository extends JpaRepository<PersonalTrainingRequestEntity, Long> {

}
