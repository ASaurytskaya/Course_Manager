package by.digital_chief.course_manager.service.implementation;

import by.digital_chief.course_manager.core.dto.CourseCreateDto;
import by.digital_chief.course_manager.core.dto.CourseDto;
import by.digital_chief.course_manager.exception.BadRequestException;
import by.digital_chief.course_manager.exception.CourseNotFoundException;
import by.digital_chief.course_manager.mapper.CourseMapper;
import by.digital_chief.course_manager.repository.api.ICourseRepository;
import by.digital_chief.course_manager.repository.entity.Course;
import by.digital_chief.course_manager.repository.entity.Trainer;
import by.digital_chief.course_manager.service.api.ICourseService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CourseService implements ICourseService {

    private static final Logger logger = LoggerFactory.getLogger(CourseService.class);

    private final ICourseRepository courseRepository;
    private final CourseMapper mapper;

    private static final String DATA_DISCREPANCY = "Course information discrepancy: provided DTO does not match the database entry";

    public CourseService(ICourseRepository courseRepository, CourseMapper mapper) {
        this.courseRepository = courseRepository;
        this.mapper = mapper;
    }

    @Transactional
    @Override
    public CourseDto createCourse(CourseCreateDto dto, Trainer trainer) {
        logger.info("Saving course with name: {}", dto.getTitle());

        Course course  = courseRepository.save(mapper.toEntity(dto, trainer));

        logger.info("Saved course with id: {}", dto.getTitle());
        return mapper.toDto(course);
    }

    @Transactional(readOnly = true)
    @Override
    public List<CourseDto> getAllCourses() {
        logger.info("Fetching courses");

        List<Course> list = courseRepository.findAll();
        if(list.isEmpty()) {

            logger.warn("No courses found");
            throw new CourseNotFoundException();
        }
        List<CourseDto> resultList = mapper.toDtoList(list);

        logger.info("Fetched {} courses", resultList.size());
        return resultList;
    }

    @Override
    public List<CourseDto> getCoursesByTrainer(int trainerId) {
        logger.info("Fetching courses for trainer with id {}", trainerId);

        List<Course> list = courseRepository.findAllByTrainerId(trainerId);
        if(list.isEmpty()) {

            logger.warn("No courses found for trainer with id {}", trainerId);
            throw new CourseNotFoundException();
        }
        List<CourseDto> resultList = mapper.toDtoList(list);

        logger.info("Fetched {} courses for trainer with id {}", resultList.size(), trainerId);
        return resultList;
    }

    @Override
    public CourseDto getCourseById(int id) {
        logger.info("Fetching course  with id {}", id);

        Course course = courseRepository.findById(id)
                .orElseThrow(() -> {
                    logger.warn("Course with id {} not found", id);
                    return new CourseNotFoundException("with id: " + id);
                });

        CourseDto dto = mapper.toDto(course);

        logger.info("Fetched course  with id {}", dto.getId());
        return dto;
    }

    @Transactional
    @Override
    public CourseDto updateCourse(int id, CourseCreateDto dto) {

        logger.info("Updating course with id: {}", id);

        Course course = findById(id);

        mapper.updateEntity(course, dto);
        CourseDto newDto = mapper.toDto(course);

        logger.info("Updated course with id: {}", newDto.getId());

        return newDto;
    }

    @Transactional
    @Override
    public void deleteCourse(int id, CourseDto dto) {
        logger.info("Deleting course with id: {}", id);

        Course course = findById(id);
        CourseDto dtoInDB = mapper.toDto(course);

        if(!dto.equals(dtoInDB)) {
            logger.warn(DATA_DISCREPANCY);
            throw new BadRequestException(DATA_DISCREPANCY);
        }

        courseRepository.delete(course);

        logger.info("Deleted trainer with id: {}", id);
    }

    private Course findById(int id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> {
                    logger.warn("Course with id {} not found", id);
                    return new CourseNotFoundException("with id: " + id);
                });
    }
}
