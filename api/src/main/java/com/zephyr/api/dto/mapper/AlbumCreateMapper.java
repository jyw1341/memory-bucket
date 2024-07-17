package com.zephyr.api.dto.mapper;

import com.zephyr.api.dto.request.AlbumCreateRequest;
import com.zephyr.api.dto.AlbumCreateServiceDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AlbumCreateMapper {
    AlbumCreateMapper INSTANCE = Mappers.getMapper(AlbumCreateMapper.class);

    AlbumCreateServiceDto toAlbumCreateServiceDto(Long memberId, AlbumCreateRequest request);
}
