package com.likelion.junseoungbin_new.member.api.dto.request;

import com.likelion.junseoungbin_new.member.domain.Part;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record MemberSaveRequestDto(
        @NotBlank(message = "닉네임은 필수로 입력해야 합니다.")
        @Size(min = 3, max = 20)
        String nickname,

        int age,

        Part part
) {
}