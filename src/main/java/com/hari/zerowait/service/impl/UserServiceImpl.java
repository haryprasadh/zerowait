package com.hari.zerowait.service.impl;

import com.hari.zerowait.dto.LoginRequest;
import com.hari.zerowait.dto.LoginResponse;
import com.hari.zerowait.model.User;
import com.hari.zerowait.repository.UserRepository;
import com.hari.zerowait.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repo;

    public LoginResponse login(LoginRequest loginRequest) {
        Long mobile = loginRequest.getMobile();
        String name = loginRequest.getName();
        String secret = loginRequest.getSecret();

        Optional<User> existing = repo.findById(mobile);
        String sessionId = UUID.randomUUID().toString();

        if(existing.isEmpty()){
            User newUser = new User();
            newUser.setId(mobile);
            newUser.setName(name);
            newUser.setSecret(secret);
            repo.save(newUser);
            return new LoginResponse(sessionId, "Hi " + name + ", welcome to ZeroWait – where waiting is strictly prohibited. See you around!");
        }

        User user = existing.get();
        if(user.getName().equals(name) && user.getSecret().equals(secret)){
            return new LoginResponse(sessionId, null);
        }

        return new LoginResponse(null, "please verify your name & password");
    }
}
