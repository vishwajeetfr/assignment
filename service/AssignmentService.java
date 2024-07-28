package com.leucine.assignment.service;

import com.leucine.assignment.dto.AssignmentDTO;
import com.leucine.assignment.entity.Assignment;
import com.leucine.assignment.exception.ResourceNotFoundExeception;
import com.leucine.assignment.repository.AssignmentRepository;
import com.leucine.assignment.mapper.AssignmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AssignmentService {

    @Autowired
    private AssignmentRepository assignmentRepository;
    @Autowired
    private ApplicationUserService applicationUserService;

    public AssignmentDTO createAssignment(AssignmentDTO assignmentDTO) {
        LocalDate currentDate = LocalDate.now();
        if (assignmentDTO.getDueDate() != null && assignmentDTO.getDueDate().isAfter(currentDate)) {
            final var createdBy = applicationUserService.getUserByUsername(assignmentDTO.getCreatedBy());
            final var assignment = AssignmentMapper.mapToAssignment(assignmentDTO, createdBy);
            final var createdAssignment =  assignmentRepository.save(assignment);
            return AssignmentMapper.mapToAssignmentDTO(createdAssignment,assignmentDTO.getCreatedBy());
        }
        throw new IllegalArgumentException("Due date must be a future date.");
    }

    public List<Assignment> getAssignmentsByTeacher(Long teacherId) {
        final var createdById = applicationUserService.getUsernameById(teacherId);
        return assignmentRepository.findByCreatedBy_Id(teacherId);
    }
    public List<AssignmentDTO> getAssignmentsByTeacherName(String teacherName){
        final var createdBy = applicationUserService.getUserByUsername(teacherName);
        final var assignments = assignmentRepository.findByCreatedBy_Id(createdBy.getId());
        return assignments.stream().map(e -> AssignmentMapper.mapToAssignmentDTO(e, teacherName)).collect(Collectors.toList());
    }

    public List<Assignment> getAssignmentsByClass(String clazz) {
        return assignmentRepository.findAllByClazz(clazz);
    }

    public AssignmentDTO updateAssignment(Long assignmentId, AssignmentDTO updatedAssignmentDTO) {
        final var assignment = assignmentRepository.findById(assignmentId)
                .orElseThrow(() -> new ResourceNotFoundExeception("Assignment does not exists with id : " + assignmentId));

        if(updatedAssignmentDTO.getCreatedBy().equals(assignment.getCreatedBy().getUsername())) {
            assignment.setTitle(updatedAssignmentDTO.getTitle());
            assignment.setDescription(updatedAssignmentDTO.getDescription());
            assignment.setDueDate(updatedAssignmentDTO.getDueDate());
            assignment.setClazz(updatedAssignmentDTO.getClazz());
        }
        else {
            throw new RuntimeException("Assignment NOT created by " + updatedAssignmentDTO.getCreatedBy() + " : ACCESS DENIED !");
        }
        final var updatedAssignment = assignmentRepository.save(assignment);
        return AssignmentMapper.mapToAssignmentDTO(updatedAssignment, updatedAssignmentDTO.getCreatedBy());

    }

        public void deleteAssignment(Long id) {
        assignmentRepository.deleteById(id);
    }

    public Assignment getAssignmentById(Long id) {
        return assignmentRepository.findById(id).orElse(null);
    }
}
