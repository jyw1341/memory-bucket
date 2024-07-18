package com.aprileaf.api.dto.mapper;

import com.aprileaf.api.dto.request.MemberCreateRequest;
import com.aprileaf.api.dto.MemberCreateServiceDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MemberCreateMapper {

    MemberCreateMapper INSTANCE = Mappers.getMapper(MemberCreateMapper.class);

    MemberCreateServiceDto toMemberCreateServiceDto(MemberCreateRequest request);
}
