package by.digital_chief.course_manager.repository.api;

import by.digital_chief.course_manager.repository.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ICourseRepository extends JpaRepository<Course, Integer> {

    @Query("SELECT c FROM Course c WHERE c.trainer.id = :trainerId")
    List<Course> findAllByTrainerId(@Param("trainerId") int trainerId);

    Optional<Course> findById(int id);
}
