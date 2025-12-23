package com.workday.sailpoint.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.workday.sailpoint.entity.ServiceNow;

public interface SNowRepository extends JpaRepository<ServiceNow, String>{

}