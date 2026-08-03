package com.taguhyung.workhub.presentation.controller.taskcategory;

import com.taguhyung.workhub.application.usecase.taskcategory.GetTaskCategoryListUseCase;
import com.taguhyung.workhub.domain.model.TaskCategory;
import com.taguhyung.workhub.presentation.mapper.taskcategory.TaskCategoryPresentationMapper;
import com.taguhyung.workhub.presentation.response.ApiResponse;
import com.taguhyung.workhub.presentation.response.taskcategory.TaskCategoryResponse;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/projects/{projectId}/task-categories")
public class GetTaskCategoryListController {
    private final GetTaskCategoryListUseCase usecase;
    private final TaskCategoryPresentationMapper mapper;

    public GetTaskCategoryListController(
            GetTaskCategoryListUseCase usecase,
            TaskCategoryPresentationMapper mapper) {
        this.usecase = usecase;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<TaskCategoryResponse>>> index(
            @PathVariable Long projectId) {
        List<TaskCategory> taskCategories = usecase.execute(projectId);

        return ResponseEntity.ok(
                new ApiResponse<>(200, "TaskCategories fetched", mapper.toResponseList(taskCategories)));
    }

}