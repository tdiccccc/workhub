package com.taguhyung.workhub.presentation.controller.auth;

import com.taguhyung.workhub.presentation.response.ApiResponse;
import com.taguhyung.workhub.presentation.response.auth.CurrentUserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class CurrentUserController {

    @GetMapping("/current-user")
    public ResponseEntity<ApiResponse<CurrentUserResponse>> currentUser(
            Authentication authentication) {
        CurrentUserResponse response = new CurrentUserResponse(
                authentication.getName());

        return ResponseEntity.ok(
                new ApiResponse<>(200, "Current user fetched", response));
    }
}