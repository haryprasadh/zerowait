package com.hari.zerowait.service.impl;

import com.hari.zerowait.dto.TokenRequest;
import com.hari.zerowait.dto.TokenResponse;
import com.hari.zerowait.dto.UserLoginRequest;
import com.hari.zerowait.dto.UserLoginResponse;
import com.hari.zerowait.model.Queue;
import com.hari.zerowait.model.Token;
import com.hari.zerowait.model.User;
import com.hari.zerowait.repository.QueueRepository;
import com.hari.zerowait.repository.UserRepository;
import com.hari.zerowait.service.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final QueueRepository queueRepository;

    public UserServiceImpl(UserRepository userRepository, QueueRepository queueRepository){
        this.userRepository = userRepository;
        this.queueRepository = queueRepository;
    }

    public UserLoginResponse login(UserLoginRequest userLoginRequest) {
        String mobile = userLoginRequest.getMobile();
        String name = userLoginRequest.getName();
        String secret = userLoginRequest.getSecret();

        Optional<User> existing = userRepository.findById(mobile);
        if(existing.isEmpty()){
            User newUser = new User();
            newUser.setId(mobile);
            newUser.setName(name);
            newUser.setSecret(secret);
            String sessionId = UUID.randomUUID().toString();
            newUser.setSessionId(sessionId);
            newUser.setLocations(new ArrayList<>());
            userRepository.save(newUser);
            return new UserLoginResponse(sessionId, "Hi " + name + ", welcome to ZeroWait – where waiting is strictly prohibited. See you around!", null);
        }

        User user = existing.get();
        if(user.getName().equals(name) && user.getSecret().equals(secret)){
            return new UserLoginResponse(user.getSessionId(), null, user.getLocations());
        }

        return new UserLoginResponse(null, "please verify your submitted details!", null);
    }

    @Override
    public TokenResponse addToken(TokenRequest tokenRequest) {
        String mobile = tokenRequest.getMobile();
        String sessionId = tokenRequest.getSessionId();
        String locationId = tokenRequest.getLocationId();
        Optional<User> existingUser = userRepository.findById(mobile);

        TokenResponse tokenResponse = new TokenResponse(-1,-1, null);
        if(existingUser.isPresent()){
            User user = existingUser.get();
            if(user.getSessionId().equals(sessionId)){
                Optional<Queue> existingQueue = queueRepository.findById(locationId);
                if(existingQueue.isPresent() && existingQueue.get().getOpenStatus().equals("open")){
                    Queue queue = existingQueue.get();
                    Optional<Token> matchedToken = queue.getTokens().stream()
                            .filter(token -> token.getUserMobile().equals(mobile)).findFirst();
                    if(matchedToken.isPresent()){
                        tokenResponse.setInitialQueuePosition(matchedToken.get().getInitialSerialNumber());
                        tokenResponse.setCurrentQueuePosition(matchedToken.get().getCurrentSerialNumber());
                        tokenResponse.setToken(matchedToken.get().getToken());
                    }else{
                        Token newToken = new Token();
                        newToken.setCurrentSerialNumber(queue.getTokens().size()+1);
                        newToken.setInitialSerialNumber(queue.getTokens().size()+1);
                        newToken.setSkippedCount(0);
                        newToken.setUserName(user.getName());
                        newToken.setUserMobile(user.getId());
                        String generatedToken = String.format("%05d", new Random().nextInt(100000));
                        newToken.setToken(generatedToken);
                        queue.getTokens().add(newToken);
                        tokenResponse.setCurrentQueuePosition(newToken.getCurrentSerialNumber());
                        tokenResponse.setInitialQueuePosition(newToken.getInitialSerialNumber());
                        tokenResponse.setToken(newToken.getToken());
                        queueRepository.save(queue);
                    }
                }
            }
        }
        return tokenResponse;
    }
}
