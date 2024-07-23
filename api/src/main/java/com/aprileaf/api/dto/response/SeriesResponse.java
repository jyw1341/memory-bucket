package com.aprileaf.api.dto.response;

import com.aprileaf.api.domain.Series;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class SeriesResponse {

    private Long id;
    private String name;

    public SeriesResponse(Series series) {
        this.id = series.getId();
        this.name = series.getName();
    }
}
