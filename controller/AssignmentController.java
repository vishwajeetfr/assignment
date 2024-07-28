package com.leucine.assignment.controller;

import com.leucine.assignment.dto.AssignmentDTO;
import com.leucine.assignment.entity.Assignment;
import com.leucine.assignment.service.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/assignments")
public class AssignmentController {

    @Autowired
    private AssignmentService assignmentService;

    @PostMapping
    @PreAuthorize("hasRole('TEACHER')")
    public ResponseEntity<AssignmentDTO> createAssignment(@RequestBody AssignmentDTO assignment) {
        final var savedAssignment =  assignmentService.createAssignment(assignment);
        return new ResponseEntity<>(savedAssignment, HttpStatus.CREATED);
    }

    @GetMapping("/teacher/{teacherId}")
    @PreAuthorize("hasRole('TEACHER')")
    public List<Assignment> getAssignmentsByTeacher(@PathVariable Long teacherId) {
        return assignmentService.getAssignmentsByTeacher(teacherId);
    }

    @GetMapping("/teacher/username/{teacherName}")
    @PreAuthorize("hasRole('TEACHER')")
    public ResponseEntity<List<AssignmentDTO>> getAssignmentsByTeacherName(@PathVariable String teacherName) {
        final var assignments =  assignmentService.getAssignmentsByTeacherName(teacherName);
        return ResponseEntity.ok(assignments);
    }

    @GetMapping("/class/{clazz}")
    @PreAuthorize("hasRole('TEACHER') || hasRole('STUDENT')")
    public List<Assignment> getAssignmentsByClass(@PathVariable String clazz) {
        return assignmentService.getAssignmentsByClass(clazz);
    }

    @PutMapping("{id}")
    @PreAuthorize("hasRole('TEACHER')")
    public ResponseEntity<AssignmentDTO> updateAssignment(@PathVariable("id") Long assignmentId,
                                                          @RequestBody AssignmentDTO assignmentDTO){
        final var updatedAssignment = assignmentService.updateAssignment(assignmentId,assignmentDTO);
        return ResponseEntity.ok (updatedAssignment);
    }


    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('TEACHER')")
    public void deleteAssignment(@PathVariable Long id) {
        assignmentService.deleteAssignment(id);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('TEACHER')")
    public Assignment getAssignmentById(@PathVariable Long id) {
        return assignmentService.getAssignmentById(id);
    }
}
