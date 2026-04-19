package com.sailpoint.datacoreactive.dto;

import lombok.*;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResearchResponse {
    private List<ResearchDto> researches;
}