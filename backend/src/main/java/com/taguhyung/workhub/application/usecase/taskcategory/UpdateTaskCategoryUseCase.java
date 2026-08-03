package com.taguhyung.workhub.application.usecase.taskcategory;

import com.taguhyung.workhub.application.command.taskcategory.UpdateTaskCategoryCommand;
import com.taguhyung.workhub.domain.exception.TaskCategoryNotFoundException;
import com.taguhyung.workhub.domain.model.TaskCategory;
import com.taguhyung.workhub.domain.repository.TaskCategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UpdateTaskCategoryUseCase {
    private final TaskCategoryRepository taskCategoryRepository;

    public UpdateTaskCategoryUseCase(
            TaskCategoryRepository taskCategoryRepository) {
        this.taskCategoryRepository = taskCategoryRepository;
    }

    @Transactional
    public TaskCategory execute(UpdateTaskCategoryCommand command) {
        TaskCategory taskCategory = taskCategoryRepository.findById(command.id())
                .orElseThrow(() -> new TaskCategoryNotFoundException(command.id()));

        taskCategory.update(
                command.projectId(),
                command.name(),
                command.description(),
                command.color(),
                command.sortOrder());

        return taskCategoryRepository.save(taskCategory);
    }
}
