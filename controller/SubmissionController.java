package com.leucine.assignment.controller;

import com.leucine.assignment.dto.SubmissionDTO;
import com.leucine.assignment.entity.Submission;
import com.leucine.assignment.service.SubmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/submissions")
public class SubmissionController {

    @Autowired
    private SubmissionService submissionService;

    @PostMapping
    public ResponseEntity<SubmissionDTO> createSubmission(@RequestBody SubmissionDTO submission) {
        final var savedSubmission =  submissionService.createSubmission(submission);
        return new ResponseEntity<>(savedSubmission, HttpStatus.CREATED);
    }

    @GetMapping("/assignment/{assignmentId}")
    public List<Submission> getSubmissionsByAssignment(@PathVariable Long assignmentId) {
        return submissionService.getSubmissionsByAssignment(assignmentId);
    }

    @GetMapping("/student/{studentId}")
    public List<Submission> getSubmissionsByStudent(@PathVariable Long studentId) {
        return submissionService.getSubmissionsByStudent(studentId);
    }

    @PutMapping("/grade/{id}")
    public ResponseEntity<SubmissionDTO> gradeSubmission(@PathVariable("id") Long submissionId,
                                                          @RequestBody SubmissionDTO submissionDTO){
        final var gradedSubmission = submissionService.gradeSubmission(submissionId, submissionDTO);
        return ResponseEntity.ok(gradedSubmission);
    }
}
