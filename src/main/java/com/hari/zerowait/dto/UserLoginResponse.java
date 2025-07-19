package com.hari.zerowait.dto;

import lombok.Getter;

@Getter
public class UserLoginResponse {
    private String sessionId;
    private String message;

    public UserLoginResponse(String sessionId, String message) {
        this.sessionId = sessionId;
        this.message = message;
    }
}
