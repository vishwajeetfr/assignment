package com.leucine.assignment.repository;

import com.leucine.assignment.entity.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AssignmentRepository extends JpaRepository<Assignment, Long> {
    List<Assignment> findByCreatedBy_Id(Long teacherId);
    List<Assignment> findAllByClazz(String clazz);
}
