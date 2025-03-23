package com.example.mybatdies.controller;

import com.example.mybatdies.model.dto.request.CourseRequest;
import com.example.mybatdies.model.dto.response.ApiResponse;
import com.example.mybatdies.model.entity.Course;
import com.example.mybatdies.service.CourseService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/courses")
@Tag(name= "course-controller")
public class CourseController {

    private final CourseService courseService;
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Course>>> getAllCourses(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "3") Integer size) {
        List<Course> courses = courseService.getAllCourses(page, size);
        ApiResponse<List<Course>> apiResponse = ApiResponse.<List<Course>>builder()
                .message("All course have been successfully fetched.")
                .payload(courses)
                .status(HttpStatus.OK)
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @GetMapping("/{course-id}")
    public ResponseEntity<ApiResponse<Course>> getCourseById(@PathVariable("course-id") Integer courseId) {
        Course course = courseService.getCourseById(courseId);
        ApiResponse<Course> apiResponse = ApiResponse.<Course>builder()
                .message("All course have been successfully fetched.")
                .payload(course)
                .status(HttpStatus.OK)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Course>> createCourse(@RequestBody CourseRequest courseRequest) {
        Course course = courseService.createCourse(courseRequest);
        ApiResponse<Course> apiResponse = ApiResponse.<Course>builder()
                .message("The course has been successfully created.")
                .payload(course)
                .status(HttpStatus.CREATED)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    @PutMapping("/{course-id}")
    public ResponseEntity<ApiResponse<Course>> updateCourseById(
            @PathVariable("course-id") Integer courseId,
            @RequestBody CourseRequest courseRequest){

        Course course = courseService.updateCourseById(courseId, courseRequest);
        ApiResponse<Course> apiResponse = ApiResponse.<Course>builder()
                .message("The course has been successfully updated.")
                .payload(course)
                .status(HttpStatus.OK)
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @DeleteMapping("/{course-id}")
    public ResponseEntity<ApiResponse<Course>> deleteCourseById(@PathVariable("course-id") Integer courseId) {
        Course course = courseService.deleteCourseById(courseId);
        ApiResponse<Course> apiResponse = ApiResponse.<Course>builder()
                .message("The course has been successfully deleted.")
                .payload(course)
                .status(HttpStatus.OK)
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }
}
