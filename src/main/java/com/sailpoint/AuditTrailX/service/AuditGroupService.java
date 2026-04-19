package com.sailpoint.AuditTrailX.service;

import com.sailpoint.AuditTrailX.dto.AuditGroupDto;
import com.sailpoint.AuditTrailX.dto.GroupResponseDto;
import com.sailpoint.AuditTrailX.dto.PaginationDto;
import com.sailpoint.AuditTrailX.entity.AuditGroup;
import com.sailpoint.AuditTrailX.repository.AuditGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuditGroupService {

    @Autowired
    private AuditGroupRepository repository;

    public void saveGroups(List<AuditGroupDto> dtos) {
        List<AuditGroup> entities = dtos.stream().map(dto ->
                AuditGroup.builder()
                        .roleId(dto.getRoleId())
                        .roleDesc(dto.getRoleDesc())
                        .build()
        ).collect(Collectors.toList());

        repository.saveAll(entities);
    }

    public GroupResponseDto getAllGroups() {
        List<AuditGroup> entities = repository.findAll();

        List<AuditGroupDto> results = entities.stream().map(entity ->
                AuditGroupDto.builder()
                        .roleId(entity.getRoleId())
                        .roleDesc(entity.getRoleDesc())
                        .build()
        ).collect(Collectors.toList());

        return GroupResponseDto.builder()
                .pagination(PaginationDto.builder()
                        .limit(100)
                        .offset(1)
                        .totalRecords(results.size())
                        .build())
                .results(results)
                .build();
    }
}