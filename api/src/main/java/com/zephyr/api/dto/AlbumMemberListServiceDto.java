package com.zephyr.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AlbumMemberListServiceDto {

    private final Long memberId;
    private final Long albumId;
}
