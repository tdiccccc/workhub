package com.taguhyung.workhub.presentation.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.taguhyung.workhub.domain.exception.ProjectNotFoundException;
import com.taguhyung.workhub.presentation.response.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProjectNotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleProjectNotFoundException(
            ProjectNotFoundException exception) {
        return ResponseEntity
                .status(404)
                .body(new ApiResponse<>(404, exception.getMessage(), null));
    }
}