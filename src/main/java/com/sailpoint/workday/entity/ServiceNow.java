package com.sailpoint.workday.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "servicenow")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ServiceNow {

    private String servicenowId;//Hr
    @Id
    private String employeeId;//Hr
    private String userName;//Hr
    private String firstName;//Hr
    private String lastName;
    private String email;//AD
    private String JobTitle;//Hr
    private String managerId;//Hr
    private String distinguishedName;//AD

}