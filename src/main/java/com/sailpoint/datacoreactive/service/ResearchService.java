package com.sailpoint.datacoreactive.service;

import com.sailpoint.datacoreactive.dto.ResearchDto;
import com.sailpoint.datacoreactive.dto.ResearchResponse;
import com.sailpoint.datacoreactive.entity.Research;
import com.sailpoint.datacoreactive.entity.ResearchCountry;
import com.sailpoint.datacoreactive.repository.ResearchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ResearchService {

    private final ResearchRepository researchRepository;

    public ResearchResponse getAllResearches() {
        List<ResearchDto> dtoList = researchRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());

        return ResearchResponse.builder().researches(dtoList).build();
    }

    @Transactional
    public ResearchResponse createResearches(ResearchResponse request) {
        if (request == null || request.getResearches() == null) return null;

        List<Research> entities = request.getResearches().stream()
                .map(this::mapToEntity)
                .collect(Collectors.toList());

        researchRepository.saveAll(entities);
        return getAllResearches(); // Returns the wrapped list after saving
    }

    private ResearchDto mapToDto(Research entity) {
        return ResearchDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .fullName(entity.getFullName())
                .protocolNumber(entity.getProtocolNumber())
                .status(entity.getStatus())
                .phase(entity.getPhase())
                .crossResearchId(entity.getCrossResearchId())
                .croSponsorName(entity.getCroSponsorName())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .countriesConfigured(entity.getCountries().stream()
                        .map(ResearchCountry::getCountryName)
                        .collect(Collectors.toList()))
                .build();
    }

    private Research mapToEntity(ResearchDto dto) {
        Research research = Research.builder()
                .id(dto.getId())
                .name(dto.getName())
                .fullName(dto.getFullName())
                .protocolNumber(dto.getProtocolNumber())
                .status(dto.getStatus())
                .phase(dto.getPhase())
                .crossResearchId(dto.getCrossResearchId())
                .croSponsorName(dto.getCroSponsorName())
                .createdAt(dto.getCreatedAt())
                .updatedAt(dto.getUpdatedAt())
                .build();

        if (dto.getCountriesConfigured() != null) {
            List<ResearchCountry> countries = dto.getCountriesConfigured().stream()
                    .map(c -> ResearchCountry.builder().countryName(c).research(research).build())
                    .collect(Collectors.toList());
            research.setCountries(countries);
        }
        return research;
    }
}