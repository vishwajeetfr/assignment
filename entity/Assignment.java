package com.leucine.assignment.entity;

import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Builder(toBuilder = true)
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Assignment")
public class Assignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    @NotEmpty(message = "Title cannot be empty")
    private String title;
    private String description;
    @Column(name = "due_date")
    @FutureOrPresent(message = "Due date cannot be in the past")
    private LocalDate dueDate;
    private String clazz;
    @Column(name = "attachment_url")
    private String attachmentUrl;

    @ManyToOne
    @JoinColumn(name = "class_id", referencedColumnName = "id")
    private Class classId;

    @ManyToOne
    @JoinColumn(name = "created_by", referencedColumnName = "id")
    private ApplicationUser createdBy;



}
