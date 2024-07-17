package by.digital_chief.course_manager.service.api;

import by.digital_chief.course_manager.core.dto.CourseCreateDto;
import by.digital_chief.course_manager.core.dto.CourseDto;

import java.util.List;

public interface ICourseService {

    CourseDto createCourse(CourseCreateDto dto);

    List<CourseDto> getAllCourses();

    List<CourseDto> getCoursesByTrainer(int trainerId);

    CourseDto getCourseById(int id);

    CourseDto updateCourse(int id, CourseCreateDto dto);

    void deleteCourse(int id, CourseDto dto);
}
