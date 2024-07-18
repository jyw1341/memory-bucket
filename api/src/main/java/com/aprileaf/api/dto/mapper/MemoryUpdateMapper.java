package com.aprileaf.api.dto.mapper;

import com.aprileaf.api.dto.MemoryUpdateServiceDto;
import com.aprileaf.api.dto.request.MemoryUpdateRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MemoryUpdateMapper {

    MemoryUpdateMapper INSTANCE = Mappers.getMapper(MemoryUpdateMapper.class);

    MemoryUpdateServiceDto toMemoryUpdateServiceDto(
            MemoryUpdateRequest request
    );
}
