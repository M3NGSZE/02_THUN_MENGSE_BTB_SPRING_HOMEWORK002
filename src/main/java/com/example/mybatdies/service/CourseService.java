package com.example.mybatdies.service;

import com.example.mybatdies.model.dto.request.CourseRequest;
import com.example.mybatdies.model.entity.Course;

import java.util.List;

public interface CourseService {

    List<Course> getAllCourses(Integer page,Integer size);
    Course getCourseById(Integer courseId);
    Course createCourse(CourseRequest courseRequest);
    Course updateCourseById(Integer courseId, CourseRequest courseRequest);
    Course deleteCourseById(Integer courseId);
}
