package com.example.mybatdies.service;

import com.example.mybatdies.model.dto.request.StudentRequest;
import com.example.mybatdies.model.entity.Student;

import java.util.List;

public interface StudentService {
    List<Student> getAllStudents(Integer page, Integer size);
    Student getStudentById(Integer id);
    Student createStudent(StudentRequest studentRequest);
    Student updateStudent(Integer studentId, StudentRequest studentRequest);
    Student deleteStudentById(Integer id);
}
