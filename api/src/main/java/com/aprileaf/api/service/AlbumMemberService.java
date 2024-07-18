package com.aprileaf.api.service;

import com.aprileaf.api.domain.AlbumMember;
import com.aprileaf.api.domain.Member;
import com.aprileaf.api.dto.AlbumMemberCreateServiceDto;
import com.aprileaf.api.dto.AlbumMemberDeleteServiceDto;
import com.aprileaf.api.dto.AlbumMemberListServiceDto;
import com.aprileaf.api.repository.AlbumMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlbumMemberService {

    private final AlbumMemberRepository albumMemberRepository;

    public void create(AlbumMemberCreateServiceDto dto) {
        AlbumMember albumMember = AlbumMember.builder()
                .member(dto.getMember())
                .album(dto.getAlbum())
                .role(dto.getRole())
                .build();

        albumMemberRepository.save(albumMember);
    }

    public List<AlbumMember> getList(AlbumMemberListServiceDto dto) {
        return albumMemberRepository.findAlbumMembers(dto);
    }

    public List<AlbumMember> getListByMember(Member member) {
        return albumMemberRepository.findByMember(member);
    }

    public List<AlbumMember> getListByAlbumId(AlbumMemberListServiceDto dto) {
        return albumMemberRepository.findByAlbumId(dto.getAlbumId());
    }

    public void delete(AlbumMemberDeleteServiceDto dto) {
        albumMemberRepository.deleteByAlbumIdAndMemberId(dto.getAlbumId(), dto.getTargetId());
    }
}
