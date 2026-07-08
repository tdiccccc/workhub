package com.taguhyung.workhub.presentation.exception;

import com.taguhyung.workhub.domain.exception.ProjectNotFoundException;
import com.taguhyung.workhub.presentation.response.ApiResponse;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * プロジェクトが見つからないときの例外
     * 
     * @param exception
     * @return
     */
    @ExceptionHandler(ProjectNotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleProjectNotFoundException(
            ProjectNotFoundException exception) {
        return ResponseEntity
                .status(404)
                .body(new ApiResponse<>(404, exception.getMessage(), null));
    }

    /**
     * バリデーション時の例外
     * 
     * @param exception
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Map<String, String>>> handleValidationException(
            MethodArgumentNotValidException exception) {
        Map<String, String> errors = new HashMap<>();

        exception.getBindingResult()
                .getFieldErrors()
                .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

        return ResponseEntity
                .badRequest()
                .body(new ApiResponse<>(400, "Validation failed", errors));
    }
}