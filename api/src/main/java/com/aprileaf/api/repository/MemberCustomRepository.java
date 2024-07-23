package com.aprileaf.api.repository;

import com.aprileaf.api.domain.Member;

import java.util.Optional;

public interface MemberCustomRepository {

    Optional<Member> findByEmail(String email);
}
