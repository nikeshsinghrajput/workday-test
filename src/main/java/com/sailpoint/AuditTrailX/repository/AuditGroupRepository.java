package com.sailpoint.AuditTrailX.repository;

import com.sailpoint.AuditTrailX.entity.AuditGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditGroupRepository extends JpaRepository<AuditGroup, Long> {
}