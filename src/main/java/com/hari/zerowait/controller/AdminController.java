package com.hari.zerowait.controller;

import com.hari.zerowait.dto.AdminLoginRequest;
import com.hari.zerowait.dto.AdminLoginResponse;
import com.hari.zerowait.dto.TokenRequest;
import com.hari.zerowait.model.Queue;
import com.hari.zerowait.service.AdminService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/getAllTokens")
    public ResponseEntity<Queue> getAllTokens(
            @RequestHeader("mobile") String mobile,
            @RequestHeader("sessionId") String sessionId,
            @RequestHeader("locationId") String locationId
    ){
        TokenRequest tokenRequest = new TokenRequest(mobile, sessionId, locationId, null, null);
        //don't expose db model directly to user in future releases
        Queue queue = adminService.getAllTokens(tokenRequest);
        if(queue == null) return ResponseEntity.badRequest().body(queue);
        return ResponseEntity.ok(queue);
    }

    @PostMapping("/setStatus")
    public ResponseEntity<?> setStatus(
            @RequestHeader("mobile") String mobile,
            @RequestHeader("sessionId") String sessionId,
            @RequestHeader("locationId") String locationId
    ){
        TokenRequest tokenRequest = new TokenRequest(mobile, sessionId, locationId, null, null);
        String status = adminService.setStatus(tokenRequest);
        if(status == null) return ResponseEntity.badRequest().body(status);
        return ResponseEntity.ok(status);
    }
}
