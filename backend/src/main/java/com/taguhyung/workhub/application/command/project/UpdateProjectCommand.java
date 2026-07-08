package com.taguhyung.workhub.application.command.project;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record UpdateProjectCommand(
        Long id,
        String name,
        BigDecimal amount,
        String description,
        LocalDateTime startedAt,
        LocalDateTime endedAt,
        Boolean isActive) {

}
