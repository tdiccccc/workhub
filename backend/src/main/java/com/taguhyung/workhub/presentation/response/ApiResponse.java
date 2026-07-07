package com.taguhyung.workhub.presentation.response;

public record ApiResponse<T>(
                int status,
                String message,
                T data) {
}