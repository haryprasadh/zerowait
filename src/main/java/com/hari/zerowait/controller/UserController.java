package com.hari.zerowait.controller;

import com.hari.zerowait.dto.UserLoginRequest;
import com.hari.zerowait.dto.UserLoginResponse;
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
    public ResponseEntity<UserLoginResponse> login(@RequestBody UserLoginRequest userLoginRequest){
        UserLoginResponse userLoginResponse = userService.login(userLoginRequest);
        if(userLoginResponse.getSessionId()==null)return ResponseEntity.badRequest().body(userLoginResponse);
        return ResponseEntity.ok(userLoginResponse);
    }
}
