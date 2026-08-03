package com.taguhyung.workhub.presentation.mapper.taskcategory;

import com.taguhyung.workhub.application.command.taskcategory.CreateTaskCategoryCommand;
import com.taguhyung.workhub.application.command.taskcategory.UpdateTaskCategoryCommand;
import com.taguhyung.workhub.presentation.response.taskcategory.TaskCategoryResponse;
import com.taguhyung.workhub.presentation.request.taskcategory.CreateTaskCategoryRequest;
import com.taguhyung.workhub.presentation.request.taskcategory.UpdateTaskCategoryRequest;
import com.taguhyung.workhub.domain.model.TaskCategory;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class TaskCategoryPresentationMapper {

    public CreateTaskCategoryCommand toCommand(
            Long projectId,
            CreateTaskCategoryRequest request) {
        return new CreateTaskCategoryCommand(
                projectId,
                request.name(),
                request.description(),
                request.color(),
                request.sortOrder());
    }

    public UpdateTaskCategoryCommand toUpdateCommand(
            Long id,
            Long projectId,
            UpdateTaskCategoryRequest request) {
        return new UpdateTaskCategoryCommand(
                id,
                projectId,
                request.name(),
                request.description(),
                request.color(),
                request.sortOrder());
    }

    public TaskCategoryResponse toResponse(TaskCategory taskCategory) {
        return new TaskCategoryResponse(
                taskCategory.getId(),
                taskCategory.getProjectId(),
                taskCategory.getName(),
                taskCategory.getDescription(),
                taskCategory.getColor(),
                taskCategory.getSortOrder(),
                taskCategory.getCreatedAt(),
                taskCategory.getUpdatedAt(),
                taskCategory.getDeletedAt());
    }

    public List<TaskCategoryResponse> toResponseList(List<TaskCategory> taskCategories) {
        return taskCategories.stream()
                .map(this::toResponse)
                .toList();
    }
}
