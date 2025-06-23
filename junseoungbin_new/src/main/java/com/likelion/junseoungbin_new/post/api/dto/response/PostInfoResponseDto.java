package com.likelion.junseoungbin_new.post.api.dto.response;

import com.likelion.junseoungbin_new.post.domain.Post;
import lombok.Builder;

@Builder
public record PostInfoResponseDto(
        String pros,
        String cons,
        String writer
) {
    public static PostInfoResponseDto from(Post post) {
        return PostInfoResponseDto.builder()
                .pros(post.getPros())
                .cons(post.getCons())
                .writer(post.getMember().getNickname())
                .build();
    }
}