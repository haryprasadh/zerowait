package com.hari.zerowait.service;

import com.hari.zerowait.dto.TokenRequest;
import com.hari.zerowait.dto.TokenResponse;
import com.hari.zerowait.dto.UserLoginRequest;
import com.hari.zerowait.dto.UserLoginResponse;

public interface UserService {
    public UserLoginResponse login(UserLoginRequest userLoginRequest);
    public TokenResponse addToken(TokenRequest tokenRequest);
}
