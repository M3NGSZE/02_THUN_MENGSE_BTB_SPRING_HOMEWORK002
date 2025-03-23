package com.example.mybatdies.service.implementaion;

import com.example.mybatdies.model.dto.request.StudentRequest;
import com.example.mybatdies.model.entity.Course;
import com.example.mybatdies.model.entity.Student;
import com.example.mybatdies.repository.CourseRepository;
import com.example.mybatdies.repository.StudentRepository;
import com.example.mybatdies.service.CourseService;
import com.example.mybatdies.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImp implements StudentService {
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    public StudentServiceImp(StudentRepository studentRepository, CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }


    @Override
    public List<Student> getAllStudents(Integer page, Integer size) {
        return studentRepository.getAllStudents(page, size);
    }

    @Override
    public Student getStudentById(Integer studentId) {
        return studentRepository.getStudentById(studentId);
    }

    @Override
    public Student createStudent(StudentRequest studentRequest) {

        Integer studentID = studentRepository.createStudent(studentRequest);

        for(Integer courseId : studentRequest.getCourseId()){
            studentRepository.insertIntoStudentCourse(studentID, courseId);
        }

        return studentRepository.getStudentById(studentID);
    }

    @Override
    public Student updateStudent(Integer studentId, StudentRequest studentRequest) {
        studentRepository.deleteStudentCourseById(studentId);

        for(Integer courseId : studentRequest.getCourseId()){
            studentRepository.insertIntoStudentCourse(studentId, courseId);
        }

        studentRepository.updateStudentById(studentId, studentRequest);

        return studentRepository.getStudentById(studentId);
    }

    @Override
    public Student deleteStudentById(Integer studentId) {
        List<Course> courses = courseRepository.getAllCourseByStudentId(studentId);

        Student deletedStudent = studentRepository.deleteStudentById(studentId);

        if (deletedStudent != null) {
            deletedStudent.setCourse(courses);
        }

        return deletedStudent;
    }
}
