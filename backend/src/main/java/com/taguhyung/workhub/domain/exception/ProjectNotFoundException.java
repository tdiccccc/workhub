package com.taguhyung.workhub.domain.exception;

public class ProjectNotFoundException extends RuntimeException {
    public ProjectNotFoundException(Long id) {
        super("プロジェクトが見つかりません: " + id);
    }
}
