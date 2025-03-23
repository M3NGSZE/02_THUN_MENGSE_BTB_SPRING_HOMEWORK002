package com.example.mybatdies.controller;

import com.example.mybatdies.model.dto.request.InstructorRequest;
import com.example.mybatdies.model.dto.response.ApiResponse;
import com.example.mybatdies.model.entity.Instructor;
import com.example.mybatdies.service.InstructorService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/instructors")
@Tag(name = "instructor-controller")
public class InstructorController {

    private final InstructorService instructorService;
    public InstructorController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Instructor>>> getAllInstructors(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "3") Integer size) {
        List<Instructor> instructors = instructorService.getAllInstructors(page, size);
        ApiResponse<List<Instructor>> apiResponse = ApiResponse.<List<Instructor>>builder()
                .message("All instructors have been successfully fetched.")
                .payload(instructors)
                .status(HttpStatus.OK)
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @GetMapping("/{instructor-id}")
    public ResponseEntity<ApiResponse<Instructor>> getInstructorById(@PathVariable("instructor-id") Integer instructorId) {
        Instructor instructor = instructorService.getInstructorById(instructorId);
        ApiResponse<Instructor> apiResponse = ApiResponse.<Instructor>builder()
                .message("The instructor has been successfully founded.")
                .payload(instructor)
                .status(HttpStatus.OK)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Instructor>> createInstructor(@RequestBody InstructorRequest instructorRequest) {
        Instructor instructor = instructorService.createInstructor(instructorRequest);
        ApiResponse<Instructor> apiResponse = ApiResponse.<Instructor>builder()
                .message("The instructor has been successfully created.")
                .payload(instructor)
                .status(HttpStatus.CREATED)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    @PutMapping("/{instructor-id}")
    public ResponseEntity<ApiResponse<Instructor>> updateInstructorById(
            @PathVariable("instructor-id") Integer instructorId,
            @RequestBody InstructorRequest instructorRequest) {
        Instructor instructor = instructorService.updateInstructorById(instructorId, instructorRequest);
        ApiResponse<Instructor> apiResponse = ApiResponse.<Instructor>builder()
                .message("The instructor has been successfully updated.")
                .payload(instructor)
                .status(HttpStatus.OK)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    @DeleteMapping("/{instructor-id}")
    public ResponseEntity<ApiResponse<Instructor>> deleteInstructorById(@PathVariable("instructor-id") Integer instructorId){
        Instructor instructor = instructorService.deleteInstructorById(instructorId);
        ApiResponse<Instructor> apiResponse = ApiResponse.<Instructor>builder()
                .message("The instructor has been successfully deleted.")
                .payload(instructor)
                .status(HttpStatus.OK)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

}
