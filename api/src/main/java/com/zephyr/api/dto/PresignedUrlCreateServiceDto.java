package com.zephyr.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PresignedUrlCreateServiceDto {

    private Long memberId;
    private Integer fileIndex;
    private String fileName;
    private Long fileSize;
}
