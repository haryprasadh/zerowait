package com.hari.zerowait.repository;

import com.hari.zerowait.model.Admin;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends MongoRepository<Admin, Long> {

}
