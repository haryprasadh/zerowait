package com.hari.zerowait.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class AdminLoginResponse {
    private String sessionId;
    private String message;
    private List<Location> locations;
}
