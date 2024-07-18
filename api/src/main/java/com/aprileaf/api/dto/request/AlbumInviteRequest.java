package com.aprileaf.api.dto.request;

import com.aprileaf.api.enums.AlbumMemberRole;
import lombok.Data;

@Data
public class AlbumInviteRequest {

    private final String username;
    private final AlbumMemberRole role;
}
