package com.hari.zerowait.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class UserLoginResponse {
    private String sessionId;
    private String message;
    private List<String> locations;

    public UserLoginResponse(String sessionId, String message, List<String> locations) {
        this.sessionId = sessionId;
        this.message = message;
        this.locations = locations;
    }
}
