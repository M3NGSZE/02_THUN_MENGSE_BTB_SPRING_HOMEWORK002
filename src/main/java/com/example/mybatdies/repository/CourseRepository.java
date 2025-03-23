package com.example.mybatdies.repository;

import com.example.mybatdies.model.dto.request.CourseRequest;
import com.example.mybatdies.model.entity.Course;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CourseRepository {

    @Select("""
        SELECT * FROM courses
        offset #{size} * (#{page} - 1)
        limit #{size}
    """)
    @Results(id = "courseMapper", value = {
            @Result(property = "courseId", column = "course_id"),
            @Result(property = "courseName", column = "course_name"),
            @Result(property = "description", column = "description"),
            @Result(property = "instructor", column = "instructor_id",
                    one = @One(select = "com.example.mybatdies.repository.InstructorRepository.getInstructorById")
            )
    })
    List<Course> getAllCourses(Integer page,Integer size);

    @Select("""
        SELECT * FROM courses
        WHERE course_id = #{id}
    """)
    @ResultMap("courseMapper")
    Course getCourseById(Integer id);

    @Select(("""
        INSERT INTO courses (course_name, description, instructor_id)
        VALUES (#{course.courseName}, #{course.description}, #{course.instructorId})
        returning *
    """))
    @ResultMap("courseMapper")
    Course createCourse(@Param("course") CourseRequest courseRequest);

    @Select("""
        UPDATE courses 
        SET course_name = #{course.courseName}, description = #{course.description}, instructor_id = #{course.instructorId}
        WHERE course_id = #{id}
        returning *
    """)
    @ResultMap("courseMapper")
    Course updateCourseById(Integer id, @Param("course") CourseRequest courseRequest);

    @Select("""
        DELETE FROM courses
        WHERE course_id = #{id}
        returning *
    """)
    @Results(id = "courseMapperDelete", value = {
            @Result(property = "courseId", column = "course_id"),
            @Result(property = "courseName", column = "course_name"),
            @Result(property = "description", column = "description"),
            @Result(property = "instructor", column = "instructor_id",
                    one = @One(select = "com.example.mybatdies.repository.InstructorRepository.getInstructorById")
            )
    })
//    @ResultMap("instructorMapper")
    Course deleteCourseById(Integer id);

    @Select("""
         SELECT c.* FROM courses c 
         INNER JOIN student_course sc 
         ON  c.course_id = sc.course_id 
         WHERE sc.student_id = #{studentId};
     """)
    @ResultMap("courseMapper")
    List<Course> getAllCourseByStudentId(Integer studentId);
}
