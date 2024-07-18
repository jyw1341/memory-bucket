package com.aprileaf.api.controller;

import com.aprileaf.api.domain.Member;
import com.aprileaf.api.dto.MemberCreateServiceDto;
import com.aprileaf.api.dto.mapper.MemberCreateMapper;
import com.aprileaf.api.dto.request.MemberCreateRequest;
import com.aprileaf.api.dto.response.MemberResponse;
import com.aprileaf.api.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    public MemberResponse create(@RequestBody MemberCreateRequest request) {
        MemberCreateServiceDto serviceDto = MemberCreateMapper.INSTANCE.toMemberCreateServiceDto(request);
        Member member = memberService.create(serviceDto);

        return new MemberResponse(member);
    }
}
