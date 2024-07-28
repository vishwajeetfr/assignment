package com.leucine.assignment.dto;

import lombok.*;

import java.time.LocalDate;
@Builder(toBuilder = true)
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AssignmentDTO {
    private Long id;
    private String title;
    private String description;
    private LocalDate dueDate;
    private String clazz;
    private String attachmentUrl;
    private String createdBy;

}
