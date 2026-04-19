package com.sailpoint.datacoreactive.repository;

import com.sailpoint.datacoreactive.entity.DataCoreUserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DataCoreUserRoleRepository extends JpaRepository<DataCoreUserRole, Long> {
    List<DataCoreUserRole> findByUserId(Long userId);
    void deleteByUserId(Long userId);
}