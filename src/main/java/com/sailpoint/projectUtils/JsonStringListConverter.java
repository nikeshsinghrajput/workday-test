package com.sailpoint.projectUtils;

import com.sailpoint.projectUtils.ProjectConfig;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.util.List;

@Converter
public class JsonStringListConverter implements AttributeConverter<List<String>, String> {

    @Override
    public String convertToDatabaseColumn(List<String> attribute) {
        return ProjectConfig.convertListToJson(attribute);
    }

    @Override
    public List<String> convertToEntityAttribute(String dbData) {
        return ProjectConfig.convertJsonToList(dbData);
    }
}