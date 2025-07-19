package com.hari.zerowait.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TokenRequest {
    private String mobile;
    private String sessionId;
    private String locationId;
    private String userName;
    private String userMobile;
}
