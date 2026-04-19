package com.sailpoint.datacoreactive.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "research_countries")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ResearchCountry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "country_name")
    private String countryName;

    @ManyToOne
    @JoinColumn(name = "research_id", nullable = false)
    private Research research;
}