package com.sailpoint.datacoreactive.dto;

import lombok.*;
import java.util.List;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserAccessRequest {
    private DataCoreUserDto user;
    private List<RoleDto> roles;
    private Map<String, Object> assignments;
}