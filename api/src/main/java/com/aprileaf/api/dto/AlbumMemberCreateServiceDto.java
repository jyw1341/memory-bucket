package com.aprileaf.api.dto;

import com.aprileaf.api.domain.Album;
import com.aprileaf.api.domain.Member;
import com.aprileaf.api.enums.AlbumMemberRole;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AlbumMemberCreateServiceDto {

    private final Member member;
    private final Album album;
    private final AlbumMemberRole role;
}
