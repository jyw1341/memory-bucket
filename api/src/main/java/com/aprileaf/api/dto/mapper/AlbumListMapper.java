package com.aprileaf.api.dto.mapper;

import com.aprileaf.api.dto.request.AlbumListRequest;
import com.aprileaf.api.dto.AlbumListServiceDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AlbumListMapper {

    AlbumListMapper INSTANCE = Mappers.getMapper(AlbumListMapper.class);

    AlbumListServiceDto toAlbumListServiceDto(Long memberId, AlbumListRequest albumListRequest);
}
