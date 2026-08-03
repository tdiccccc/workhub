package com.taguhyung.workhub.application.usecase.taskcategory;

import com.taguhyung.workhub.application.command.taskcategory.CreateTaskCategoryCommand;
import com.taguhyung.workhub.domain.model.TaskCategory;
import com.taguhyung.workhub.domain.repository.TaskCategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CreateTaskCategoryUseCase {
    private final TaskCategoryRepository taskCategoryRepository;

    public CreateTaskCategoryUseCase(
            TaskCategoryRepository taskCategoryRepository) {
        this.taskCategoryRepository = taskCategoryRepository;
    }

    @Transactional
    public TaskCategory execute(CreateTaskCategoryCommand command) {
        TaskCategory taskCategory = TaskCategory.create(
                command.projectId(),
                command.name(),
                command.description(),
                command.color(),
                command.sortOrder());

        return taskCategoryRepository.save(taskCategory);
    }
}
