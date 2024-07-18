package com.aprileaf.api.dto.response;

import com.aprileaf.api.domain.Album;
import lombok.Data;

@Data
public class AlbumListResponse {

    private final Long id;
    private final String title;
    private final String description;
    private final String thumbnailUrl;
    private final MemberResponse owner;
//    private final Integer albumMemberCount;

    public AlbumListResponse(Album album) {
        this.id = album.getId();
        this.title = album.getTitle();
        this.description = album.getDescription();
        this.thumbnailUrl = album.getThumbnailUrl();
        this.owner = new MemberResponse(album.getOwner());
//        this.albumMemberCount = album.getAlbumMembers().size();
    }
}
