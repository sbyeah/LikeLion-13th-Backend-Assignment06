package com.likelion.junseoungbin_new.post.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record PostSaveRequestDto(
        @NotNull(message = "플레이어 아이디를 필수로 입력해야 합니다.")
        Long playerId,
        @NotBlank(message = "장점을 필수로 입력해야 합니다.")
        @Size(min = 3, max = 20)
        String pros,
        @NotBlank(message = "단점을 필수로 입력해야 합니다.")
        @Size(min = 3, max = 20)
        String cons
) {
}