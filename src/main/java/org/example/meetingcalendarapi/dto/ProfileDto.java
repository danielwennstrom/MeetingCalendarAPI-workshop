package org.example.meetingcalendarapi.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProfileDto {
    @NotNull
    private String name;
    @NotNull
    @Email
    private String email;
    @NotNull
    private String phoneNumber;
    private String avatarUrl;
}
