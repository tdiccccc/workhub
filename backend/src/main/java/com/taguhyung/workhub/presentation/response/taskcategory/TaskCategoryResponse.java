package com.taguhyung.workhub.presentation.response.taskcategory;

import java.time.LocalDateTime;

public record TaskCategoryResponse(
        Long id,
        Long projectId,
        String name,
        String description,
        String color,
        Integer sortOrder,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        LocalDateTime deletedAt) {
}
