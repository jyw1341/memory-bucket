package com.aprileaf.api.repository;

import com.aprileaf.api.domain.Post;
import com.aprileaf.api.dto.PostSearchServiceDto;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface PostCustomRepository {

    Page<Post> search(PostSearchServiceDto dto);

    Optional<Post> findByIdFetchMemories(Long postId);

    Optional<Post> findByIdFetchMemberAndSeriesAndMemories(Long postId);
}
