package com.sailpoint.AuditTrailX.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "audit_trail_users")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuditTrailUser {

    @Id
    @Column(name = "account_id")
    private String accountId;

    @Column(name = "email")
    private String email;

    @Column(name = "username")
    private String username;

    @Column(name = "employee_id")
    private String employeeId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "country")
    private String country;

    @Column(name = "address")
    private String address;

    @Column(name = "office_location")
    private String officeLocation;

    @Column(name = "title")
    private String title;

    @Column(name = "roles_json", columnDefinition = "TEXT")
    private String rolesJson;

    @Column(name = "research_json", columnDefinition = "TEXT")
    private String researchJson;
}