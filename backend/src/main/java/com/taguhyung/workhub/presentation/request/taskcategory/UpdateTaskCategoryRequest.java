package com.taguhyung.workhub.presentation.request.taskcategory;

import jakarta.validation.constraints.NotBlank;

public record UpdateTaskCategoryRequest(
                @NotBlank(message = "タスクカテゴリー名は必須です") String name,

                @NotBlank(message = "タスクカテゴリー概要は必須です") String description,

                String color,

                Integer sortOrder) {

}
