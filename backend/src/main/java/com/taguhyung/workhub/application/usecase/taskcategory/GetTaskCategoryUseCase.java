package com.taguhyung.workhub.application.usecase.taskcategory;

import com.taguhyung.workhub.domain.exception.TaskCategoryNotFoundException;
import com.taguhyung.workhub.domain.model.TaskCategory;
import com.taguhyung.workhub.domain.repository.TaskCategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GetTaskCategoryUseCase {
    private final TaskCategoryRepository taskCategoryRepository;

    public GetTaskCategoryUseCase(TaskCategoryRepository taskCategoryRepository) {
        this.taskCategoryRepository = taskCategoryRepository;
    }

    @Transactional(readOnly = true)
    public TaskCategory execute(Long id) {
        return taskCategoryRepository.findById(id)
                .orElseThrow(() -> new TaskCategoryNotFoundException(id));
    }
}
