package com.hari.zerowait.model;

import com.hari.zerowait.dto.Location;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@Document(collection = "admins")
public class Admin {
    @Id
    private String id;
    private String name;
    private String secret;
    private String sessionId;
    private List<Location> locations;
}
