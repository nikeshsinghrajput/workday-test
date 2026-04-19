package com.sailpoint.AuditTrailX.repository;

import com.sailpoint.AuditTrailX.entity.AuditTrailUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditTrailRepository extends JpaRepository<AuditTrailUser, String> {
}