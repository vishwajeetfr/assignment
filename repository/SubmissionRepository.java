package com.leucine.assignment.repository;

import com.leucine.assignment.entity.Submission;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SubmissionRepository extends JpaRepository<Submission, Long> {
    List<Submission> findByAssignment_Id(Long assignmentId);
    List<Submission> findByStudent_Id(Long studentId);
}
