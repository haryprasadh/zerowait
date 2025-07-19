package com.hari.zerowait.service.impl;

import com.hari.zerowait.dto.AdminLoginRequest;
import com.hari.zerowait.dto.AdminLoginResponse;
import com.hari.zerowait.dto.Location;
import com.hari.zerowait.model.Admin;
import com.hari.zerowait.model.Queue;
import com.hari.zerowait.repository.AdminRepository;
import com.hari.zerowait.repository.QueueRepository;
import com.hari.zerowait.service.AdminService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;

    private final QueueRepository queueRepository;

    public AdminServiceImpl(AdminRepository adminRepository, QueueRepository queueRepository){
        this.adminRepository = adminRepository;
        this.queueRepository = queueRepository;
    }

    @Override
    public AdminLoginResponse login(AdminLoginRequest adminLoginRequest) {
        String mobile = adminLoginRequest.getMobile();
        String name = adminLoginRequest.getName();
        String secret = adminLoginRequest.getSecret();
        String locationName = adminLoginRequest.getLocationName();

        Optional<Admin> existingAdmin = adminRepository.findById(mobile);
        Admin admin = null;
        if(existingAdmin.isPresent()){
            admin = existingAdmin.get();
            if(!admin.getSecret().equals(secret)){
                return new AdminLoginResponse(null, "please verify your submitted details!", null);
            }
        }

        List<Location> locations = new ArrayList<>();
        locations.add(new Location(locationName, UUID.randomUUID().toString()));
        Queue newQueue = new Queue();
        newQueue.setLocationId(locations.getFirst().getLocationId());
        newQueue.setLocationName(locationName);
        newQueue.setOpenStatus("close");
        newQueue.setTokens(new ArrayList<>());

        if(existingAdmin.isEmpty()){
            Admin newAdmin = new Admin();
            newAdmin.setId(mobile);
            newAdmin.setName(name);
            newAdmin.setSecret(secret);
            String newSessionId = UUID.randomUUID().toString();
            newAdmin.setSessionId(newSessionId);
            newAdmin.setLocations(locations);
            adminRepository.save(newAdmin);
            queueRepository.save(newQueue);
            return new AdminLoginResponse(newSessionId, "Hi "+ name +", zerowait wishes you happy businness & customers at your "+locationName, locations);
        }

        boolean exists = admin.getLocations().stream().anyMatch(loc -> loc.getLocationName().equalsIgnoreCase(locationName));
        if(!exists){
            admin.getLocations().add(locations.getFirst());
            adminRepository.save(admin);
            queueRepository.save(newQueue);
        }
        return new AdminLoginResponse(admin.getSessionId(), null, admin.getLocations());
    }
}
