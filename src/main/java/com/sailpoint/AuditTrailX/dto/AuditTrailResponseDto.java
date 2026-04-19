package com.sailpoint.AuditTrailX.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuditTrailResponseDto {
    @JsonProperty("pagination")
    private PaginationDto pagination;

    @JsonProperty("results")
    private List<AuditTrailUserDto> results;
}