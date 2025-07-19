package com.hari.zerowait.service.impl;

import com.hari.zerowait.dto.AdminLoginRequest;
import com.hari.zerowait.dto.AdminLoginResponse;
import com.hari.zerowait.dto.Location;
import com.hari.zerowait.model.Admin;
import com.hari.zerowait.repository.AdminRepository;
import com.hari.zerowait.service.AdminService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AdminServiceImpl implements AdminService {

    private AdminRepository repo;

    public AdminServiceImpl(AdminRepository adminRepository){
        this.repo = adminRepository;
    }

    @Override
    public AdminLoginResponse login(AdminLoginRequest adminLoginRequest) {
        Long mobile = adminLoginRequest.getMobile();
        String name = adminLoginRequest.getName();
        String secret = adminLoginRequest.getSecret();
        String locationName = adminLoginRequest.getLocationName();

        Optional<Admin> existing = repo.findById(mobile);
        List<Location> locations = new ArrayList<>();
        locations.add(new Location(locationName, UUID.randomUUID().toString()));
        if(existing.isEmpty()){
            Admin newAdmin = new Admin();
            newAdmin.setId(mobile);
            newAdmin.setName(name);
            newAdmin.setSecret(secret);
            String newSessionId = UUID.randomUUID().toString();
            newAdmin.setSessionId(newSessionId);
            newAdmin.setLocations(locations);
            repo.save(newAdmin);
            return new AdminLoginResponse(newSessionId, "Hi "+ name +", zerowait wishes you happy businness & customers at your "+locationName, locations);
        }

        Admin admin = existing.get();
        if(!admin.getSecret().equals(secret)){
            return new AdminLoginResponse(null, "please verify your submitted details!", null);
        }
        boolean exists = admin.getLocations().stream().anyMatch(loc -> loc.getLocationName().equalsIgnoreCase(locationName));
        if(!exists){
            admin.getLocations().add(locations.getFirst());
            repo.save(admin);
        }
        return new AdminLoginResponse(admin.getSessionId(), null, admin.getLocations());
    }
}
