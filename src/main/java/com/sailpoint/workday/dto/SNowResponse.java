package com.sailpoint.workday.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class SNowResponse {
    private String message;
    private int statusCode;
    private String ServiceNowId;
}