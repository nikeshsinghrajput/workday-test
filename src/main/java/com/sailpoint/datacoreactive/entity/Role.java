package com.sailpoint.datacoreactive.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "roles")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Role {

    @Id
    private Long id;

    private String name;
    private String description;
    private Boolean researchLevel;
    private Boolean siteLevel;
}