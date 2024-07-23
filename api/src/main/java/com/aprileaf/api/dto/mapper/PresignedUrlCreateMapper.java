package com.aprileaf.api.dto.mapper;

import com.aprileaf.api.dto.request.PresignedUrlCreateRequest;
import com.aprileaf.api.dto.PresignedUrlCreateServiceDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PresignedUrlCreateMapper {

    PresignedUrlCreateMapper INSTANCE = Mappers.getMapper(PresignedUrlCreateMapper.class);

    PresignedUrlCreateServiceDto toFileCreateServiceDto(Long memberId, PresignedUrlCreateRequest presignedUrlCreateRequest);
}
