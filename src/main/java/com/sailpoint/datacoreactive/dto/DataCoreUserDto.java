package com.sailpoint.datacoreactive.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class DataCoreUserDto {

    private Long id;
    private String email;

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    @JsonProperty("sensitive_user")
    private Boolean sensitiveUser;

    @JsonProperty("enable_audit_report_access")
    private Boolean enableAuditReportAccess;

    @JsonProperty("enable_access_all_researches")
    private Boolean enableAccessAllResearches;

    @JsonProperty("email_access_only")
    private Boolean emailAccessOnly;

    @JsonProperty("locked_at")
    private String lockedAt;

    @JsonProperty("cro_sponsor_name")
    private String croSponsorName;

    @JsonProperty("associate_user")
    private Boolean associateUser;

    private Boolean deactivated;

    @JsonProperty("deactivated_at")
    private String deactivatedAt;

    @JsonProperty("system_role")
    private String systemRole;

    @JsonProperty("last_edit_date")
    private String lastEditDate;

    @JsonProperty("assigned_research")
    private String assignedResearch;

    @JsonProperty("assigned_research_site")
    private String assignedResearchSite;
}