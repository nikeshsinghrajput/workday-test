package com.sailpoint.workday.controller;

import com.sailpoint.workday.service.SNowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sailpoint.workday.entity.ServiceNow;
import com.sailpoint.workday.dto.SNowResponse;

@RestController
@RequestMapping("/servicenow")
public class SNowController {

    @Autowired
    private SNowService serviceNowService;

    @PostMapping("/")
    public ResponseEntity<SNowResponse> storeServiceNowInstance(@RequestBody ServiceNow serviceNowDetails) {
        SNowResponse res=serviceNowService.createServiceNowInstance(serviceNowDetails);
        return ResponseEntity.status(res.getStatusCode()).body(res);

    }

    public ResponseEntity<SNowResponse> checkServiceNowInstance(@RequestBody String employeeId){
        SNowResponse res=serviceNowService.checkServiceNowInstance(employeeId);
        return ResponseEntity.status(res.getStatusCode()).body(res);
    }
}