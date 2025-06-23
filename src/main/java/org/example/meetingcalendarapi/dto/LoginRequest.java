package org.example.meetingcalendarapi.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class LoginRequest {
    @NotNull
    private String username;
    @NotNull
    private String password;
}
