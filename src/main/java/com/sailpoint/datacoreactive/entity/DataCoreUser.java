package com.sailpoint.datacoreactive.entity;

import com.sailpoint.projectUtils.JsonStringListConverter;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Entity
@Table(name = "datacore_users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class DataCoreUser {

    @Id
    private Long id;

    private String email;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "sensitive_user")
    private Boolean sensitiveUser;

    @Column(name = "enable_audit_report_access")
    private Boolean enableAuditReportAccess;

    @Column(name = "enable_access_all_researches")
    private Boolean enableAccessAllResearches;

    @Column(name = "email_access_only")
    private Boolean emailAccessOnly;

    @Column(name = "locked_at")
    private String lockedAt;

    @Column(name = "cro_sponsor_name")
    private String croSponsorName;

    @Column(name = "associate_user")
    private Boolean associateUser;

    private Boolean deactivated;

    @Column(name = "deactivated_at")
    private String deactivatedAt;

    @Column(name = "system_role")
    private String systemRole;

    @Column(name = "last_edit_date")
    private String lastEditDate;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DataCoreUserRole> userRoles;

    @PrePersist
    @PreUpdate
    public void updateTimestamp() {
        this.lastEditDate = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'"));
    }

    // Inside DataCoreUser.java
    @Column(name = "assigned_research", columnDefinition = "TEXT")
    private String assignedResearch;

    @Column(name = "assigned_research_site", columnDefinition = "TEXT")
    private String assignedResearchSite;
}