package com.likelion.junseoungbin_new.member.api.dto.response;

import com.likelion.junseoungbin_new.member.api.dto.request.MemberJoinReqDto;
import com.likelion.junseoungbin_new.member.domain.Member;
import lombok.Builder;

@Builder
public record MemberInfoResDto(
        String email,
        String name,
        String token
) {
    public static MemberInfoResDto of(Member member, String token) {
        return MemberInfoResDto.builder()
                .email(member.getEmail())
                .name(member.getName())
                .token(token)
                .build();
    }
}