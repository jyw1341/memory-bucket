package com.aprileaf.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostDeleteServiceDto {

    private final Long memberId;
    private final Long postId;
}
