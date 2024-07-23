package com.aprileaf.api.dto.mapper;

import com.aprileaf.api.dto.AlbumDeleteServiceDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AlbumDeleteMapper {

    AlbumDeleteMapper INSTANCE = Mappers.getMapper(AlbumDeleteMapper.class);

    AlbumDeleteServiceDto toAlbumDeleteServiceDto(Long memberId, Long albumId);
}
