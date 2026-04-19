package com.sailpoint.AuditTrailX.controller;

import com.sailpoint.AuditTrailX.dto.AuditGroupDto;
import com.sailpoint.AuditTrailX.dto.GroupResponseDto;
import com.sailpoint.AuditTrailX.service.AuditGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/audit-groups")
public class AuditGroupController {

    @Autowired
    private AuditGroupService service;

    @PostMapping("/role")
    public ResponseEntity<String> postGroups(@RequestBody List<AuditGroupDto> roles) {
        service.saveGroups(roles);
        return ResponseEntity.ok("Groups populated successfully.");
    }

    @GetMapping("/role")
    public ResponseEntity<GroupResponseDto> getGroups() {
        return ResponseEntity.ok(service.getAllGroups());
    }
}