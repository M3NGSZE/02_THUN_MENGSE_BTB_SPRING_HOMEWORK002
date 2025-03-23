package com.example.mybatdies.service;

import com.example.mybatdies.model.dto.request.InstructorRequest;
import com.example.mybatdies.model.entity.Instructor;

import java.util.List;

public interface InstructorService {
    List<Instructor> getAllInstructors(Integer page, Integer size);
    Instructor getInstructorById(Integer instructorId);
    Instructor createInstructor(InstructorRequest instructorRequest);
    Instructor updateInstructorById(Integer instructorId,InstructorRequest instructorRequest);
    Instructor deleteInstructorById(Integer instructorId);
}
