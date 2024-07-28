package com.leucine.assignment.mapper;

import com.leucine.assignment.dto.AssignmentDTO;
import com.leucine.assignment.entity.ApplicationUser;
import com.leucine.assignment.entity.Assignment;
import org.springframework.stereotype.Component;

@Component
public class AssignmentMapper {

    public static AssignmentDTO mapToAssignmentDTO(Assignment assignment, String createdByID) {
        AssignmentDTO dto = new AssignmentDTO();
        dto.setId(assignment.getId());
        dto.setTitle(assignment.getTitle());
        dto.setDescription(assignment.getDescription());
        dto.setDueDate(assignment.getDueDate());
        dto.setClazz(assignment.getClazz());
        dto.setAttachmentUrl(assignment.getAttachmentUrl());
        dto.setCreatedBy(createdByID);
        return dto;
    }

    public static Assignment mapToAssignment(AssignmentDTO dto, ApplicationUser createdBy) {
        Assignment assignment = new Assignment();
        assignment.setId(dto.getId());
        assignment.setTitle(dto.getTitle());
        assignment.setDescription(dto.getDescription());
        assignment.setDueDate(dto.getDueDate());
        assignment.setClazz(dto.getClazz());
        assignment.setAttachmentUrl(dto.getAttachmentUrl());
        assignment.setCreatedBy(createdBy);
        return assignment;
    }
}
