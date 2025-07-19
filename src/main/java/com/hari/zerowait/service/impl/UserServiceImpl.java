package com.hari.zerowait.service.impl;

import com.hari.zerowait.dto.UserLoginRequest;
import com.hari.zerowait.dto.UserLoginResponse;
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

    public UserLoginResponse login(UserLoginRequest userLoginRequest) {
        Long mobile = userLoginRequest.getMobile();
        String name = userLoginRequest.getName();
        String secret = userLoginRequest.getSecret();

        Optional<User> existing = repo.findById(mobile);
        if(existing.isEmpty()){
            User newUser = new User();
            newUser.setId(mobile);
            newUser.setName(name);
            newUser.setSecret(secret);
            String sessionId = UUID.randomUUID().toString();
            newUser.setSessionId(sessionId);
            repo.save(newUser);
            return new UserLoginResponse(sessionId, "Hi " + name + ", welcome to ZeroWait – where waiting is strictly prohibited. See you around!");
        }

        User user = existing.get();
        if(user.getName().equals(name) && user.getSecret().equals(secret)){
            return new UserLoginResponse(user.getSessionId(), null);
        }

        return new UserLoginResponse(null, "please verify your submitted details!");
    }
}
