package com.hari.zerowait.service;

import com.hari.zerowait.dto.UserLoginRequest;
import com.hari.zerowait.dto.UserLoginResponse;

public interface UserService {
    public UserLoginResponse login(UserLoginRequest userLoginRequest);
}
