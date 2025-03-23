package com.example.mybatdies.service.implementaion;

import com.example.mybatdies.model.dto.request.InstructorRequest;
import com.example.mybatdies.model.entity.Instructor;
import com.example.mybatdies.repository.InstructorRepository;
import com.example.mybatdies.service.InstructorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstructorServiceImp implements InstructorService {

    private final InstructorRepository instructorRepository;
    public InstructorServiceImp(InstructorRepository instructorRepository) {
        this.instructorRepository = instructorRepository;
    }

    @Override
    public List<Instructor> getAllInstructors(Integer page, Integer size) {
        return instructorRepository.getAllInstructors(page, size);
    }

    @Override
    public Instructor getInstructorById(Integer instructorId) {
        return instructorRepository.getInstructorById(instructorId);
    }

    @Override
    public Instructor createInstructor(InstructorRequest instructorRequest) {
        return instructorRepository.createInstructor(instructorRequest);
    }

    @Override
    public Instructor updateInstructorById(Integer instructorId, InstructorRequest instructorRequest) {
        return instructorRepository.updateInstructorById(instructorId, instructorRequest);
    }

    @Override
    public Instructor deleteInstructorById(Integer instructorId) {
        return instructorRepository.deleteInstructorById(instructorId);
    }
}
