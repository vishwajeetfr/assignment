package com.leucine.assignment.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Builder(toBuilder = true)
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Submission")
public class Submission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "assignment_id", referencedColumnName = "id")
    private Assignment assignment;

    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private ApplicationUser student;

    @Column(name = "submission_text")
    private String submissionText;
    @Column(name = "attachment_url")
    private String attachmentUrl;
    @Column(name = "submission_date")
    private LocalDateTime submissionDate;

    @Column(name = "has_graded")
    private String hasGraded;

}
