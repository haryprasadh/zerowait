package com.hari.zerowait.dto;

import lombok.Getter;

@Getter
public class LoginResponse {
    private String sessionId;
    private String message;

    public LoginResponse(String sessionId, String message) {
        this.sessionId = sessionId;
        this.message = message;
    }
}
