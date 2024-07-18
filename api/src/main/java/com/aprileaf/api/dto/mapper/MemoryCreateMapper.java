package com.aprileaf.api.dto.mapper;

import com.aprileaf.api.dto.MemoryCreateServiceDto;
import com.aprileaf.api.dto.request.MemoryCreateRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MemoryCreateMapper {

    MemoryCreateMapper INSTANCE = Mappers.getMapper(MemoryCreateMapper.class);

    MemoryCreateServiceDto toMemoryCreateServiceDto(MemoryCreateRequest request);
}
