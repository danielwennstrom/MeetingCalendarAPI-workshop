package org.example.meetingcalendarapi.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegisterDto {
    @NotNull
    private String username;
    @NotNull
    private String password;
    private ProfileDto profile;
}
