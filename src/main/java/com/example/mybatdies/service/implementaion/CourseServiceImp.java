package com.example.mybatdies.service.implementaion;

import com.example.mybatdies.model.dto.request.CourseRequest;
import com.example.mybatdies.model.entity.Course;
import com.example.mybatdies.repository.CourseRepository;
import com.example.mybatdies.service.CourseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImp implements CourseService {

    private final CourseRepository courseRepository;
    public CourseServiceImp(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public List<Course> getAllCourses(Integer page, Integer size) {
        return courseRepository.getAllCourses(page, size);
    }

    @Override
    public Course getCourseById(Integer courseId) {
        return courseRepository.getCourseById(courseId);
    }

    @Override
    public Course createCourse(CourseRequest courseRequest) {
        return courseRepository.createCourse(courseRequest);
    }

    @Override
    public Course updateCourseById(Integer courseId, CourseRequest courseRequest) {
        return courseRepository.updateCourseById(courseId, courseRequest);
    }

    @Override
    public Course deleteCourseById(Integer courseId) {
        return courseRepository.deleteCourseById(courseId);
    }
}
