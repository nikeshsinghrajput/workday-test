package com.sailpoint.datacoreactive.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "assignments")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Assignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "assignment_key")
    private String assignmentKey;

    @Lob
    @Column(name = "assignment_value", columnDefinition = "LONGTEXT")
    private String assignmentValue;

    @ManyToOne
    @JoinColumn(name = "user_role_id", nullable = false)
    private DataCoreUserRole userRole;
}