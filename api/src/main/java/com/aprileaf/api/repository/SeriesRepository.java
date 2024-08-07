package com.aprileaf.api.repository;

import com.aprileaf.api.domain.Series;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeriesRepository extends JpaRepository<Series, Long>, SeriesCustomRepository {

    List<Series> findByAlbumId(Long albumId);
}
