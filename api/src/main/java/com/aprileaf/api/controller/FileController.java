package com.aprileaf.api.controller;

import com.aprileaf.api.dto.PresignedUrlCreateServiceDto;
import com.aprileaf.api.dto.mapper.PresignedUrlCreateMapper;
import com.aprileaf.api.dto.request.PresignedUrlCreateRequest;
import com.aprileaf.api.dto.response.PresignedUrlCreateResponse;
import com.aprileaf.api.service.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/files")
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;

    @PostMapping
    public ResponseEntity<Map<Integer, String>> createPresignedUrl(@RequestBody List<PresignedUrlCreateRequest> request) {
        Long loginId = 1L;
        Map<Integer, String> result = new HashMap<>();
        List<PresignedUrlCreateServiceDto> serviceDtos = new ArrayList<>();

        for (PresignedUrlCreateRequest presignedUrlCreateRequest : request) {
            PresignedUrlCreateServiceDto serviceDto = PresignedUrlCreateMapper.INSTANCE.toFileCreateServiceDto(
                    loginId,
                    presignedUrlCreateRequest
            );
            serviceDtos.add(serviceDto);
        }

        List<PresignedUrlCreateResponse> presignedUrlCreateResponses = fileService.createPresignedUrl(serviceDtos);
        for (PresignedUrlCreateResponse response : presignedUrlCreateResponses) {
            result.put(response.getFileIndex(), response.getUrl());
        }

        return ResponseEntity.ok().body(result);
    }

}
