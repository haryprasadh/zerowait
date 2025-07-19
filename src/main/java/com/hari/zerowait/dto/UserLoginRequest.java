package com.hari.zerowait.dto;

import lombok.Getter;
import jakarta.validation.constraints.*;

@Getter
public class UserLoginRequest {

    @NotBlank(message = "Name cannot be blank")
    @Size(min = 3, message = "Name must be at least 3 characters")
    @Pattern(regexp = "^[^\\s]+$", message = "Name must not contain spaces")
    private String name;

    @NotNull(message = "Mobile cannot be null")
    @Pattern(regexp = "^[0-9]{10}$", message = "Mobile must be exactly 10 digits (numbers only)")
    private Long mobile;

    @NotBlank(message = "Secret cannot be blank")
    @Size(min = 3, message = "Secret must be at least 3 characters")
    @Pattern(regexp = "^[^\\s]+$", message = "Secret must not contain spaces")
    private String secret;
}
