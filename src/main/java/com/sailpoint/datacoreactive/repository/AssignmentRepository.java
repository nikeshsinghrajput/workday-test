package com.sailpoint.datacoreactive.repository;

import com.sailpoint.datacoreactive.entity.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AssignmentRepository extends JpaRepository<Assignment, Long> {
    List<Assignment> findByUserRoleId(Long userRoleId);
    void deleteByUserRoleId(Long userRoleId);
}