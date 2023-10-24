package com.group2.secotool_app.bussiness.mapper;

import com.group2.secotool_app.model.dto.request.FeatureRequestDto;
import com.group2.secotool_app.model.entity.Feature;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FeatureMapper {
    @Mapping(source = "name", target = "name")
    @Mapping(source = "icon", target = "icon")
    Feature toFeature (FeatureRequestDto feature);
}