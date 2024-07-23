package com.aprileaf.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SeriesCreateServiceDto {

    private final Long albumId;
    private final String seriesName;
}
