package com.aprileaf.api.repository;

import com.aprileaf.api.domain.AlbumMember;
import com.aprileaf.api.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlbumMemberRepository extends JpaRepository<AlbumMember, Long>, AlbumMemberCustomRepository {

    List<AlbumMember> findByAlbumId(Long albumId);

    List<AlbumMember> findByMember(Member member);

    void deleteByAlbumIdAndMemberId(Long albumId, Long memberId);
}
