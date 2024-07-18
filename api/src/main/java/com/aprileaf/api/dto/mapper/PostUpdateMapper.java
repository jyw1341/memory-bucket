package com.aprileaf.api.dto.mapper;

import com.aprileaf.api.dto.PostUpdateServiceDto;
import com.aprileaf.api.dto.request.PostUpdateRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PostUpdateMapper {

    PostUpdateMapper INSTANCE = Mappers.getMapper(PostUpdateMapper.class);

    PostUpdateServiceDto toPostUpdateServiceDto(
            Long memberId,
            Long postId,
            PostUpdateRequest postUpdateRequest
    );
}
