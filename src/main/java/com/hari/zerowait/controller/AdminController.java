package com.hari.zerowait.controller;

import com.hari.zerowait.dto.AdminLoginRequest;
import com.hari.zerowait.dto.AdminLoginResponse;
import com.hari.zerowait.service.AdminService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private AdminService adminService;

    public AdminController(AdminService adminService){
        this.adminService = adminService;
    }

    @PostMapping("/login")
    public ResponseEntity<AdminLoginResponse> login(@RequestBody @Valid AdminLoginRequest adminLoginRequest){
        AdminLoginResponse adminLoginResponse = adminService.login(adminLoginRequest);
        if(adminLoginResponse.getSessionId()==null)return ResponseEntity.badRequest().body(adminLoginResponse);
        return ResponseEntity.ok(adminLoginResponse);
    }
}
