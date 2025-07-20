package com.hari.zerowait.service;

import com.hari.zerowait.dto.AdminLoginRequest;
import com.hari.zerowait.dto.AdminLoginResponse;
import com.hari.zerowait.dto.TokenRequest;
import com.hari.zerowait.model.Queue;

public interface AdminService {
    public AdminLoginResponse login(AdminLoginRequest adminLoginRequest);

    public Queue getAllTokens(TokenRequest tokenRequest);

    public String setStatus(TokenRequest tokenRequest);
}
