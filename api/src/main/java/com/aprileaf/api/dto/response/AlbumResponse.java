package com.aprileaf.api.dto.response;

import com.aprileaf.api.domain.Album;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor // Add an all-args constructor for easier instantiation
public class AlbumResponse {

    private final Long id;
    private final String title;
    private final String description;
    private final String thumbnailUrl;

    public AlbumResponse(Album album) {
        this.id = album.getId();
        this.title = album.getTitle();
        this.description = album.getDescription();
        this.thumbnailUrl = album.getThumbnailUrl();
    }
}
