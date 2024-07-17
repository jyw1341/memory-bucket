package com.zephyr.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
public class PostCreateServiceDto {

    private final Long memberId;
    private final Long albumId;
    private final Long seriesId;
    private final String title;
    private final String description;
    private final LocalDate memoryDate;
    private final String coverMemoryRequestId;
    private final List<MemoryCreateServiceDto> memoryCreateServiceDtos;
}
