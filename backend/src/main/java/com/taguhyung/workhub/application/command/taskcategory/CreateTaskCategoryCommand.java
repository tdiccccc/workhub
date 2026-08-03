package com.taguhyung.workhub.application.command.taskcategory;

public record CreateTaskCategoryCommand(
        Long projectId,
        String name,
        String description,
        String color,
        Integer sortOrder) {

}
