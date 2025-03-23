package com.example.mybatdies.repository;

import com.example.mybatdies.model.dto.request.InstructorRequest;
import com.example.mybatdies.model.entity.Instructor;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface InstructorRepository {

    @Select("""
        SELECT * FROM instructors
        offset #{size} * (#{page} - 1)
        limit #{size}
    """)
    @Results(id = "instructorMapper", value = {
            @Result(property = "instructorId", column = "instructor_id"),
            @Result(property = "instructorName", column = "instructor_name"),
            @Result(property = "instructorEmail", column = "email")
    })
    List<Instructor> getAllInstructors(Integer page, Integer size);

    @Select("""
        SELECT * FROM instructors
        WHERE instructor_id = #{id}
    """)
    @ResultMap("instructorMapper")
    Instructor getInstructorById(Integer id);

    @Select("""
        INSERT INTO instructors (instructor_name, email)
        VALUES (#{instructor.instructorName}, #{instructor.instructorEmail})
        returning *
    """)
    @ResultMap("instructorMapper")
    Instructor createInstructor(@Param("instructor")InstructorRequest instructorRequest);

    @Select("""
        UPDATE instructors
        SET instructor_name = #{instructor.instructorName}, email = #{instructor.instructorEmail}
        WHERE instructor_id = #{id}
        returning *
    """)
    @ResultMap("instructorMapper")
    Instructor updateInstructorById(Integer id, @Param("instructor") InstructorRequest instructorRequest);

    @Select("""
        DELETE FROM instructors
        WHERE instructor_id = #{id}
        returning *
    """)
    @ResultMap("instructorMapper")
    Instructor deleteInstructorById(Integer id);
}
