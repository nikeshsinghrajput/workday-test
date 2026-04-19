package com.sailpoint.datacoreactive.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResearchDto {

    private Long id;
    private String name;

    @JsonProperty("full_name")
    private String fullName;

    @JsonProperty("protocol_number")
    private String protocolNumber;

    private String status;
    private String phase;

    @JsonProperty("countries_configured")
    private List<String> countriesConfigured;

    @JsonProperty("crossResearchId")
    private String crossResearchId;

    @JsonProperty("croSponsorName")
    private String croSponsorName;

    @JsonProperty("createdAt")
    private String createdAt;

    @JsonProperty("updatedAt")
    private String updatedAt;
}