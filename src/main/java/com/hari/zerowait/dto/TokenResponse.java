package com.hari.zerowait.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TokenResponse {
    private int initialQueuePosition;
    private int currentQueuePosition;
    private String token;
}
