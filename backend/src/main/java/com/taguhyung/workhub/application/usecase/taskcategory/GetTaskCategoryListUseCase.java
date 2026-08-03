package com.taguhyung.workhub.application.usecase.taskcategory;

import com.taguhyung.workhub.domain.model.TaskCategory;
import com.taguhyung.workhub.domain.repository.TaskCategoryRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GetTaskCategoryListUseCase {
    private final TaskCategoryRepository taskCategoryRepository;

    public GetTaskCategoryListUseCase(TaskCategoryRepository taskCategoryRepository) {
        this.taskCategoryRepository = taskCategoryRepository;
    }

    @Transactional(readOnly = true)
    public List<TaskCategory> execute(Long projectId) {
        return taskCategoryRepository.findByProjectId(projectId);
    }

}
