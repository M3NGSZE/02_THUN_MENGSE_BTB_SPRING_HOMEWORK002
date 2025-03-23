package com.example.mybatdies.controller;

import com.example.mybatdies.model.dto.request.StudentRequest;
import com.example.mybatdies.model.dto.response.ApiResponse;
import com.example.mybatdies.model.entity.Student;
import com.example.mybatdies.repository.StudentRepository;
import com.example.mybatdies.service.CourseService;
import com.example.mybatdies.service.StudentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
@Tag(name = "student-controller")
public class StudentController {

    private final StudentService studentService;
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Student>>> getAllStudents(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "3") Integer size) {

        List<Student> students = studentService.getAllStudents(page, size);
        ApiResponse<List<Student>> apiResponse = ApiResponse.<List<Student>>builder()
                .message("All students have been successfully fetched.")
                .payload(students)
                .status(HttpStatus.OK)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @GetMapping("/{student-id}")
    public ResponseEntity<ApiResponse<Student>> getStudentById(@PathVariable("student-id") Integer studentId) {
        Student student = studentService.getStudentById(studentId);
        ApiResponse<Student> apiResponse = ApiResponse.<Student>builder()
                .message("Student has been successfully fetched.")
                .payload(student)
                .status(HttpStatus.OK)
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Student>> addStudent(@RequestBody StudentRequest studentRequest) {

        Student student = studentService.createStudent(studentRequest);
        ApiResponse<Student> apiResponse = ApiResponse.<Student>builder()
                .message("Student has been successfully created.")
                .payload(student)
                .status(HttpStatus.CREATED)
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    @PutMapping("/{student-id}")
    public ResponseEntity<ApiResponse<Student>> updateStudent(
            @PathVariable("student-id") Integer studentId,
            @RequestBody StudentRequest studentRequest) {

        Student student = studentService.updateStudent(studentId, studentRequest);
        ApiResponse<Student> apiResponse = ApiResponse.<Student>builder()
                .message("Student has been successfully updated.")
                .payload(student)
                .status(HttpStatus.OK)
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @DeleteMapping("/{student-id}")
    public ResponseEntity<ApiResponse<Student>> deleteStudent(@PathVariable("student-id") Integer studentId) {
        Student student = studentService.deleteStudentById(studentId);
        ApiResponse<Student> apiResponse = ApiResponse.<Student>builder()
                .message("Student has been successfully deleted.")
                .payload(student)
                .status(HttpStatus.OK)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }
}
