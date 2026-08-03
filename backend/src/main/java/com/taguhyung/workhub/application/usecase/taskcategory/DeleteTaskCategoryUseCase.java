package com.taguhyung.workhub.application.usecase.taskcategory;

import com.taguhyung.workhub.domain.exception.TaskCategoryNotFoundException;
import com.taguhyung.workhub.domain.model.TaskCategory;
import com.taguhyung.workhub.domain.repository.TaskCategoryRepository;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DeleteTaskCategoryUseCase {
    private final TaskCategoryRepository taskCategoryRepository;

    public DeleteTaskCategoryUseCase(
            TaskCategoryRepository taskCategoryRepository) {
        this.taskCategoryRepository = taskCategoryRepository;
    }

    @Transactional
    public void execute(Long id) {
        TaskCategory taskCategory = taskCategoryRepository.findById(id)
                .orElseThrow(() -> new TaskCategoryNotFoundException(id));

        taskCategory.delete(LocalDateTime.now());
        taskCategoryRepository.save(taskCategory);
    }
}
