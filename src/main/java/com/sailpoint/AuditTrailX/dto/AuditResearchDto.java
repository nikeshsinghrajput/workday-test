package com.sailpoint.AuditTrailX.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuditResearchDto {
    @JsonProperty("srResearchNumber")
    private String srResearchNumber;

    @JsonProperty("researchTeamRole")
    private String researchTeamRole;

    @JsonProperty("researchUserstartdate")
    private String researchUserStartDate;

    @JsonProperty("researchUserenddate")
    private String researchUserEndDate;

    @JsonProperty("blinded")
    private String blinded;
}