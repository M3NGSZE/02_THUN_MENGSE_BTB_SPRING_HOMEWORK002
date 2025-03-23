package com.example.mybatdies.repository;

import com.example.mybatdies.model.dto.request.StudentRequest;
import com.example.mybatdies.model.entity.Student;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface StudentRepository {

    @Select("""
        SELECT * FROM students
        offset #{size} * (#{page} - 1)
        limit #{size}
    """)
    @Results(id = "studentMapper", value = {
            @Result(property = "studentId", column = "student_id"),
            @Result(property = "studentName", column = "student_name"),
            @Result(property = "studentEmail", column = "email"),
            @Result(property = "studentPhone", column = "phone_number"),
            @Result(property = "course", column = "student_id",
                    many = @Many(select = "com.example.mybatdies.repository.CourseRepository.getAllCourseByStudentId")
            )
    })
    List<Student> getAllStudents(Integer page, Integer size);

    @Select("""
        SELECT * FROM students
        WHERE student_id = #{id}
    """)
    @ResultMap("studentMapper")
    Student getStudentById(Integer id);

    @Select("""
        INSERT INTO students (student_name, email, phone_number)
        VALUES (#{student.studentName}, #{student.studentEmail}, #{student.studentPhone})
        returning student_id
    """)
    Integer createStudent(@Param("student") StudentRequest studentRequest);

    @Insert("""
        INSERT INTO student_course VALUES (default,#{studentId},#{courseId}) 
    """)
    void insertIntoStudentCourse(Integer studentId, Integer courseId);

    @Select("""
        UPDATE students 
        SET student_name = #{student.studentName}, email = #{student.studentEmail}, phone_number = #{student.studentPhone}
        WHERE student_id = #{id}
        returning *
    """)
    @ResultMap("studentMapper")
    Student updateStudentById(Integer id, @Param("student") StudentRequest studentRequest);

    @Select("""
        DELETE from student_course WHERE student_id = #{id}
    """)
    @ResultMap("studentMapper")
    Student deleteStudentCourseById(Integer id);

    @Select("""
        DELETE FROM students 
        where student_id = #{id}
        returning *
    """)
    @ResultMap("studentMapper")
    Student deleteStudentById(Integer id);

}
