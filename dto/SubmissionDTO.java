package com.leucine.assignment.dto;

import lombok.*;

import java.time.LocalDateTime;

@Builder(toBuilder = true)
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SubmissionDTO {
    private Long id;
    private Long assignmentId;
    private String submissionText;
    private String attachmentUrl;
    private LocalDateTime submissionDate;
    private String submittedBy;
    private String hasGraded;

}
