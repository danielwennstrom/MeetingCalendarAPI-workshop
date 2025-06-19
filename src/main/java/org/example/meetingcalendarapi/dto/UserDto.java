package org.example.meetingcalendarapi.dto;

import org.example.meetingcalendarapi.enums.UserRole;

public class UserDto {
    private Long id;
    private String username;
    private UserRole role;
    private ProfileDto profile;
}
