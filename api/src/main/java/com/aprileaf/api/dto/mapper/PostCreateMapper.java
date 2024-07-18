package com.aprileaf.api.dto.mapper;

import com.aprileaf.api.dto.MemoryCreateServiceDto;
import com.aprileaf.api.dto.PostCreateServiceDto;
import com.aprileaf.api.dto.request.PostCreateRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PostCreateMapper {

    PostCreateMapper INSTANCE = Mappers.getMapper(PostCreateMapper.class);

    PostCreateServiceDto toPostCreateServiceDto(
            Long memberId,
            Long albumId,
            PostCreateRequest request,
            List<MemoryCreateServiceDto> memoryCreateServiceDtos
    );
}
