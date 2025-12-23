package com.sailpoint.workday.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sailpoint.workday.entity.ServiceNow;

public interface SNowRepository extends JpaRepository<ServiceNow, String>{

}