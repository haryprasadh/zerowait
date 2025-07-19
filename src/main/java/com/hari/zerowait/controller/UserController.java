package com.hari.zerowait.controller;

import com.hari.zerowait.dto.TokenRequest;
import com.hari.zerowait.dto.TokenResponse;
import com.hari.zerowait.dto.UserLoginRequest;
import com.hari.zerowait.dto.UserLoginResponse;
import com.hari.zerowait.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<UserLoginResponse> login(@RequestBody @Valid UserLoginRequest userLoginRequest){
        UserLoginResponse userLoginResponse = userService.login(userLoginRequest);
        if(userLoginResponse.getSessionId()==null)return ResponseEntity.badRequest().body(userLoginResponse);
        return ResponseEntity.ok(userLoginResponse);
    }

    @PostMapping("/addToken")
    public ResponseEntity<TokenResponse> addToken(
            @RequestHeader("mobile") String mobile,
            @RequestHeader("sessionId") String sessionId,
            @RequestHeader("locationId") String locationId
    ){
        TokenRequest tokenRequest = new TokenRequest(mobile, sessionId, locationId, null, null);
        TokenResponse tokenResponse = userService.addToken(tokenRequest);
        if(tokenResponse.getToken() == null)return ResponseEntity.badRequest().body(tokenResponse);
        return ResponseEntity.ok(tokenResponse);
    }
}
