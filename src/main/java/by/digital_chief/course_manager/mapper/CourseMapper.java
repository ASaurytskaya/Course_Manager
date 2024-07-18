package by.digital_chief.course_manager.mapper;

import by.digital_chief.course_manager.core.dto.CourseCreateDto;
import by.digital_chief.course_manager.core.dto.CourseDto;
import by.digital_chief.course_manager.repository.entity.Course;
import by.digital_chief.course_manager.repository.entity.Trainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CourseMapper {

    private static final Logger logger = LoggerFactory.getLogger(CourseMapper.class);

    public CourseDto toDto(Course course) {
        logger.info("Converting {} course to DTO", course.getId());

        CourseDto dto = new CourseDto();
        dto.setId(course.getId());
        dto.setTitle(course.getTitle());
        dto.setDescription(course.getDescription());
        dto.setTrainerId(course.getTrainerId());

        logger.info("CourseDto {} created", dto.getId());
        return dto;
    }

    public List<CourseDto> toDtoList(List<Course> courseList) {
        logger.info("Converting {} courses to DTOs", courseList.size());

        return courseList.
                stream().map(this::toDto).
                collect(Collectors.toList());
    }

    public Course toEntity(CourseCreateDto dto, Trainer trainer) {
        logger.info("Converting {} DTO to course", dto.getTitle());

        Course course = new Course();
        course.setTitle(dto.getTitle());
        course.setDescription(dto.getDescription());
        course.setTrainer(trainer);

        logger.info("Course entity {} created", course.getTitle());
        return course;
    }

    public void updateEntity(Course course, CourseCreateDto dto) {
        logger.info("Updating {} course entity", course.getId());

        course.setTitle(dto.getTitle());
        course.setDescription(dto.getDescription());

        logger.info("Trainer {} updated", course.getId());
    }
}
