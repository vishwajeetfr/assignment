package com.leucine.assignment.mapper;

import com.leucine.assignment.dto.SubmissionDTO;
import com.leucine.assignment.entity.ApplicationUser;
import com.leucine.assignment.entity.Assignment;
import com.leucine.assignment.entity.Submission;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class SubmissionMapper {
    public static SubmissionDTO mapToSubmissionDTO(Submission submission, String submittedById) {
        SubmissionDTO dto = SubmissionDTO.builder()
                .id(submission.getId())
                .assignmentId(submission.getAssignment().getId())
                .submissionText(submission.getSubmissionText())
                .attachmentUrl(submission.getAttachmentUrl())
                .submissionDate(submission.getSubmissionDate())
                .submittedBy(submittedById)
                .hasGraded(submission.getHasGraded())
                .build();
        return dto;
    }

    public static Submission mapToSubmission(SubmissionDTO dto, LocalDateTime currentDate ,
                                             Assignment assignment, ApplicationUser submittedBy) {
        Submission submission = Submission.builder()
                .assignment(assignment)
                .submissionText(dto.getSubmissionText())
                .attachmentUrl(dto.getAttachmentUrl())
                .submissionDate(currentDate)
                .student(submittedBy)
                .hasGraded(dto.getHasGraded())
                .build();
        return submission;
    }
}
