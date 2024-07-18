package com.aprileaf.api.repository;

import com.aprileaf.api.dto.response.SeriesAggregationResponse;

import java.util.List;

public interface SeriesCustomRepository {

    List<SeriesAggregationResponse> findSeriesAggregationDto(Long albumId);

}
