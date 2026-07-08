package com.taguhyung.workhub.presentation.request.project;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record UpdateProjectRequest(
        @NotBlank(message = "プロジェクト名は必須です") String name,

        @NotNull(message = "受注金額は必須です") @PositiveOrZero(message = "受注金額は0以上で入力してください") BigDecimal amount,

        @NotBlank(message = "案件概要は必須です") String description,

        LocalDateTime startedAt,

        LocalDateTime endedAt,

        Boolean isActive) {

}
