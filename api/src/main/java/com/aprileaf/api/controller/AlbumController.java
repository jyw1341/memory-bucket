package com.aprileaf.api.controller;

import com.aprileaf.api.domain.Album;
import com.aprileaf.api.dto.AlbumCreateServiceDto;
import com.aprileaf.api.dto.AlbumDeleteServiceDto;
import com.aprileaf.api.dto.AlbumListServiceDto;
import com.aprileaf.api.dto.AlbumUpdateServiceDto;
import com.aprileaf.api.dto.mapper.AlbumCreateMapper;
import com.aprileaf.api.dto.mapper.AlbumDeleteMapper;
import com.aprileaf.api.dto.mapper.AlbumListMapper;
import com.aprileaf.api.dto.mapper.AlbumUpdateMapper;
import com.aprileaf.api.dto.request.AlbumCreateRequest;
import com.aprileaf.api.dto.request.AlbumListRequest;
import com.aprileaf.api.dto.request.AlbumUpdateRequest;
import com.aprileaf.api.dto.response.AlbumListResponse;
import com.aprileaf.api.dto.response.AlbumResponse;
import com.aprileaf.api.service.AlbumService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/albums")
public class AlbumController {

    private final AlbumService albumService;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody AlbumCreateRequest request) {
        Long loginId = 1L;
        AlbumCreateServiceDto serviceDto = AlbumCreateMapper.INSTANCE.toAlbumCreateServiceDto(loginId, request);
        Album album = albumService.create(serviceDto);

        return ResponseEntity.created(URI.create("/albums/" + album.getId())).build();
    }

    @GetMapping("/{albumId}")
    public AlbumResponse get(@PathVariable Long albumId) {
        Album album = albumService.get(albumId);

        return new AlbumResponse(album);
    }

    @GetMapping
    public List<AlbumListResponse> getList(@ModelAttribute AlbumListRequest request) {
        Long loginId = 1L;
        AlbumListServiceDto serviceDto = AlbumListMapper.INSTANCE.toAlbumListServiceDto(loginId, request);
        List<Album> albums = albumService.getList(serviceDto);

        return albums.stream().map(AlbumListResponse::new).toList();
    }

    @PatchMapping("/{albumId}")
    public void update(@PathVariable Long albumId, @RequestBody AlbumUpdateRequest request) {
        Long loginId = 1L;
        AlbumUpdateServiceDto serviceDto = AlbumUpdateMapper.INSTANCE.toAlbumUpdateServiceDto(loginId, albumId, request);
        albumService.update(serviceDto);
    }

    @DeleteMapping("/{albumId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long albumId) {
        Long loginId = 1L;
        AlbumDeleteServiceDto serviceDto = AlbumDeleteMapper.INSTANCE.toAlbumDeleteServiceDto(loginId, albumId);
        albumService.delete(serviceDto);
    }
}
