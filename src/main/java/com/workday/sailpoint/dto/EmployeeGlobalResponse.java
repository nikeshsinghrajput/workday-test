package com.workday.sailpoint.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class EmployeeGlobalResponse {
    private String message;
    private int statusCode;
    private String EmployeeId;
}