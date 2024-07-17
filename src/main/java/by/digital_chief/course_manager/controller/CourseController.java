package by.digital_chief.course_manager.controller;

import by.digital_chief.course_manager.core.dto.CourseCreateDto;
import by.digital_chief.course_manager.core.dto.CourseDto;
import by.digital_chief.course_manager.service.api.ICourseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/courses")
public class CourseController {

    private static final Logger logger = LoggerFactory.getLogger(CourseController.class);

    private final ICourseService courseService;

    public CourseController(ICourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping
    public ResponseEntity<CourseDto> createCourse(@RequestBody CourseCreateDto dto) {
        logger.info("Received request to create course: {}", dto.getTitle());

        CourseDto createdCourse = courseService.createCourse(dto);

        logger.info("Course created successfully with id: {}", createdCourse.getId());
        return new ResponseEntity<>(createdCourse, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CourseDto>> getAllCourses() {
        logger.info("Received request to get list of all courses");

        List<CourseDto> trainers = courseService.getAllCourses();

        logger.info("Fetched {} courses", trainers.size());
        return new ResponseEntity<>(trainers, HttpStatus.OK);
    }

    @GetMapping("/trainer/{trainerId}")
    public ResponseEntity<List<CourseDto>> getCoursesByTrainerId(@PathVariable int trainerId) {
        logger.info("Received request to get list of all courses for trainer with id {}", trainerId);

        List<CourseDto> courses = courseService.getCoursesByTrainer(trainerId);

        logger.info("Fetched {} courses for trainer with id {}", courses.size(), trainerId);
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseDto> getCourseById(@PathVariable int id) {
        logger.info("Received request to get course with id {}", id);

        CourseDto dto = courseService.getCourseById(id);

        logger.info("Fetched course with id {}", dto.getId());
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseDto> updateTrainer(@PathVariable int id, @RequestBody CourseCreateDto dto) {
        logger.info("Received request to update course with id {}", id);

        CourseDto updatedCourse = courseService.updateCourse(id, dto);

        logger.info("Updated course with id {}", updatedCourse.getId());
        return new ResponseEntity<>(updatedCourse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrainer(@PathVariable int id, @RequestBody CourseDto dto) {
        logger.info("Received request to delete course with id {}", id);

        courseService.deleteCourse(id,dto);

        logger.info("Deleted course with id {}", id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
