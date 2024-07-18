package com.aprileaf.api.repository;

import com.aprileaf.api.domain.AlbumMember;
import com.aprileaf.api.dto.AlbumMemberListServiceDto;

import java.util.List;

public interface AlbumMemberCustomRepository {

    List<AlbumMember> findAlbumMembers(AlbumMemberListServiceDto dto);
}
