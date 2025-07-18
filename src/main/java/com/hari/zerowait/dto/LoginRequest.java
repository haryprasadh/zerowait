package com.hari.zerowait.dto;

import lombok.Getter;

@Getter
public class LoginRequest {
    private String name;
    private Long mobile;
    private String secret;
}
