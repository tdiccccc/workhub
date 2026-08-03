package com.taguhyung.workhub.application.command.taskcategory;

public record UpdateTaskCategoryCommand(
        Long id,
        Long projectId,
        String name,
        String description,
        String color,
        Integer sortOrder) {

}
