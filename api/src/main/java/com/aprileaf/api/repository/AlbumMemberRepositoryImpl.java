package com.aprileaf.api.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.aprileaf.api.domain.AlbumMember;
import com.aprileaf.api.dto.AlbumMemberListServiceDto;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.aprileaf.api.domain.QAlbumMember.albumMember;

@RequiredArgsConstructor
public class AlbumMemberRepositoryImpl implements AlbumMemberCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<AlbumMember> findAlbumMembers(AlbumMemberListServiceDto dto) {
        return jpaQueryFactory.selectFrom(albumMember)
                .where(albumIdEq(dto.getAlbumId()))
                .fetch();
    }

    private BooleanExpression albumIdEq(Long albumId) {
        if (albumId == null) {
            return null;
        }
        return albumMember.album.id.eq(albumId);
    }

    private BooleanExpression memberIdEq(Long memberId) {
        if (memberId == null) {
            return null;
        }
        return albumMember.member.id.eq(memberId);
    }
}
