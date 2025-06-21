package org.example.meetingcalendarapi.dto;

import lombok.Getter;

@Getter
public class UserRegisterDto {
    private Long id;
    private String username;
    private String password;
    private ProfileDto profile;
}
