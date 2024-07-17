package by.digital_chief.course_manager.repository.api;

import by.digital_chief.course_manager.repository.entity.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ITrainerRepository extends JpaRepository<Trainer, Integer> {

    Optional<Trainer> findById(int id);
}
