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
@Table(name = "audit_groups")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuditGroup {

    @Id
    @Column(name = "group_id")
    private Long roleId;

    @Column(name = "group_description")
    private String roleDesc;
}
