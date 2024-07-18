package com.aprileaf.api.dto.mapper;

import com.aprileaf.api.domain.Album;
import com.aprileaf.api.domain.Member;
import com.aprileaf.api.dto.AlbumMemberCreateServiceDto;
import com.aprileaf.api.enums.AlbumMemberRole;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AlbumMemberCreateMapper {
    AlbumMemberCreateMapper INSTANCE = Mappers.getMapper(AlbumMemberCreateMapper.class);

    AlbumMemberCreateServiceDto toAlbumMemberCreateServiceDto(Album album, Member member, AlbumMemberRole role);
}
