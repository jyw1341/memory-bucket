package com.aprileaf.api.dto.mapper;

import com.aprileaf.api.dto.request.AlbumUpdateRequest;
import com.aprileaf.api.dto.AlbumUpdateServiceDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AlbumUpdateMapper {

    AlbumUpdateMapper INSTANCE = Mappers.getMapper(AlbumUpdateMapper.class);

    AlbumUpdateServiceDto toAlbumUpdateServiceDto(Long memberId, Long albumId, AlbumUpdateRequest request);
}
