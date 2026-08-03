package com.taguhyung.workhub.presentation.request.taskcategory;

import jakarta.validation.constraints.NotBlank;

public record CreateTaskCategoryRequest(
        @NotBlank(message = "案件カテゴリー名は必須です") String name,

        @NotBlank(message = "案件カテゴリー概要は必須です") String description,

        String color,

        Integer sortOrder) {

}
