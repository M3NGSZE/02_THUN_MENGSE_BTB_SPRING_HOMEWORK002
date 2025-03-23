package com.example.mybatdies.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentRequest {
    private String studentName;
    private String studentEmail;
    private String studentPhone;
    private List<Integer> courseId;
}
