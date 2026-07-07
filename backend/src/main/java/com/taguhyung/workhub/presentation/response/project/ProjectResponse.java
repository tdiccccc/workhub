package com.taguhyung.workhub.presentation.response.project;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ProjectResponse(
        Long id,
        String name,
        BigDecimal amount,
        String description,
        LocalDateTime startedAt,
        LocalDateTime endedAt,
        Boolean isActive) {
}