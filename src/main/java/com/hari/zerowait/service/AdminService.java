package com.hari.zerowait.service;

import com.hari.zerowait.dto.AdminLoginRequest;
import com.hari.zerowait.dto.AdminLoginResponse;

public interface AdminService {
    public AdminLoginResponse login(AdminLoginRequest adminLoginRequest);
}
