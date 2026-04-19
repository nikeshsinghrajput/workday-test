package com.sailpoint.AuditTrailX.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AuditTrailUserDto {
    @JsonProperty("email")
    private String email;

    @JsonProperty("username")
    private String username;

    @JsonProperty("employeeId")
    private String employeeId;

    @JsonProperty("firstName")
    private String firstName;

    @JsonProperty("lastName")
    private String lastName;

    @JsonProperty("country")
    private String country;

    @JsonProperty("address")
    private String address;

    @JsonProperty("officeLocation")
    private String officeLocation;

    @JsonProperty("title")
    private String title;

    @JsonProperty("accountId")
    private String accountId;

    @JsonProperty("roles")
    private List<AuditRoleDto> roles;

    @JsonProperty("research")
    private List<AuditResearchDto> research;
}