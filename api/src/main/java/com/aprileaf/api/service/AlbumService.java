package com.aprileaf.api.service;

import com.aprileaf.api.config.S3ConfigurationProperties;
import com.aprileaf.api.domain.Album;
import com.aprileaf.api.domain.AlbumMember;
import com.aprileaf.api.domain.Member;
import com.aprileaf.api.dto.*;
import com.aprileaf.api.dto.mapper.AlbumMemberCreateMapper;
import com.aprileaf.api.enums.AlbumMemberRole;
import com.aprileaf.api.exception.AlbumNotFoundException;
import com.aprileaf.api.exception.ForbiddenException;
import com.aprileaf.api.repository.AlbumRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class AlbumService {

    private final AlbumRepository albumRepository;
    private final MemberService memberService;
    private final AlbumMemberService albumMemberService;
    private final MessageSource messageSource;
    private final S3ConfigurationProperties s3Properties;

    public Album create(AlbumCreateServiceDto dto) {
        Member member = memberService.get(dto.getMemberId());
        Album album = Album.builder()
                .owner(member)
                .title(dto.getTitle())
                .description(dto.getDescription())
                .thumbnailUrl(dto.getThumbnailUrl())
                .build();

        albumRepository.save(album);
        AlbumMemberCreateServiceDto serviceDto = AlbumMemberCreateMapper.INSTANCE.toAlbumMemberCreateServiceDto(
                album,
                member,
                AlbumMemberRole.ADMIN
        );
        albumMemberService.create(serviceDto);

        return album;
    }

    public Album get(Long albumId) {
        Album album = albumRepository.findById(albumId)
                .orElseThrow(() -> new AlbumNotFoundException(messageSource));

        setDefaultThumbnailUrlIfNull(album);

        return album;
    }

    public List<Album> getList(AlbumListServiceDto dto) {
        AlbumMemberListServiceDto albumMemberListServiceDto = new AlbumMemberListServiceDto(
                dto.getMemberId(),
                null
        );

        List<AlbumMember> albumMembers = albumMemberService.getList(albumMemberListServiceDto);
        List<Album> result = new ArrayList<>();

        for (AlbumMember albumMember : albumMembers) {
            Album album = albumMember.getAlbum();
            setDefaultThumbnailUrlIfNull(album);
            result.add(album);
        }

        return result;
    }

    public void update(AlbumUpdateServiceDto dto) {
        Album album = albumRepository.findById(dto.getAlbumId())
                .orElseThrow(() -> new AlbumNotFoundException(messageSource));

        validAlbumOwner(album, dto.getMemberId());
        album.setTitle(dto.getTitle());
        album.setDescription(dto.getDescription());
        album.setThumbnailUrl(dto.getThumbnailUrl());
    }

    public void delete(AlbumDeleteServiceDto dto) {
        Album album = albumRepository.findById(dto.getAlbumId())
                .orElseThrow(() -> new AlbumNotFoundException(messageSource));

        validAlbumOwner(album, dto.getMemberId());
        albumRepository.delete(album);
    }

    private void validAlbumOwner(Album album, Long memberId) {
        if (album.getOwner().getId().equals(memberId)) {
            return;
        }
        throw new ForbiddenException(messageSource);
    }

    private void setDefaultThumbnailUrlIfNull(Album album) {
        if (album.getThumbnailUrl() == null || album.getThumbnailUrl().isBlank()) {
            album.setThumbnailUrl(getDefaultAlbumThumbnailUrl());
        }
    }

    public String getDefaultAlbumThumbnailUrl() {
        return s3Properties.getEndPoint() + s3Properties.getBucketName() + s3Properties.getThumbnailUrl();
    }
}
