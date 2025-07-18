package com.hari.zerowait.controller;

import com.hari.zerowait.dto.LoginRequest;
import com.hari.zerowait.dto.LoginResponse;
import com.hari.zerowait.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest){
        LoginResponse loginResponse = userService.login(loginRequest);
        if(loginResponse.getSessionId()==null)return ResponseEntity.badRequest().body(loginResponse);
        return ResponseEntity.ok(loginResponse);
    }
}
