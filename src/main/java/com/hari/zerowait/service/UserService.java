package com.hari.zerowait.service;

import com.hari.zerowait.dto.LoginRequest;
import com.hari.zerowait.dto.LoginResponse;

public interface UserService {
    public LoginResponse login(LoginRequest loginRequest);
}
