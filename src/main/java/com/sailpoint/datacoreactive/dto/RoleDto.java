package com.sailpoint.datacoreactive.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleDto {

    private Long id;
    private String name;
    private String description;
    @JsonProperty("research_level") private Boolean researchLevel;
    @JsonProperty("site_level") private Boolean siteLevel;
}