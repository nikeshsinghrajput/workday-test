package com.sailpoint.AuditTrailX.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sailpoint.AuditTrailX.dto.*;
import com.sailpoint.AuditTrailX.entity.AuditTrailUser;
import com.sailpoint.AuditTrailX.repository.AuditTrailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuditTrailService {

    @Autowired
    private AuditTrailRepository repository;

    @Autowired
    private ObjectMapper objectMapper;

    public void saveAuditUsers(AuditTrailResponseDto responseDto) {
        if (responseDto.getResults() == null) return;

        List<AuditTrailUser> entities = responseDto.getResults().stream().map(dto -> {
            try {
                return AuditTrailUser.builder()
                        .accountId(dto.getAccountId())
                        .email(dto.getEmail())
                        .username(dto.getUsername())
                        .employeeId(dto.getEmployeeId())
                        .firstName(dto.getFirstName())
                        .lastName(dto.getLastName())
                        .country(dto.getCountry())
                        .address(dto.getAddress())
                        .officeLocation(dto.getOfficeLocation())
                        .title(dto.getTitle())
                        .rolesJson(objectMapper.writeValueAsString(dto.getRoles()))
                        .researchJson(objectMapper.writeValueAsString(dto.getResearch()))
                        .build();
            } catch (Exception e) {
                throw new RuntimeException("Error mapping to DB Entity", e);
            }
        }).collect(Collectors.toList());

        repository.saveAll(entities);
    }

    public AuditTrailResponseDto getAllUsers() {
        List<AuditTrailUser> entities = repository.findAll();

        List<AuditTrailUserDto> dtos = entities.stream().map(entity -> {
            try {
                return AuditTrailUserDto.builder()
                        .accountId(entity.getAccountId())
                        .email(entity.getEmail())
                        .username(entity.getUsername())
                        .employeeId(entity.getEmployeeId())
                        .firstName(entity.getFirstName())
                        .lastName(entity.getLastName())
                        .country(entity.getCountry())
                        .address(entity.getAddress())
                        .officeLocation(entity.getOfficeLocation())
                        .title(entity.getTitle())
                        .roles(objectMapper.readValue(entity.getRolesJson(), new TypeReference<List<AuditRoleDto>>() {}))
                        .research(objectMapper.readValue(entity.getResearchJson(), new TypeReference<List<AuditResearchDto>>() {}))
                        .build();
            } catch (Exception e) {
                throw new RuntimeException("Error mapping to API DTO", e);
            }
        }).collect(Collectors.toList());

        return AuditTrailResponseDto.builder()
                .pagination(PaginationDto.builder()
                        .limit(100)
                        .offset(1)
                        .totalRecords(dtos.size())
                        .build())
                .results(dtos)
                .build();
    }
}