package com.sailpoint.datacoreactive.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sailpoint.datacoreactive.dto.*;
import com.sailpoint.datacoreactive.entity.*;
import com.sailpoint.datacoreactive.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DataCoreUserService {

    private final DataCoreUserRepository userRepository;
    private final RoleRepository roleRepository;
    private final DataCoreUserRoleRepository userRoleRepository;
    private final AssignmentRepository assignmentRepository;
    private final ObjectMapper objectMapper;

    public List<UserAccessResponse> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public UserAccessResponse createUserAccess(UserAccessRequest request) {
        DataCoreUser user = userRepository.save(mapToUserEntity(request.getUser()));
        processRolesAndAssignments(user, request.getRoles(), request.getAssignments());
        return mapToResponse(user);
    }

    @Transactional
    public UserAccessResponse updateUserAccess(Long userId, UserAccessRequest request) {
        DataCoreUser user = mapToUserEntity(request.getUser());
        user.setId(userId);
        user = userRepository.save(user);

        userRoleRepository.deleteByUserId(userId);

        processRolesAndAssignments(user, request.getRoles(), request.getAssignments());
        return mapToResponse(user);
    }

    private void processRolesAndAssignments(DataCoreUser user, List<RoleDto> roleDtos, Map<String, Object> assignments) {
        if (roleDtos == null) return;

        for (RoleDto roleDto : roleDtos) {
            Role role = roleRepository.findById(roleDto.getId())
                    .orElseGet(() -> roleRepository.save(mapToRoleEntity(roleDto)));

            DataCoreUserRole userRole = userRoleRepository.save(
                    DataCoreUserRole.builder().user(user).role(role).build()
            );

            if (assignments != null && assignments.containsKey(role.getName())) {
                assignmentRepository.save(
                        Assignment.builder()
                                .assignmentKey(role.getName())
                                .assignmentValue(convertObjectToJson(assignments.get(role.getName())))
                                .userRole(userRole)
                                .build()
                );
            }
        }
    }

    private UserAccessResponse mapToResponse(DataCoreUser user) {
        List<DataCoreUserRole> userRoles = userRoleRepository.findByUserId(user.getId());
        List<RoleDto> roleDtos = new ArrayList<>();
        Map<String, Object> assignmentsMap = new LinkedHashMap<>();

        for (DataCoreUserRole ur : userRoles) {
            roleDtos.add(mapToRoleDto(ur.getRole()));

            List<Assignment> assignments = assignmentRepository.findByUserRoleId(ur.getId());
            for (Assignment a : assignments) {
                assignmentsMap.put(a.getAssignmentKey(), convertJsonToObject(a.getAssignmentValue()));
            }
        }

        return UserAccessResponse.builder()
                .user(mapToUserDto(user))
                .roles(roleDtos)
                .assignments(assignmentsMap)
                .build();
    }

    private String convertObjectToJson(Object value) {
        try { return objectMapper.writeValueAsString(value); }
        catch (JsonProcessingException e) { throw new RuntimeException(e); }
    }

    private Object convertJsonToObject(String value) {
        try { return objectMapper.readValue(value, Object.class); }
        catch (Exception e) { return value; }
    }

    private DataCoreUser mapToUserEntity(DataCoreUserDto dto) {
        return DataCoreUser.builder()
                .id(dto.getId())
                .email(dto.getEmail())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .sensitiveUser(dto.getSensitiveUser())
                .enableAuditReportAccess(dto.getEnableAuditReportAccess())
                .enableAccessAllResearches(dto.getEnableAccessAllResearches())
                .emailAccessOnly(dto.getEmailAccessOnly())
                .lockedAt(dto.getLockedAt())
                .croSponsorName(dto.getCroSponsorName())
                .assignedResearch(String.join(",", dto.getAssignedResearch()))
                .assignedResearchSite(String.join(",", dto.getAssignedResearchSite()))
                .associateUser(dto.getAssociateUser())
                .deactivated(dto.getDeactivated())
                .deactivatedAt(dto.getDeactivatedAt())
                .systemRole(dto.getSystemRole())
                .lastEditDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")))
                .build();
    }

    private DataCoreUserDto mapToUserDto(DataCoreUser entity) {
        return DataCoreUserDto.builder()
                .id(entity.getId())
                .email(entity.getEmail())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .sensitiveUser(entity.getSensitiveUser())
                .enableAuditReportAccess(entity.getEnableAuditReportAccess())
                .enableAccessAllResearches(entity.getEnableAccessAllResearches())
                .emailAccessOnly(entity.getEmailAccessOnly())
                .lockedAt(entity.getLockedAt())
                .croSponsorName(entity.getCroSponsorName())
                .assignedResearch(entity.getAssignedResearch() != null ?
                        entity.getAssignedResearch().replaceAll("[\\[\\]\"]", "") : "")

                .assignedResearchSite(entity.getAssignedResearchSite() != null ?
                        entity.getAssignedResearchSite().replaceAll("[\\[\\]\"]", "") : "")
                .associateUser(entity.getAssociateUser())
                .deactivated(entity.getDeactivated())
                .deactivatedAt(entity.getDeactivatedAt())
                .systemRole(entity.getSystemRole())
                .lastEditDate(entity.getLastEditDate())
                .build();
    }

    private Role mapToRoleEntity(RoleDto dto) {
        return Role.builder().id(dto.getId()).name(dto.getName()).description(dto.getDescription())
                .researchLevel(dto.getResearchLevel())
                .siteLevel(dto.getSiteLevel()).build();
    }

    private RoleDto mapToRoleDto(Role entity) {
        return RoleDto.builder().id(entity.getId()).name(entity.getName()).description(entity.getDescription())
                .researchLevel(entity.getResearchLevel()).siteLevel(entity.getSiteLevel()).build();
    }
}