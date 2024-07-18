package com.aprileaf.api.dto.mapper;

import com.aprileaf.api.dto.SeriesUpdateServiceDto;
import com.aprileaf.api.dto.request.SeriesUpdateRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SeriesUpdateMapper {

    SeriesUpdateMapper INSTANCE = Mappers.getMapper(SeriesUpdateMapper.class);

    SeriesUpdateServiceDto toSeriesUpdateServiceDto(
            Long memberId,
            Long seriesId,
            SeriesUpdateRequest request);
}
