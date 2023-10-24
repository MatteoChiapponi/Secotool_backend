package com.group2.secotool_app.bussiness.mapper;

import com.group2.secotool_app.model.dto.FeatureDto;
import com.group2.secotool_app.model.entity.Feature;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FeatureDtoMapper {
    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "icon", target = "icon")
    FeatureDto toFeatureDto (Feature feature);
}
