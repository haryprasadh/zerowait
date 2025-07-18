package com.hari.zerowait.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@Document(collection="users")
public class User {
    @Id
    private Long id;
    private String name;
    private String secret;
}
