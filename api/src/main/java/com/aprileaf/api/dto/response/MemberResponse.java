package com.aprileaf.api.dto.response;

import com.aprileaf.api.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class MemberResponse {

    private final Long id;
    private final String username;
    private final String profileImageUrl;

    public MemberResponse(Member member) {
        this.id = member.getId();
        this.username = member.getUsername();
        this.profileImageUrl = member.getProfileUrl();
    }
}
