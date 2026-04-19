package com.sailpoint.datacoreactive.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "researches")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Research {

    @Id
    private Long id;
    private String name;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "protocol_number")
    private String protocolNumber;

    private String status;
    private String phase;

    @Column(name = "cross_research_id")
    private String crossResearchId; // The ID used for ManagedAttribute lookup

    @Column(name = "cro_sponsor_name")
    private String croSponsorName;

    @Column(name = "created_at")
    private String createdAt;

    @Column(name = "updated_at")
    private String updatedAt;

    @OneToMany(mappedBy = "research", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ResearchCountry> countries;
}