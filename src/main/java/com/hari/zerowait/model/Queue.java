package com.hari.zerowait.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@Document(collection = "queues")
public class Queue {
    @Id
    private String locationId;
    private String locationName;
    private String openStatus;
    private List<Token> tokens;
}
