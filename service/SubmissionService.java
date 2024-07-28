package com.leucine.assignment.service;

import com.leucine.assignment.dto.SubmissionDTO;
import com.leucine.assignment.entity.Submission;
import com.leucine.assignment.exception.ResourceNotFoundExeception;
import com.leucine.assignment.mapper.SubmissionMapper;
import com.leucine.assignment.repository.SubmissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SubmissionService {

    @Autowired
    private SubmissionRepository submissionRepository;

    @Autowired
    private ApplicationUserService applicationUserService;

    @Autowired
    private AssignmentService assignmentService;



    public SubmissionDTO createSubmission(SubmissionDTO submissionDTO) {
        LocalDateTime currentDate = LocalDateTime.now();
        final var submittedBy = applicationUserService.getUserByUsername(submissionDTO.getSubmittedBy());
        final var assignment = assignmentService.getAssignmentById(submissionDTO.getAssignmentId());

        if (!submittedBy.getClassId().getName().equals(assignment.getClazz())) {
            throw new RuntimeException("Student is not allowed to submit this assignment for a different class.");
        }
        final var submission = SubmissionMapper.mapToSubmission(submissionDTO, currentDate, assignment, submittedBy);
        final var careatedSubmission =  submissionRepository.save(submission);
        return SubmissionMapper.mapToSubmissionDTO(careatedSubmission, submissionDTO.getSubmittedBy());
    }

    public SubmissionDTO gradeSubmission(Long submissionId, SubmissionDTO updatedSubmissionDTO) {
        final var submission = submissionRepository.findById(submissionId)
                .orElseThrow(() -> new ResourceNotFoundExeception("Submission does not exists with id : " + submissionId));
        submission.setHasGraded(updatedSubmissionDTO.getHasGraded());
        final var gradedSubmission = submissionRepository.save(submission);
        return SubmissionMapper.mapToSubmissionDTO(gradedSubmission, updatedSubmissionDTO.getSubmittedBy());
    }

    public List<Submission> getSubmissionsByAssignment(Long assignmentId) {
        return submissionRepository.findByAssignment_Id(assignmentId);
    }

    public List<Submission> getSubmissionsByStudent(Long studentId) {
        return submissionRepository.findByStudent_Id(studentId);
    }
}
