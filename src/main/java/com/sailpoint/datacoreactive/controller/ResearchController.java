package com.sailpoint.datacoreactive.controller;

import com.sailpoint.datacoreactive.dto.ResearchResponse;
import com.sailpoint.datacoreactive.service.ResearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/data-core")
@RequiredArgsConstructor
public class ResearchController {

    private final ResearchService researchService;

    @GetMapping("/research")
    public ResearchResponse getResearches() {
        return researchService.getAllResearches();
    }

    @PostMapping("/research")
    public ResearchResponse createResearches(@RequestBody ResearchResponse request) {
        return researchService.createResearches(request);
    }
}