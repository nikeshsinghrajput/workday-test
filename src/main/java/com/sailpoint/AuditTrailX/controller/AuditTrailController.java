package com.sailpoint.AuditTrailX.controller;


import com.sailpoint.AuditTrailX.dto.AuditTrailResponseDto;
import com.sailpoint.AuditTrailX.service.AuditTrailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/audit-trail")
public class AuditTrailController {

    @Autowired
    private AuditTrailService service;

    @PostMapping("/users")
    public ResponseEntity<String> postUsers(@RequestBody AuditTrailResponseDto data) {
        service.saveAuditUsers(data);
        return ResponseEntity.ok("Audit Trail Users successfully processed into one table.");
    }

    @GetMapping("/users")
    public ResponseEntity<AuditTrailResponseDto> getUsers() {
        return ResponseEntity.ok(service.getAllUsers());
    }
}