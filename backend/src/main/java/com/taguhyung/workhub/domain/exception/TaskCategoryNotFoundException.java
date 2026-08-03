package com.taguhyung.workhub.domain.exception;

public class TaskCategoryNotFoundException extends RuntimeException {
    public TaskCategoryNotFoundException(Long id) {
        super("案件カテゴリーが見つかりません: " + id);
    }

}
