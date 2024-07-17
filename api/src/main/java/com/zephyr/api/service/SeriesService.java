package com.zephyr.api.service;

import com.zephyr.api.domain.Album;
import com.zephyr.api.domain.Series;
import com.zephyr.api.dto.SeriesCreateServiceDto;
import com.zephyr.api.dto.SeriesUpdateServiceDto;
import com.zephyr.api.dto.response.SeriesAggregationResponse;
import com.zephyr.api.exception.SeriesNotFoundException;
import com.zephyr.api.repository.SeriesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SeriesService {

    private final SeriesRepository seriesRepository;
    private final AlbumService albumService;
    private final MessageSource messageSource;

    public Series create(SeriesCreateServiceDto dto) {
        Album album = albumService.get(dto.getAlbumId());

        Series series = Series.builder()
                .album(album)
                .name(dto.getSeriesName())
                .build();
        seriesRepository.save(series);

        return series;
    }

    public Series get(Long seriesId) {
        if (seriesId == null) {
            return null;
        }

        return seriesRepository.findById(seriesId)
                .orElseThrow(() -> new SeriesNotFoundException(messageSource));
    }

    public List<Series> getList(Long albumId) {
        return seriesRepository.findByAlbumId(albumId);
    }

    public List<SeriesAggregationResponse> getSeriesAggregations(Long albumId) {
        return seriesRepository.findSeriesAggregationDto(albumId);
    }

    @Transactional
    public void updateName(SeriesUpdateServiceDto dto) {
        Series series = seriesRepository.findById(dto.getSeriesId())
                .orElseThrow(() -> new SeriesNotFoundException(messageSource));

        series.setName(dto.getSeriesName());
    }

    public void delete(Long seriesId) {
        Series series = seriesRepository.findById(seriesId)
                .orElseThrow(() -> new SeriesNotFoundException(messageSource));

        seriesRepository.delete(series);
    }
}
