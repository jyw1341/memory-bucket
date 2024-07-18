package com.aprileaf.api.dto.mapper;

import com.aprileaf.api.dto.SeriesCreateServiceDto;
import com.aprileaf.api.dto.request.SeriesCreateRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SeriesCreateMapper {

    SeriesCreateMapper INSTANCE = Mappers.getMapper(SeriesCreateMapper.class);

    SeriesCreateServiceDto toSeriesCreateServiceDto(Long albumId, SeriesCreateRequest request);
}
