package com.aprileaf.api.dto.mapper;

import com.aprileaf.api.dto.AlbumMemberDeleteServiceDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AlbumMemberDeleteMapper {

    AlbumMemberDeleteMapper INSTANCE = Mappers.getMapper(AlbumMemberDeleteMapper.class);

    AlbumMemberDeleteServiceDto toAlbumMemberDeleteServiceDto(Long albumId, Long targetId);
}
