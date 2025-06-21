package org.example.meetingcalendarapi.dto;

import lombok.Data;
import org.example.meetingcalendarapi.enums.UserRole;

@Data
public class UserDto {
    private Long id;
    private String username;
    private UserRole role;
    private ProfileDto profile;
}
