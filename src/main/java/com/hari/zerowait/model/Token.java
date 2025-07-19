package com.hari.zerowait.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Token {
    private int initialSerialNumber;
    private int currentSerialNumber;
    private int skippedCount;
    private String token;
    private String userName;
    private String userMobile;
}
