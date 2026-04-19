package com.sailpoint.datacoreactive.repository;

import com.sailpoint.datacoreactive.entity.DataCoreUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DataCoreUserRepository extends JpaRepository<DataCoreUser, Long> {
}